package com.daio.api.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ServiceConfigTest {

    private ServiceConfig config;

    @Nested
    class TestServiceConfigWhenConfigIsPresent {

        @BeforeEach
        void before() {
            config = new ServiceConfig(Paths.get(
                    "src",
                    "test",
                    "resources",
                    "cfg",
                    "test-service.json"
            ));
        }

        @Test
        void getUploadDirectory() {
            final String actualUploadDirectory = config.getUploadDirectory();
            final String expectedUploadDirectory = "/path/to/upload";

            assertEquals(expectedUploadDirectory, actualUploadDirectory);
        }

        @Test
        void getPort() {
            final int actualPort = config.getPort();
            final int expectedPort = 80;

            assertEquals(expectedPort, actualPort);
        }

        @Test
        void getDefaultAddress() {
            final String actualDefaultAddress = config.getDefaultAddress();
            final String expectedDefaultAddress = "localhost";

            assertEquals(expectedDefaultAddress, actualDefaultAddress);
        }
    }

    @Nested
    class TestServiceConfigWhenConfigIsNotPresent {

        @BeforeEach
        void before() {
            config = new ServiceConfig(Paths.get(
                    ""
            ));
        }

        @Test
        void getUploadDirectory() {
            final String actualUploadDirectory = config.getUploadDirectory();
            final String expectedUploadDirectory = "";

            assertEquals(expectedUploadDirectory, actualUploadDirectory);
        }

        @Test
        void getPort() {
            final int actualPort = config.getPort();
            final int expectedPort = -1;

            assertEquals(expectedPort, actualPort);
        }

        @Test
        void getDefaultAddress() {
            final String actualDefaultAddress = config.getDefaultAddress();
            final String expectedDefaultAddress = "";

            assertEquals(expectedDefaultAddress, actualDefaultAddress);
        }
    }
}
