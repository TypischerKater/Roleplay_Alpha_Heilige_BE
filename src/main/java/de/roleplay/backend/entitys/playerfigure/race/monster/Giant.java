package de.roleplay.backend.entitys.playerfigure.race.monster;

import de.roleplay.backend.entitys.playerfigure.race.Race;

public class Giant extends Race {
    public Giant() {
        setStrength(getStrength()+27);
        setConstitution(getConstitution()+22);
        setWisdom(getWisdom()+16);
        setIntelligence(getIntelligence()+12);
    }
}
