package de.roleplay.backend.entitys.playerfigure.concreteplayerfigure;

import de.roleplay.backend.entitys.playerfigure.PlayerFigure;
import de.roleplay.backend.entitys.playerfigure.race.Race;
import de.roleplay.backend.entitys.playerfigure.concreteplayerfigure.talent.TalentWizard;

public class Wizard extends PlayerFigure {

    private TalentWizard talent;

    public Wizard(String name, Race race) {
        super(name, race);
        setHealthPoints(8);
        setAmorClass(10);
    }
}
