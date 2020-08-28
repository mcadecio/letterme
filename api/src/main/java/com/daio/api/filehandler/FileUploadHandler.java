package com.daio.api.filehandler;

import com.google.inject.ImplementedBy;
import io.vertx.ext.web.FileUpload;

@FunctionalInterface
@ImplementedBy(FileUploadHandlerImpl.class)
public interface FileUploadHandler {

    void handle(FileUpload fileUpload);
}
