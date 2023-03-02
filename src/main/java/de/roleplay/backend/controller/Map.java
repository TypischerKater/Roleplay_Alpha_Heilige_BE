package de.roleplay.backend.controller;

import de.roleplay.backend.DungonMap;
import de.roleplay.backend.MapGenerator;
import de.roleplay.backend.service.GameService;
import de.roleplay.backend.service.PlayerService;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.UUID;

@RestController
@RequestMapping("api/map")
public class Map {

    private Gson gson;

    private final PlayerService playerService;
    private final GameService gameService;

    public Map(PlayerService playerService,
               GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
        gson = new Gson();
    }

    @GetMapping
    public String getMap(){
        MapGenerator mg = new MapGenerator(playerService);
        DungonMap[][] dungon = mg.generateAndReturnMap(100, 100, 10, 10, 10);
        gson.fromJson(gson.toJson(dungon), DungonMap.class);
        return gson.toJson(dungon);

    }

    @GetMapping(value = "/{uuid}")
    public DungonMap[][] getMap(@PathVariable(value = "uuid") final UUID uuid){
        return gameService.getMapByUuid(uuid);
    }
}
