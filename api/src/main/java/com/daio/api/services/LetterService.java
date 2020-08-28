package com.daio.api.services;

import com.daio.api.filehandler.FileNameSupplier;
import com.daio.api.filehandler.FileUploadHandler;
import com.daio.api.response.DefaultResponse;
import com.google.inject.Inject;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LetterService implements Service {


    private static final Logger logger = LoggerFactory.getLogger(LetterService.class);
    private final FileNameSupplier fileSupplier;
    private final FileUploadHandler fileHandler;

    @Inject
    public LetterService(FileUploadHandler fileHandler, FileNameSupplier fileSupplier) {
        this.fileHandler = fileHandler;
        this.fileSupplier = fileSupplier;
    }

    @Override
    public HttpServerResponse save(RoutingContext routingContext) {
        routingContext.fileUploads().forEach(fileHandler::handle);
        return routingContext.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setStatusCode(200)
                .setChunked(true)
                .write(new DefaultResponse(true, "").toString());
    }

    @Override
    public HttpServerResponse listAll(RoutingContext routingContext) {
        return routingContext.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setStatusCode(200)
                .setChunked(true)
                .write(new DefaultResponse(true, fileSupplier.get()).toString());
    }

    @Override
    public HttpServerResponse getFile(RoutingContext routingContext, String directory) {
        final String fileName = routingContext.pathParam("filename");
        final String date = routingContext.pathParam("date");
        return routingContext.response()
                .setStatusCode(200)
                .sendFile(Paths.get(directory, date, fileName).toString());
    }

    @Override
    public HttpServerResponse remove(RoutingContext routingContext, String directory) {
        final String fileName = routingContext.pathParam("filename");
        final String date = routingContext.pathParam("date");
        var jsonObject = deleteFile(fileName, date, directory);
        return routingContext.response()
                .setStatusCode(200)
                .setChunked(true)
                .write(new DefaultResponse(true, jsonObject).toString());
    }

    private Map<String, Object> deleteFile(String filename, String date, String directory) {
        Map<String, Object> mapResult = new HashMap<>();

        try {
            final boolean wasDeleted = Files.deleteIfExists(Paths.get(directory, date, filename));
            mapResult.put("wasDeleted", wasDeleted);
        } catch (IOException e) {
            logger.error("Failed to delete file because {}", e.getMessage());
            mapResult.put("wasDeleted", false);
            mapResult.put("reason", e.getMessage());
        }

        return mapResult;
    }
}
