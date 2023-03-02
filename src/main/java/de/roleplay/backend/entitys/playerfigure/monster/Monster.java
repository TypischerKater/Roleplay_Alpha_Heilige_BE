package de.roleplay.backend.entitys.playerfigure.monster;

import de.roleplay.backend.entitys.playerfigure.PlayerFigure;
import de.roleplay.backend.entitys.playerfigure.race.Race;

import java.util.Random;

public class Monster extends PlayerFigure {
    public Monster(String name, Race race) {
        super(name, race);
    }

    public void move(int x, int y) {
        // TODO move on map
    }

    public void moveRandom() {
        int x = new Random().nextInt(100);
        int y = new Random().nextInt(100);
        move(x, y);
    }

    public void discover() {
        int a = new Random().nextInt(10);
        if (a >= 3) {
            // 70% chance
            // TODO follow player on map
        } else if (a >= 1) {
            // 20% chance
            // Do nothing
        } else {
            // 10% chance
            // TODO run away from player on map
        }
    }
}
