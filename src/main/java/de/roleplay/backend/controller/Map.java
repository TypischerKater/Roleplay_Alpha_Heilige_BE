package de.roleplay.backend.controller;

import de.roleplay.backend.DungonMap;
import de.roleplay.backend.MapGenerator;
import de.roleplay.backend.dungonGenerator.MapCoverService;
import de.roleplay.backend.service.GameService;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.UUID;

@RestController
@RequestMapping("api/map")
public class Map {

    private Gson gson;

    private final GameService gameService;
    private final MapCoverService mapCoverService;

    public Map(GameService gameService,
               MapCoverService mapCoverService) {
        this.gameService = gameService;
        this.mapCoverService = mapCoverService;
        gson = new Gson();
    }

    @GetMapping
    public String getMap(){
        MapGenerator mg = new MapGenerator();
        DungonMap[][] dungon = mg.generateAndReturnMap(100, 100, 10, 10, 10);
        return gson.toJson(dungon);

    }

    @GetMapping(value = "/{gameuuid}/{spieleruuid}")
    public String getMap(@PathVariable(value = "gameuuid") final UUID gameuuid, @PathVariable(value = "gameuuid") final UUID spieleruuid){
        int radius = 5;
        DungonMap[][] map = gson.fromJson(gameService.getMapByUuid(gameuuid), DungonMap[][].class);
        if(!gameService.isGameMaster(gameuuid, spieleruuid)){
            map = mapCoverService.updateVisibleArea(map, radius, spieleruuid);
            return gson.toJson(map);
        }
        return gson.toJson(map);
    }
}
