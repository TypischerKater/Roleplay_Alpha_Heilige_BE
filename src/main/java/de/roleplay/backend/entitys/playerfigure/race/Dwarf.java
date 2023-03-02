package de.roleplay.backend.entitys.playerfigure.race;

public class Dwarf extends Race {
    public Dwarf() {
        setStrength(getStrength()+2);
        setConstitution(getConstitution()+2);
        setWisdom(getWisdom()+1);
    }
}
