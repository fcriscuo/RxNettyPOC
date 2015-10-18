package org.mskcc.cbio.rxnettypoc;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.channel.ObservableConnection;
import io.reactivex.netty.protocol.http.websocket.WebSocketClient;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) Fred Criscuolo
 * fcriscu1@jhu.edu
 * <p/>
 * <p/>
 * Created by fcriscuo on 7/10/15.
 */
public class CacheSignal {
    static final int DEFAULT_NO_OF_EVENTS = 100;
    static final int DEFAULT_INTERVAL = 100;
    static final int DEFAULT_PORT = 8090;

    private final int port;

    public CacheSignal(int port) {
        this.port = port;
    }

    public void sendHelloRequests(final int noOfEvents, final int interval) throws Exception {
        WebSocketClient<TextWebSocketFrame, TextWebSocketFrame> rxClient =
                RxNetty.<TextWebSocketFrame, TextWebSocketFrame> newWebSocketClientBuilder("localhost", port)
                        .withWebSocketURI("/websocket")
                        .withWebSocketVersion(WebSocketVersion.V13)
                        .build();

        rxClient.connect()
                .flatMap((ObservableConnection<TextWebSocketFrame, TextWebSocketFrame> connection) -> {
                    // start ping-pong session with an initial write
                    return connection.writeAndFlush(new TextWebSocketFrame("ping"))
                            // then we start reading
                            .concatWith(connection.getInput()
                                    // until a certain number of messages have been received
                                    .take(noOfEvents)
                                            // and for each message we log it and response back after a delay
                                    .flatMap((TextWebSocketFrame webSocketFrame) -> {
                                        System.out.println("Got back: " + webSocketFrame.text());
                                        // ping-pong back "ping" after 'internal' milliseconds
                                        return Observable.timer(interval, TimeUnit.MILLISECONDS)
                                                .flatMap(aLong -> connection.writeAndFlush(new TextWebSocketFrame("ping")));
                                    }));
                })
                .toBlocking() // block and wait for terminal event after `noOfEvents` is received and we unsubscribe
                .last();
    }

    public static void main(String[] args) throws Exception {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new CacheSignal(port).sendHelloRequests(DEFAULT_NO_OF_EVENTS, DEFAULT_INTERVAL);
    }
}
