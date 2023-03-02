package de.roleplay.backend.service;

import de.roleplay.backend.DTOs.CreateGameDTO;
import de.roleplay.backend.DTOs.JoinDTO;
import de.roleplay.backend.DungonMap;
import de.roleplay.backend.entitys.GameEntity;
import de.roleplay.backend.entitys.PlayerEntity;
import de.roleplay.backend.repositorys.GameRepository;
import de.roleplay.backend.repositorys.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.google.gson.Gson;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public GameEntity createGame(CreateGameDTO createGameDTO) {
        GameEntity gameEntity = new GameEntity(createGameDTO);
        gameEntity.setGameId(UUID.randomUUID());

        return gameRepository.saveAndFlush(gameEntity);
    }

    @Transactional
    public GameEntity joinGame(JoinDTO joinDTO) {
        GameEntity game = this.gameRepository.findByGameId(joinDTO.getGameID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "there is no game open with this id"));

        PlayerEntity playerEntity = playerRepository.findByPlayerId(joinDTO.getPlayerID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "there was no player found with this id"));

        System.out.println(game.getTurnOrder());
        Map<Integer, UUID> turnOrder = this.convertToString(game.getTurnOrder());
        turnOrder.forEach((k,v) -> {
            if (v == playerEntity.getPlayerId()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "player with this ID Already exists in this game");
            }
        });

        turnOrder.put(turnOrder.size() + 1,  playerEntity.getPlayerId());


        game.update(turnOrder);
        return this.gameRepository.saveAndFlush(game);
    }


    public Map<Integer, UUID> convertToString(String mapAsString) {
        mapAsString = mapAsString.replace("{", "");
        mapAsString = mapAsString.replace("}", "");
        Map<Integer, UUID> myMap = new HashMap<>();
        String[] Pairs = mapAsString.split(",");
        for (String pair: Pairs) {
            String[] keyValue = pair.split("=");
            myMap.put(Integer.valueOf(keyValue[0].replace(" ", "")),UUID.fromString(keyValue[1].replace(" ", "")));
        }

        return myMap;
    }

    public DungonMap[][] getMapByUuid(UUID uuid) {
        return gameRepository.getMapByGameId(uuid);
        return null;
    }
}
