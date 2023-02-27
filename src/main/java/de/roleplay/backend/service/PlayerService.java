package de.roleplay.backend.service;

import de.roleplay.backend.DTOs.CreatePlayerDTO;
import de.roleplay.backend.entitys.PlayerEntity;
import de.roleplay.backend.repositorys.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public PlayerEntity createPlayer(CreatePlayerDTO createPlayerDTO){
        int statsSum = createPlayerDTO.getConstitution() + createPlayerDTO.getIntelligence() + createPlayerDTO.getStrength() + createPlayerDTO.getWisdom() + createPlayerDTO.getDexterity();
        if(!(statsSum >= 60 && statsSum <= 80)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the Stats are not vaild");
        }

        return playerRepository.save(new PlayerEntity(createPlayerDTO));
    }


    public ArrayList<Integer> getSkillPoints(){
        ArrayList<Integer> stats = new ArrayList();
        for(int i = 0; i <= 5; i++){
            stats.add( (int) Math.floor(Math.random() * 18) + 3);
        }
        while (stats.stream().mapToInt(a -> a).sum() <= 60){
            stats.remove(Collections.min(stats));
            stats.add( (int) Math.floor(Math.random() * 18) + 3);
        }

        return stats;
    }

}
