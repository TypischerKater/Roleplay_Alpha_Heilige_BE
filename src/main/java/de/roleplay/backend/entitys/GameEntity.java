package de.roleplay.backend.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {
    @Id
    UUID gameId;
    @NonNull
    UUID gameMasterId;
    @NonNull
    String map;
    @NonNull
    UUID lastTurn;
    @NonNull
    String turnOrder;
}
