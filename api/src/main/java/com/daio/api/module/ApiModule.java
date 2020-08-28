package com.daio.api.module;

import com.daio.api.config.ServiceConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ApiModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    public String defaultDirectory() {
        return new ServiceConfig().getUploadDirectory();
    }
}
