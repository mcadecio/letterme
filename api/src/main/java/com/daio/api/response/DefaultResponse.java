package com.daio.api.response;

import com.daio.api.jsonconverter.JsonConverter;

public class DefaultResponse {

    private boolean success;
    private Object result;

    public DefaultResponse(boolean success, Object result) {
        this.success = success;
        this.result = result;
    }

    @Override
    public String toString() {
        return new JsonConverter().writeValueAsString(this);
    }
}
