package de.roleplay.backend.playerfigure.race;

public class Human extends Race {
    public Human() {
        setIntelligence(getIntelligence()+1);
        setStrength(getStrength()+1);
        setConstitution(getConstitution()+1);
        setWisdom(getWisdom()+1);
        setDexterity(getDexterity()+1);
    }
}
