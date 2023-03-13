package de.roleplay.backend.entitys.playerfigure.race.monster;

import de.roleplay.backend.entitys.playerfigure.race.Race;

public class Elemental extends Race {
    public Elemental() {
        setStrength(getStrength()+17);
        setConstitution(getConstitution()+15);
        setWisdom(getWisdom()+13);
        setIntelligence(getIntelligence()+12);
    }
}
