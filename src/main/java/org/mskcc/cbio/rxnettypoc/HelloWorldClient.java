package org.mskcc.cbio.rxnettypoc;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Copyright (c) Fred Criscuolo
 * fcriscu1@jhu.edu
 * <p/>
 * <p/>
 * Created by fcriscuo on 7/11/15.
 */
public class HelloWorldClient {

    private final int port;
    static final int DEFAULT_PORT = 9090;

    public HelloWorldClient(int port) {
        this.port = port;
    }

    public String sendHelloRequest() throws InterruptedException, ExecutionException, TimeoutException {
        return RxNetty.createHttpGet("http://localhost:" + port + "/hello")
                .flatMap(response -> {
                    printResponseHeader(response);
                    return response.getContent().<String> map(content -> {
                        return content.toString(Charset.defaultCharset());
                    });
                })
                .toBlocking()
                .toFuture().get(1, TimeUnit.MINUTES);
    }

    public void printResponseHeader(HttpClientResponse<ByteBuf> response) {
        System.out.println("New response received.");
        System.out.println("========================");
        System.out.println(response.getHttpVersion().text() + ' ' + response.getStatus().code()
                + ' ' + response.getStatus().reasonPhrase());
        for (Map.Entry<String, String> header : response.getHeaders().entries()) {
            System.out.println(header.getKey() + ": " + header.getValue());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        String value = new HelloWorldClient(port).sendHelloRequest();
        System.out.println(value);
        System.out.println("========================");
    }
}
