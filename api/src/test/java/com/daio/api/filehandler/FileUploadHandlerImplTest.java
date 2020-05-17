package com.daio.api.filehandler;

import io.vertx.ext.web.FileUpload;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileUploadHandlerImplTest {

    private static final String BASE_DIR = System.getProperty("user.dir") + "/src/test/file-uploads";
    private static final String UPLOADED_FILENAME = BASE_DIR + "/uploaded-filename";
    private static final String FILENAME = "filename.txt";

    @Mock
    private FileUpload fileUpload;

    private FileUploadHandler fileHandler;

    @BeforeEach
    void before() throws IOException {
        Files.createFile(Paths.get(UPLOADED_FILENAME));
        fileHandler = new FileUploadHandlerImpl();
        when(fileUpload.uploadedFileName()).thenReturn(UPLOADED_FILENAME);
        when(fileUpload.fileName()).thenReturn(FILENAME);
    }

    @DisplayName("handle() should rename and move file to date named folder")
    @Test
    void testHandle() throws IOException {
        fileHandler.handle(fileUpload);

        final Path expectedFilePath = Paths.get(BASE_DIR, LocalDate.now().toString(), FILENAME);
        assertTrue(Files.exists(expectedFilePath));
        FileUtils.deleteDirectory(expectedFilePath.getParent().toFile());
    }

    @DisplayName("handle() should create target directory if does not exist")
    @Test
    void testHandle1() throws IOException {
        fileHandler.handle(fileUpload);

        final Path expectedFilePath = Paths.get(BASE_DIR, LocalDate.now().toString());
        assertTrue(Files.exists(expectedFilePath));
        FileUtils.deleteDirectory(expectedFilePath.toFile());
    }
}