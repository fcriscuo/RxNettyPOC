package org.mskcc.cbio.rxnettypoc;

import io.netty.buffer.*;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;

import java.util.Map;
/**
 * Copyright (c) Fred Criscuolo
 * fcriscu1@jhu.edu
 * <p/>
 * <p/>
 * Created by fcriscuo on 7/10/15.
 */

/*
RxNetty-based implementation of a Web socket server
 */
public class CacheServer {

    static final int DEFAULT_PORT = 8090;

    private final int port;

    public CacheServer(int aPort){
        this.port = aPort;
    }
    public HttpServer<ByteBuf, ByteBuf> createServer() {
        return RxNetty.createHttpServer(port, (request, response) -> {
            printRequestHeader(request);
            return response.writeStringAndFlush("Hello from Cache Server!");
        });
    }

    public void printRequestHeader(HttpServerRequest<ByteBuf> request) {
        System.out.println("New request received");
        System.out.println(request.getHttpMethod() + " " + request.getUri() + ' ' + request.getHttpVersion());
        for (Map.Entry<String, String> header : request.getHeaders().entries()) {
            System.out.println(header.getKey() + ": " + header.getValue());
        }
    }

    public static void main(final String[] args) {
        System.out.println("HTTP cache server starting ...");
        new CacheServer(DEFAULT_PORT).createServer().startAndWait();
    }

}
