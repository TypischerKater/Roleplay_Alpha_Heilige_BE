package de.roleplay.backend.controller;

import de.roleplay.backend.DTOs.CreateGameDTO;
import de.roleplay.backend.DTOs.JoinDTO;
import de.roleplay.backend.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class GameController {

    private final String GAME_PATH = "/game";
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping(path = GAME_PATH + "/create")
    public UUID createGame(@RequestBody CreateGameDTO createGameDTO){
        return gameService.createGame(createGameDTO).getGameId();
    }

    @PostMapping(path = GAME_PATH + "/join")
    public void joinGame(@RequestBody JoinDTO joinDTO){
        this.gameService.joinGame(joinDTO);
    }

    @GetMapping(path = GAME_PATH + "/next/{gameId}")
    public UUID getNextTurn(@PathVariable UUID gameId){
       return this.gameService.getNextTurn(gameId);
    }
}
