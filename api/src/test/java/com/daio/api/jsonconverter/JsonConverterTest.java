package com.daio.api.jsonconverter;

import com.daio.api.response.DefaultResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JsonConverterTest {

    @DisplayName("should convert object to json")
    @Test
    void testWriteValueAsString() {
        final DefaultResponse object = new DefaultResponse(true, new int[]{1, 2, 3});

        final String actual = new JsonConverter().writeValueAsString(object);
        final String expected = "{\"success\":true,\"result\":[1,2,3]}";

        assertEquals(expected, actual);
    }

    @DisplayName("converter should fallback to gson when jackson fails")
    @Test
    void testWriteValueAsString1() throws JsonProcessingException {
        final ObjectMapper mockedMapper = mock(ObjectMapper.class);
        when(mockedMapper.writeValueAsString(any())).thenThrow(new JsonParseException(null, "Expected"));
        final DefaultResponse object = new DefaultResponse(true, new int[]{1, 2, 3});

        final String actual = new JsonConverter(mockedMapper, new Gson()).writeValueAsString(object);
        final String expected = "{\"success\":true,\"result\":[1,2,3]}";

        assertEquals(expected, actual);
    }

}