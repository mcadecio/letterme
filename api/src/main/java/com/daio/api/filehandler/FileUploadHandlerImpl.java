package com.daio.api.filehandler;

import io.vertx.ext.web.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class FileUploadHandlerImpl implements FileUploadHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handle(FileUpload fileUpload) {
        final String uploadedFileName = fileUpload.uploadedFileName();

        final String directoryName = uploadedFileName.substring(0, uploadedFileName.lastIndexOf('/'))
                + File.separator
                + LocalDate.now();

        try {
            String path = createIfNotExists(directoryName);
            renameFileTo(uploadedFileName, fileUpload.fileName(), path);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

    private void renameFileTo(String uploadedFileName, String targetFileName, String path) {
        final File file = new File(uploadedFileName);
        final boolean renameSucceeded = file.renameTo(new File(path + File.separator + targetFileName));

        if (renameSucceeded) {
            logger.info("Rename Succeeded");
        }
    }

    private String createIfNotExists(String directoryName) throws IOException {
        final Path path = Paths.get(directoryName);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        return path.toString();
    }
}
