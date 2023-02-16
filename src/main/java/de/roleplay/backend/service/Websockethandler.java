package de.roleplay.backend.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.Task;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class Websockethandler extends TextWebSocketHandler implements SubProtocolCapable {

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    private final GameService game;

    public Websockethandler(GameService game) {
        this.game = game;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("server connection opened");
        sessions.add(session);

        TextMessage message = new TextMessage("Please send Task Id");
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Server connection closed:" + status);
        sessions.remove(session);
    }

    @Scheduled(fixedRate = 10000)
    void sendPeriodicMessages() throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                String broadcast = "server periodic message " + LocalTime.now();
                System.out.println("Server sends: " + broadcast);
                session.sendMessage(new TextMessage(broadcast));
            }
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        System.out.println("Server received: " + request);

        Game game = this.game.getTaskById(UUID.fromString(request));
        if(game == null) {
            session.sendMessage(new TextMessage("No task to Id found"));
            return;
        }

        GameObserver gameObserver = new GameObserver(session);
        game.registerObserver(gameObserver);

        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
        System.out.println("Server sends: " + response);
        session.sendMessage(new TextMessage("Registered for Task updates for task with Id: " + request));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.out.println("Server transport error: " + exception.getMessage());
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.demo.websocket");
    }
}
