package com.daio.api;

import com.daio.api.filehandler.FileNameSupplierImpl;
import com.daio.api.filehandler.FileUploadHandlerImpl;
import com.daio.api.services.LetterService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server extends AbstractVerticle {

    private static final String UPLOAD_DIRECTORY = "/Users/ddaio/work/LetterMe/fileuploads";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final LetterService letterService = new LetterService(new FileUploadHandlerImpl(), new FileNameSupplierImpl(UPLOAD_DIRECTORY));
    private HttpServer httpServer;

    @Override
    public void start(Promise<Void> startPromise) {

        final var router = Router.router(vertx);

        router.route("/api/letters/save").handler(BodyHandler.create(UPLOAD_DIRECTORY));

        router.get("/api/letters/listall").handler(rc -> letterService.listAll(rc).end());
        /*
            curl \
              -F "userid=1" \
              -F "filecomment=This is an image file" \
              -F "image=@/home/user1/Desktop/test.jpg" \
              localhost/uploader.php
         */
        router.post("/api/letters/save").handler(rc -> letterService.save(rc).end());
        router.get("/api/letters/get/:date/:filename").handler(rc -> letterService.getFile(rc, UPLOAD_DIRECTORY).end());


        httpServer = vertx.createHttpServer();
        httpServer
                .requestHandler(router)
                .listen(8080, "192.168.0.79", result -> {
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
        Vertx.vertx().deployVerticle(new Server());
    }
}
