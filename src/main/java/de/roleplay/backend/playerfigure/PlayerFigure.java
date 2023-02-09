package de.roleplay.backend.playerfigure;

import lombok.Data;

@Data
public abstract class PlayerFigure {
    private String name;
    private CharacterClass characterClass;
    private int lifePoints;
    private int intelligence;
    private int strength;
    private int constitution;
    private int wisdom;
    private int skill;

    public PlayerFigure(String name) {
        setName(name);
    }
}
