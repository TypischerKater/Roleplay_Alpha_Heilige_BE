package de.roleplay.backend.playerfigure.concreteplayerfigure;

import de.roleplay.backend.playerfigure.PlayerFigure;
import de.roleplay.backend.playerfigure.race.Race;

public class Thief extends PlayerFigure {
    public Thief(String name, Race race) {
        super(name, race);
        setHealthPoints(8);
        setAmorClass(10);
    }
}
