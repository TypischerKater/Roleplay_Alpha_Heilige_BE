package de.roleplay.backend.config;

import de.roleplay.backend.service.GameService;
import de.roleplay.backend.service.Websockethandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private final GameService gameService;

    public WebsocketConfig(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/websocket")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler webSocketHandler(){
        return new Websockethandler(gameService);
    }
}
