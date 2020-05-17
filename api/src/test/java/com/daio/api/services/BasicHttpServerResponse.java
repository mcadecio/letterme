package com.daio.api.services;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;

import java.util.HashMap;
import java.util.Map;

public class BasicHttpServerResponse implements HttpServerResponse {

    public String body = "";
    public Map<String, String> headers = new HashMap<>();
    public int statusCode;
    public String sendFile = "";

    @Override
    public HttpServerResponse sendFile(String filename) {
        sendFile = filename;
        return this;
    }

    @Override
    public HttpServerResponse sendFile(String filename, long offset) {
        return null;
    }

    @Override
    public HttpServerResponse sendFile(String filename, Handler<AsyncResult<Void>> resultHandler) {
        return null;
    }

    @Override
    public HttpServerResponse sendFile(String filename, long offset, Handler<AsyncResult<Void>> resultHandler) {
        return null;
    }

    @Override
    public void reset() {

    }

    @Override
    public HttpServerResponse writeCustomFrame(HttpFrame frame) {
        return null;
    }

    @Override
    public HttpServerResponse setStreamPriority(StreamPriority streamPriority) {
        return null;
    }

    @Override
    public @Nullable Cookie removeCookie(String name) {
        return null;
    }

    @Override
    public HttpServerResponse exceptionHandler(Handler<Throwable> handler) {
        return null;
    }

    @Override
    public HttpServerResponse write(Buffer buffer) {
        return null;
    }

    @Override
    public HttpServerResponse write(Buffer buffer, Handler<AsyncResult<Void>> handler) {
        return null;
    }

    @Override
    public HttpServerResponse setWriteQueueMaxSize(int i) {
        return null;
    }

    @Override
    public boolean writeQueueFull() {
        return false;
    }

    @Override
    public HttpServerResponse drainHandler(Handler<Void> handler) {
        return null;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public HttpServerResponse setStatusCode(int i) {
        statusCode = i;
        return this;
    }

    @Override
    public String getStatusMessage() {
        return null;
    }

    @Override
    public HttpServerResponse setStatusMessage(String s) {
        return null;
    }

    @Override
    public HttpServerResponse setChunked(boolean b) {
        return this;
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public MultiMap headers() {
        return null;
    }

    @Override
    public HttpServerResponse putHeader(String s, String s1) {
        return null;
    }

    @Override
    public HttpServerResponse putHeader(CharSequence charSequence, CharSequence charSequence1) {
        headers.put(charSequence.toString(), charSequence1.toString());
        return this;
    }

    @Override
    public HttpServerResponse putHeader(String s, Iterable<String> iterable) {
        return null;
    }

    @Override
    public HttpServerResponse putHeader(CharSequence charSequence, Iterable<CharSequence> iterable) {
        return null;
    }

    @Override
    public MultiMap trailers() {
        return null;
    }

    @Override
    public HttpServerResponse putTrailer(String s, String s1) {
        return null;
    }

    @Override
    public HttpServerResponse putTrailer(CharSequence charSequence, CharSequence charSequence1) {
        return null;
    }

    @Override
    public HttpServerResponse putTrailer(String s, Iterable<String> iterable) {
        return null;
    }

    @Override
    public HttpServerResponse putTrailer(CharSequence charSequence, Iterable<CharSequence> iterable) {
        return null;
    }

    @Override
    public HttpServerResponse closeHandler(@Nullable Handler<Void> handler) {
        return null;
    }

    @Override
    public HttpServerResponse endHandler(@Nullable Handler<Void> handler) {
        return null;
    }

    @Override
    public HttpServerResponse write(String s, String s1) {
        return null;
    }

    @Override
    public HttpServerResponse write(String s, String s1, Handler<AsyncResult<Void>> handler) {
        return null;
    }

    @Override
    public HttpServerResponse write(String s) {
        body = s;
        return this;
    }

    @Override
    public HttpServerResponse write(String s, Handler<AsyncResult<Void>> handler) {
        return null;
    }

    @Override
    public HttpServerResponse writeContinue() {
        return null;
    }

    @Override
    public void end(String s) {

    }

    @Override
    public void end(String s, Handler<AsyncResult<Void>> handler) {

    }

    @Override
    public void end(String s, String s1) {

    }

    @Override
    public void end(String s, String s1, Handler<AsyncResult<Void>> handler) {

    }

    @Override
    public void end(Buffer buffer) {

    }

    @Override
    public void end(Buffer buffer, Handler<AsyncResult<Void>> handler) {

    }

    @Override
    public void end() {

    }

    @Override
    public void end(Handler<AsyncResult<Void>> handler) {

    }

    @Override
    public HttpServerResponse sendFile(String s, long l, long l1) {
        return null;
    }

    @Override
    public HttpServerResponse sendFile(String s, long l, long l1, Handler<AsyncResult<Void>> handler) {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean ended() {
        return false;
    }

    @Override
    public boolean closed() {
        return false;
    }

    @Override
    public boolean headWritten() {
        return false;
    }

    @Override
    public HttpServerResponse headersEndHandler(@Nullable Handler<Void> handler) {
        return null;
    }

    @Override
    public HttpServerResponse bodyEndHandler(@Nullable Handler<Void> handler) {
        return null;
    }

    @Override
    public long bytesWritten() {
        return 0;
    }

    @Override
    public int streamId() {
        return 0;
    }

    @Override
    public HttpServerResponse push(HttpMethod httpMethod, String s, String s1, Handler<AsyncResult<HttpServerResponse>> handler) {
        return null;
    }

    @Override
    public HttpServerResponse push(HttpMethod httpMethod, String s, MultiMap multiMap, Handler<AsyncResult<HttpServerResponse>> handler) {
        return null;
    }

    @Override
    public HttpServerResponse push(HttpMethod httpMethod, String s, Handler<AsyncResult<HttpServerResponse>> handler) {
        return null;
    }

    @Override
    public HttpServerResponse push(HttpMethod httpMethod, String s, String s1, MultiMap multiMap, Handler<AsyncResult<HttpServerResponse>> handler) {
        return null;
    }

    @Override
    public void reset(long l) {

    }

    @Override
    public HttpServerResponse writeCustomFrame(int i, int i1, Buffer buffer) {
        return null;
    }

    @Override
    public HttpServerResponse addCookie(Cookie cookie) {
        return null;
    }

    @Override
    public @Nullable Cookie removeCookie(String s, boolean b) {
        return null;
    }
}
