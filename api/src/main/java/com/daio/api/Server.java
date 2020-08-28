package com.daio.api;

import com.daio.api.config.ServiceConfig;
import com.daio.api.module.ApiModule;
import com.daio.api.services.Service;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private final Service letterService;
    private HttpServer httpServer;
    private final ServiceConfig config;

    @Inject
    Server(Service service, ServiceConfig config) {
        this.config = config;
        letterService = service;
    }

    @Override
    public void start(Promise<Void> startPromise) {

        final var router = Router.router(vertx);

        router.route("/*").handler(StaticHandler.create("static"));
        router.route("/api/letters/save").handler(BodyHandler.create(config.getUploadDirectory()));
        router.route("/api*").handler(rc -> {
            final String path = rc.request().path();
            logger.info("Received request on path - {}", path);
            rc.next();
        });
        router.get("/api/letters/listall").handler(rc -> letterService.listAll(rc).end());
        router.get("/api/letters/get/:date/:filename").handler(rc -> letterService.getFile(rc, config.getUploadDirectory()).end());
        router.post("/api/letters/save").handler(rc -> letterService.save(rc).end());
        router.delete("/api/letters/delete/:date/:filename").handler(rc -> letterService.remove(rc, config.getUploadDirectory()).end());

        httpServer = vertx.createHttpServer();
        httpServer
                .requestHandler(router)
                .listen(config.getPort(), config.getDefaultAddress(), result -> {
                    logger.info("HTTP Server Started ...");
                    startPromise.complete();
                });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> vertx.close()));
    }

    @Override
    public void stop() {
        logger.info("Shutting Down...");
        httpServer.close(ar -> logger.info("Shutting Down HTTP Server"));
    }

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new ApiModule());
        Vertx.vertx().deployVerticle(injector.getInstance(Server.class));
    }
}
