package de.roleplay.backend.entitys.playerfigure.concreteplayerfigure;

import de.roleplay.backend.entitys.playerfigure.PlayerFigure;
import de.roleplay.backend.entitys.playerfigure.race.Race;
import de.roleplay.backend.entitys.playerfigure.concreteplayerfigure.talent.TalentFighter;

public class Fighter extends PlayerFigure {

    private TalentFighter talent;

    public Fighter(String name, Race race) {
        super(name, race);
        setHealthPoints(10);
        setAmorClass(13);
    }
}
