package com.daio.api.filehandler;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileNameSupplierImplTest {

    private static final String DIRECTORY = System.getProperty("user.dir") + File.separatorChar + "src/test/file-uploads";

    private FileNameSupplier fileNameSupplier;

    @BeforeEach
    void before() throws IOException {
        fileNameSupplier = new FileNameSupplierImpl(DIRECTORY);
        FileUtils.deleteDirectory(Paths.get(DIRECTORY).toFile());
        Files.createDirectory(Paths.get(DIRECTORY));
    }

    @DisplayName("get() should return the filename of all the files in directory")
    @Test
    void testGet1() throws IOException {
        generateFiles(3);

        final Map<String, List<String>> fileNames = fileNameSupplier.get();
        final Map<String, List<String>> expected = Map.of(
          "ABC0", Collections.singletonList("temp0.txt"),
          "ABC1", Collections.singletonList("temp1.txt"),
          "ABC2", Collections.singletonList("temp2.txt")
        );

        assertEquals(3, fileNames.size());
        assertEquals(fileNames, expected);
    }


    @DisplayName("get() should return an empty list when there are no files in directory")
    @Test
    void testGet2() {
        final Map<String, List<String>> fileNames = fileNameSupplier.get();

        assertTrue(fileNames.isEmpty());
    }

    private void generateFiles(int nFiles) throws IOException {
        for (int i = 0; i < nFiles; i++) {
            final String dir = DIRECTORY + "/ABC" + i;
            Files.createDirectory(Paths.get(dir));
            Files.createFile(Paths.get(dir, "temp" + i + ".txt"));
        }
    }
}