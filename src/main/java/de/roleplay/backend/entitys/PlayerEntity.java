package de.roleplay.backend.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
@Entity
@Data
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {
    @Id
    UUID playerId;
    @NonNull
    String username;
    @NonNull
    UUID gameId;
    @NonNull
    CharacterClass characterClass;
    @NonNull
    CharacterRace characterRace;
    @NonNull
    int healthPoints;
    @NonNull
    int intelligence;
    @NonNull
    int strength;
    @NonNull
    int constitution;
    @NonNull
    int wisdom;
    @NonNull
    int dexterity;
}
