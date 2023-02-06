package de.roleplay.backend.spielfigur;

import lombok.Data;

@Data
public abstract class Spielfigur {
    private String name;
    private Charakterklasse charakterklasse;
    private int lebenspunkte;
    private int intelligenz;
    private int staerke;
    private int konstitution;
    private int weisheit;
    private int geschicklichkeit;

    public Spielfigur(String name) {
        setName(name);
    }
}
