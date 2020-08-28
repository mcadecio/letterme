package com.daio.api.services;

import com.google.inject.ImplementedBy;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

@ImplementedBy(LetterService.class)
public interface Service {

    HttpServerResponse save(RoutingContext routingContext);

    HttpServerResponse listAll(RoutingContext routingContext);

    HttpServerResponse getFile(RoutingContext routingContext, String dir);

    HttpServerResponse remove(RoutingContext routingContext, String uploadDirectory);
}
