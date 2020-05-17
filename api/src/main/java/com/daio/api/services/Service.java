package com.daio.api.services;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public interface Service {

    HttpServerResponse save(RoutingContext routingContext);

    HttpServerResponse listAll(RoutingContext routingContext);

    HttpServerResponse getFile(RoutingContext routingContext, String dir);

}
