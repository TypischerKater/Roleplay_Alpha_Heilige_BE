package de.roleplay.backend.entitys;

import de.roleplay.backend.DTOs.CreateGameDTO;
import de.roleplay.backend.DTOs.GameUpdateDTO;
import de.roleplay.backend.DungonMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "game")
@NoArgsConstructor
public class GameEntity {
    @Id
    UUID gameId;
    @NonNull
    UUID gameMasterId;
    @NonNull
    String map;
    int lastTurn;
    String turnOrder;


    public GameEntity(CreateGameDTO createGameDTO) {
        UUID gameMasterId = UUID.randomUUID();
        this.setGameId(UUID.randomUUID());
        this.setGameMasterId(gameMasterId);
        this.setMap(createGameDTO.getMap());
        this.updateLastTurn(gameMasterId);
        Map<Integer, UUID> turnOrder = new HashMap<>();
        turnOrder.put(0, gameMasterId);
        this.setTurnOrder(mapToString(turnOrder));

    }

    public void update(DungonMap[][] map){
        this.setMap(Arrays.toString(map));
    }


    public void update(GameUpdateDTO gameUpdateDTO){
        this.setMap(gameUpdateDTO.getMap());
        this.setLastTurn(gameUpdateDTO.getLastTurn());
    }

    public void update(Map<Integer, UUID> turnOrder){
        String stringTurnOrder = convertWithStream(turnOrder);
        this.setTurnOrder(stringTurnOrder);
    }

    public String convertWithStream(Map<Integer, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

    public static String mapToString(Map<Integer, ?> map) {
        String ret = "";
        for (Integer key : map.keySet()) {
            ret += key + "=" + map.get(key) + ",";
        }
        return ret;
    }

    public GameEntity updateLastTurn(UUID playerId){
        Map<Integer,UUID> turnOrder = this.convertFromString(this.getTurnOrder());
        turnOrder.forEach((k,v) -> {
            if (v == playerId) {
                this.lastTurn = k;
            }
        });
        return this;
    }

    /**
     * converts turnOrder map string to Map<Integer, UUID>
     *
     * @param playerMapAsString
     * @return
     */
    public Map<Integer, UUID> convertFromString(String playerMapAsString) {
        playerMapAsString = playerMapAsString.replace("{", "");
        playerMapAsString = playerMapAsString.replace("}", "");
        Map<Integer, UUID> myMap = new HashMap<>();
        String[] Pairs = playerMapAsString.split(",");
        for (String pair: Pairs) {
            String[] keyValue = pair.split("=");
            myMap.put(Integer.valueOf(keyValue[0].replace(" ", "")),UUID.fromString(keyValue[1].replace(" ", "")));
        }

        return myMap;
    }

        public UUID getNextTurn(){
        int newTurn = this.getLastTurn() + 1;
        Map<Integer, UUID> turnOrder = this.convertFromString(this.getTurnOrder());

        if(turnOrder.size() > newTurn) {
            newTurn = 0;
        }
        return turnOrder.get(newTurn);
    }


}
