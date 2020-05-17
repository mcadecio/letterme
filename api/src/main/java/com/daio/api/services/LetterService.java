package com.daio.api.services;

import com.daio.api.filehandler.FileNameSupplier;
import com.daio.api.filehandler.FileUploadHandler;
import com.daio.api.response.DefaultResponse;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class LetterService implements Service {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String INFO_LINE = "Received request on path - {}";

    private final FileNameSupplier fileSupplier;
    private final FileUploadHandler fileHandler;

    public LetterService(FileUploadHandler fileHandler, FileNameSupplier fileSupplier) {
        this.fileHandler = fileHandler;
        this.fileSupplier = fileSupplier;
    }

    @Override
    public HttpServerResponse save(RoutingContext routingContext) {
        final String path = routingContext.request().path();
        logger.info(INFO_LINE, path);
        routingContext.fileUploads().forEach(fileHandler::handle);
        return routingContext.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setStatusCode(200)
                .setChunked(true)
                .write(new DefaultResponse(true, "").toString());
    }

    @Override
    public HttpServerResponse listAll(RoutingContext routingContext) {
        final String path = routingContext.request().path();
        logger.info(INFO_LINE, path);
        return routingContext.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setStatusCode(200)
                .setChunked(true)
                .write(new DefaultResponse(true, fileSupplier.get()).toString());
    }

    @Override
    public HttpServerResponse getFile(RoutingContext routingContext, String directory) {
        final String path = routingContext.request().path();
        logger.info(INFO_LINE, path);
        final String fileName = routingContext.pathParam("filename");
        final String date = routingContext.pathParam("date");
        return routingContext.response()
                .setStatusCode(200)
                .sendFile(Paths.get(directory, date, fileName).toString());
    }
}
