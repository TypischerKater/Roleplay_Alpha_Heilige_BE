package de.roleplay.backend.entitys.playerfigure.race;

public class Elf extends Race {
    public Elf() {
        setIntelligence(getIntelligence()+2);
        setWisdom(getWisdom()+1);
        setDexterity(getDexterity()+2);
    }
}