package de.roleplay.backend.entitys.playerfigure.concreteplayerfigure;

import de.roleplay.backend.entitys.playerfigure.PlayerFigure;
import de.roleplay.backend.entitys.playerfigure.race.Race;
import de.roleplay.backend.entitys.playerfigure.concreteplayerfigure.talent.TalentThief;

public class Thief extends PlayerFigure {

    private TalentThief talent;

    public Thief(String name, Race race) {
        super(name, race);
        setHealthPoints(8);
        setAmorClass(10);
    }
}
