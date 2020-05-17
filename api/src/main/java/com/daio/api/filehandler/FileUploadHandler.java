package com.daio.api.filehandler;

import io.vertx.ext.web.FileUpload;

@FunctionalInterface
public interface FileUploadHandler {

    void handle(FileUpload fileUpload);
}
