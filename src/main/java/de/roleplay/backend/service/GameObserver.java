package de.roleplay.backend.service;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class GameObserver {

    private WebSocketSession session;

    public GameObserver(WebSocketSession session) {
        this.session = session;
    }

    public void update(Game game) throws IOException {
        if (game.status == "closed"){
            session.close();
        }
        System.out.println("Update for task: " + game.getId());
        try {
            session.sendMessage(new TextMessage(game.getProgress() + ""));
            session.sendMessage(new TextMessage(game.getStatus()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
