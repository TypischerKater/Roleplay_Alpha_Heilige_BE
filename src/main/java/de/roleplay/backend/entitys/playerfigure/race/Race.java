package de.roleplay.backend.entitys.playerfigure.race;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class Race {
    private int intelligence;
    private int strength;
    private int constitution;
    private int wisdom;
    private int dexterity;
}
