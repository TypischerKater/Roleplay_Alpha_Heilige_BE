package de.roleplay.backend.controller;

import de.roleplay.backend.MapGenerator;
import de.roleplay.backend.entitys.CharacterRace;
import de.roleplay.backend.entitys.PlayerEntity;
import de.roleplay.backend.repositorys.GameRepository;
import de.roleplay.backend.repositorys.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/api/map")
public class Map {

    private final PlayerRepository playerRepository;

    private final GameRepository gameRepository;

    private final MapGenerator mapGenerator = new MapGenerator();

    public Map(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }


    @PostMapping("/Test")
    public String save(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setPlayerId(UUID.randomUUID());
        playerEntity.setConstitution(1);
        playerEntity.setDexterity(1);
        playerEntity.setIntelligence(0);
        playerEntity.setCharacterRace(CharacterRace.DWARF);
        playerEntity.setGameId(UUID.randomUUID());
        playerEntity.setWisdom(0);
        playerEntity.setStrength(0);
        playerEntity.setUsername("bob");
        playerEntity.setHealthPoints(100);

        return playerRepository.save(playerEntity).getPlayerId().toString();
    }

    @GetMapping("/Test")
    public String get(){
        return playerRepository.findAll().toString();
    }

    @DeleteMapping("/Test/{id}")
    public void deletebyid(@PathVariable UUID id){
        playerRepository.deleteByPlayerId(id);
    }

    @GetMapping
    public char[][] getMap(){
        return MapGenerator.generateAndReturnMap(100,100,10,10,1);
    }
}
