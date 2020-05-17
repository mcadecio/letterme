package com.daio.api.jsonconverter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JsonConverter {

    private final ObjectMapper objectMapper;
    private final Gson gson;

    public JsonConverter() {
        this(new ObjectMapper(), new Gson());
    }

    JsonConverter(final ObjectMapper objectMapper, final Gson gson) {
        this.objectMapper = objectMapper;
        this.gson = gson;
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public String writeValueAsString(Object object) {
        String result;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            result = gson.toJson(object);
        }
        return result;
    }
}
