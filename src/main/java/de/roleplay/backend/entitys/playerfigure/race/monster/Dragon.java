package de.roleplay.backend.entitys.playerfigure.race.monster;

import de.roleplay.backend.entitys.playerfigure.race.Race;

public class Dragon extends Race {
    public Dragon() {
        setStrength(getStrength()+30);
        setConstitution(getConstitution()+25);
        setWisdom(getWisdom()+13);
        setIntelligence(getIntelligence()+18);
    }
}
