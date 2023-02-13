package de.roleplay.backend.playerfigure.race;

import lombok.Data;

@Data
public abstract class Race {
    private int intelligence;
    private int strength;
    private int constitution;
    private int wisdom;
    private int dexterity;

    public Race() {

    }
}
