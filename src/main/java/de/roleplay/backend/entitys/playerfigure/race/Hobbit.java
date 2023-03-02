package de.roleplay.backend.entitys.playerfigure.race;

public class Hobbit extends Race {
    public Hobbit() {
        setConstitution(getConstitution()+1);
        setWisdom(getWisdom()+2);
        setDexterity(getDexterity()+2);
    }
}
