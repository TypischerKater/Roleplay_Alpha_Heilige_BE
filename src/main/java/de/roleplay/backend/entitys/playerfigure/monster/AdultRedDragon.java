package de.roleplay.backend.entitys.playerfigure.monster;

import de.roleplay.backend.entitys.playerfigure.race.monster.Dragon;
import de.roleplay.backend.entitys.playerfigure.race.Race;

public class AdultRedDragon extends Monster {
    public AdultRedDragon(String name, Race race) {
        super(name, race);
        setRace(new Dragon());
        setHealthPoints(256);
        setAmorClass(19);
    }
}
