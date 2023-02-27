package de.roleplay.backend.controller;

import de.roleplay.backend.DTOs.CreatePlayerDTO;
import de.roleplay.backend.entitys.PlayerEntity;
import de.roleplay.backend.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController("player/")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("player/create")
    public PlayerEntity createPlayer(@RequestBody final CreatePlayerDTO createPlayerDTO){
        return playerService.createPlayer(createPlayerDTO);
    }

    @GetMapping("player/generate/skillpoints")
    public ArrayList<Integer> generateAndReturnSkillPoints(){
        return playerService.getSkillPoints();
    }
}
