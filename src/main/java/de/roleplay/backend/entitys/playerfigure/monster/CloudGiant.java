package de.roleplay.backend.entitys.playerfigure.monster;

import de.roleplay.backend.entitys.playerfigure.race.Race;
import de.roleplay.backend.entitys.playerfigure.race.monster.Giant;

public class CloudGiant extends Monster {
    public CloudGiant(String name, Race race) {
        super(name, race);
        setRace(new Giant());
        setHealthPoints(200);
        setAmorClass(14);
    }
}
