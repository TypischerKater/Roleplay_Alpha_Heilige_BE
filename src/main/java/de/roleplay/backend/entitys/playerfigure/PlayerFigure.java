package de.roleplay.backend.entitys.playerfigure;

import de.roleplay.backend.entitys.playerfigure.race.Race;
import de.roleplay.backend.weapons.Weapon;
import lombok.Data;

@Data
public abstract class PlayerFigure {
    private String name;
    private Race race;
    private int healthPoints;
    private int amorClass;
    private Weapon weapon;

    public PlayerFigure(String name, Race race) {
        setName(name);
        setRace(race);
    }
}
