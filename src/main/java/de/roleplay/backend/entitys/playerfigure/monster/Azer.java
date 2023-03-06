package de.roleplay.backend.entitys.playerfigure.monster;

import de.roleplay.backend.entitys.playerfigure.race.Race;
import de.roleplay.backend.entitys.playerfigure.race.monster.Elemental;

public class Azer extends Monster {
    public Azer(String name, Race race) {
        super(name, race);
        setRace(new Elemental());
        setHealthPoints(39);
        setAmorClass(17);
    }
}
