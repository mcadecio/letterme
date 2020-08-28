package com.daio.api.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServiceConfig {

    private static final Logger logger = LoggerFactory.getLogger(ServiceConfig.class);
    private static final String UPLOAD_DIRECTORY = "uploadDirectory";
    private static final String PORT = "port";
    private static final String DEFAULT_ADDRESS = "defaultAddress";

    private Path configPath;

    public ServiceConfig() {
        this(Paths.get(System.getProperty("user.dir"),
                "cfg",
                "service.json"));
    }

    ServiceConfig(Path configPath) {
        this.configPath = configPath;
    }

    public String getUploadDirectory() {
        return getProperty(UPLOAD_DIRECTORY).getAsString();
    }

    public int getPort() {
        return getProperty(PORT).getAsInt();
    }

    public String getDefaultAddress() {
        return getProperty(DEFAULT_ADDRESS).getAsString();
    }

    private JsonObject convertFromJson() {
        String json;
        try {
            json = new String(Files.readAllBytes(configPath));
        } catch (IOException e) {
            logger.error("Something went wrong while reading the config file: ", e);
            json = "{}";
        }
        return new JsonParser()
                .parse(json)
                .getAsJsonObject();
    }

    private JsonElement getProperty(String key) {
        JsonObject jsonObject = convertFromJson();
        if (jsonObject.has(key)) {
            return jsonObject.get(key);
        }
        return defaultJsonObject().get(key);
    }

    private JsonObject defaultJsonObject() {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(UPLOAD_DIRECTORY, "");
        jsonObject.addProperty(PORT, -1);
        jsonObject.addProperty(DEFAULT_ADDRESS, "");
        return jsonObject;
    }

}
