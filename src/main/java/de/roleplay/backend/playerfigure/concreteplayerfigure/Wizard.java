package de.roleplay.backend.playerfigure.concreteplayerfigure;

import de.roleplay.backend.playerfigure.PlayerFigure;
import de.roleplay.backend.playerfigure.concreteplayerfigure.talent.TalentWizard;
import de.roleplay.backend.playerfigure.race.Race;

public class Wizard extends PlayerFigure {

    private TalentWizard talent;

    public Wizard(String name, Race race) {
        super(name, race);
        setHealthPoints(8);
        setAmorClass(10);
    }
}
