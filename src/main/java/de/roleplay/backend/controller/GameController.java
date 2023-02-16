package de.roleplay.backend.controller;

import de.roleplay.backend.service.Game;
import de.roleplay.backend.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public UUID createGame(){
        Game newGame = new Game();
        gameService.addTask(newGame);
        return newGame.getId();
    }
}
