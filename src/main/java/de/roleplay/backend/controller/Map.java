package de.roleplay.backend.controller;

import de.roleplay.backend.MapGenerator;
import de.roleplay.backend.entitys.CharacterClass;
import de.roleplay.backend.entitys.CharacterRace;
import de.roleplay.backend.entitys.GameEntity;
import de.roleplay.backend.entitys.PlayerEntity;
import de.roleplay.backend.repositorys.GameRepository;
import de.roleplay.backend.repositorys.PlayerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("api/map")
public class Map {


    @GetMapping
    public char[][] getMap(){
        return MapGenerator.generateAndReturnMap(100,100,10,10,1);
    }
}
