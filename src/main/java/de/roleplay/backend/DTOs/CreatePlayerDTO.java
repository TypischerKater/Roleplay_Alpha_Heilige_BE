package de.roleplay.backend.DTOs;

import de.roleplay.backend.entitys.CharacterClass;
import de.roleplay.backend.entitys.CharacterRace;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreatePlayerDTO {
    private String username;
    private UUID gameId;
    private CharacterClass characterClass;
    private CharacterRace characterRace;
    private int healthPoints;
    private int strength;
    private int intelligence;
    private int constitution;
    private int wisdom;
    private int dexterity;
}
