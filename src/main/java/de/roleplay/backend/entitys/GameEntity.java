package de.roleplay.backend.entitys;

import de.roleplay.backend.DTOs.CreateGameDTO;
import de.roleplay.backend.DTOs.GameUpdateDTO;
import de.roleplay.backend.DungonMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
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
        this.setLastTurn(-1);
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
}
