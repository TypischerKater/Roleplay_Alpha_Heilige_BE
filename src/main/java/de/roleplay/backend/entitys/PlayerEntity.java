package de.roleplay.backend.entitys;

import de.roleplay.backend.DTOs.CreatePlayerDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Data
@Table(name = "player")
@NoArgsConstructor
public class PlayerEntity {
    @Id
    UUID playerId;
    @NonNull
    String username;
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "posX", column = @Column(name = "PosX")),
            @AttributeOverride(name = "posY", column = @Column(name = "PosY"))
    })
    Coordinates coordinates;
    Boolean isRested;

    public PlayerEntity(CreatePlayerDTO createPlayerDTO){
        this.setUsername(createPlayerDTO.getUsername());
        this.setPlayerId(UUID.randomUUID());
        this.setGameId(createPlayerDTO.getGameId());
        this.setHealthPoints(createPlayerDTO.getHealthPoints());
        this.setWisdom(createPlayerDTO.getWisdom());
        this.setStrength(createPlayerDTO.getStrength());
        this.setDexterity(createPlayerDTO.getDexterity());
        this.setConstitution(createPlayerDTO.getConstitution());
        this.setIntelligence(createPlayerDTO.getIntelligence());
        this.setCharacterClass(createPlayerDTO.getCharacterClass());
        this.setCharacterRace(createPlayerDTO.getCharacterRace());
        this.setIsRested(true);
    }

    public void updateCoordinates(Coordinates coordinates){
     this.coordinates = coordinates;
    }
}
