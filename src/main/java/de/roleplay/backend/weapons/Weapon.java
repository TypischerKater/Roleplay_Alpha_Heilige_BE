package de.roleplay.backend.weapons;

import lombok.Data;

@Data
public abstract class Weapon {

    private int damageDice;
    private Modifier modifier;
    private int maxUsage;
}
