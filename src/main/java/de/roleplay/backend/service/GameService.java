package de.roleplay.backend.service;

import de.roleplay.backend.DTOs.CreateGameDTO;
import de.roleplay.backend.DTOs.JoinDTO;
import de.roleplay.backend.DungonMap;
import de.roleplay.backend.entitys.Coordinates;
import de.roleplay.backend.entitys.GameEntity;
import de.roleplay.backend.entitys.MapType;
import de.roleplay.backend.entitys.PlayerEntity;
import de.roleplay.backend.repositorys.GameRepository;
import de.roleplay.backend.repositorys.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.google.gson.Gson;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private  Gson gson;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.gson = new Gson();

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
        Map<Integer, UUID> turnOrder = this.convertFromString(game.getTurnOrder());
        turnOrder.forEach((k,v) -> {
            if (v == playerEntity.getPlayerId()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "player with this ID Already exists in this game");
            }
        });

        turnOrder.put(turnOrder.size() + 1,  playerEntity.getPlayerId());


        game.update(turnOrder);
        return this.gameRepository.saveAndFlush(game);
    }


    public Map<Integer, UUID> convertFromString(String mapAsString) {
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
        return gson.fromJson(gameRepository.getMapByGameId(uuid), DungonMap[][].class);
}

    public UUID getNextTurn(UUID gameID){
        GameEntity game = this.gameRepository.findByGameId(gameID).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "no game exists by"));
        int newTurn = game.getLastTurn() + 1;
        Map<Integer, UUID> turnOrder = this.convertFromString(game.getTurnOrder());

        if(turnOrder.size() > newTurn) {
            newTurn = 0;
        }
        return turnOrder.get(newTurn);
    }

    @Transactional(rollbackOn = {ResponseStatusException.class})
    public DungonMap[][] Move(UUID gameID, UUID characterID, Coordinates newPos) {

        GameEntity game = gameRepository.findByGameId(gameID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "thre is no game with this id"));
        PlayerEntity playerEntity = this.playerRepository.findByPlayerId(characterID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "you are not participating in this game"));

        if(characterID == this.getNextTurn(gameID)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not your Turn");
        }
        DungonMap[][] map = gson.fromJson(game.getMap(), DungonMap[][].class);

        DungonMap oldPosPlayer = map[playerEntity.getCoordinates().getPosX()][playerEntity.getCoordinates().getPosY()];
        DungonMap newPosPlayer = map[newPos.getPosX()][newPos.getPosY()];

        if(!(newPosPlayer.getFieldType() == MapType.FLORE)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "this is no vaild field to sand on");
        }

        if(!newPosPlayer.getMonsters().isEmpty()){
            //todo start fightRound when implemented
        }
        newPosPlayer.addPlayer(playerEntity);
        oldPosPlayer.removePlayer(playerEntity);

        map[playerEntity.getCoordinates().getPosX()][playerEntity.getCoordinates().getPosY()] = oldPosPlayer;
        map[newPos.getPosX()][newPos.getPosY()] = newPosPlayer;

        game.update(map);

        gameRepository.saveAndFlush(game);
        playerRepository.saveAndFlush(playerEntity);

        return map;
    }
}
