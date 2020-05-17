package com.daio.api.services;

import com.daio.api.filehandler.FileUploadHandler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LetterServiceTest {

    private static final String SOME_PATH = "/some/path";

    @Mock
    private RoutingContext routingContext;
    @Mock
    private HttpServerRequest serverRequest;

    private HttpServerResponse serverResponse;
    private FileUploadHandler fileHandler;

    @BeforeEach
    void before() {
        serverResponse = new BasicHttpServerResponse();
        fileHandler = (fileUpload) -> {
        };
        when(routingContext.request()).thenReturn(serverRequest);
        when(routingContext.response()).thenReturn(serverResponse);
        when(serverRequest.path()).thenReturn(SOME_PATH);
    }

    @Test
    @DisplayName("save() should return a 200 response")
    void testSave() {
        final LetterService letterService = new LetterService(fileHandler, null);

        final BasicHttpServerResponse response = (BasicHttpServerResponse) letterService.save(routingContext);

        assertEquals("{\"success\":true,\"result\":\"\"}", response.body);
        assertEquals(200, response.statusCode);
        assertEquals("application/json", response.headers.get(HttpHeaders.CONTENT_TYPE.toString()));
    }

    @Test
    @DisplayName("listall() should return a json with all the filenames")
    void testListAll() {
        final LetterService letterService = new LetterService(fileHandler, () -> Map.of("2020-01-01", new String[]{"text.txt"}));

        final BasicHttpServerResponse response = (BasicHttpServerResponse) letterService.listAll(routingContext);

        assertEquals("{\"success\":true,\"result\":{\"2020-01-01\":[\"text.txt\"]}}", response.body);
        assertEquals(200, response.statusCode);
        assertEquals("application/json", response.headers.get(HttpHeaders.CONTENT_TYPE.toString()));
    }

    @Test
    @DisplayName("getFile() should return the correct file path to send")
    void testGetFile() {
        when(routingContext.pathParam("filename")).thenReturn("file.txt");
        when(routingContext.pathParam("date")).thenReturn("2020-01-12");
        final LetterService letterService = new LetterService(fileHandler, null);

        final BasicHttpServerResponse response = (BasicHttpServerResponse) letterService.getFile(routingContext, "/src/file-uploads");

        assertEquals(200, response.statusCode);
        assertEquals("/src/file-uploads/2020-01-12/file.txt", response.sendFile);
    }
}