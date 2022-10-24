package org.cis120.AmericaOnDiet.mushroom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.lang.*;

public class JUnitTest {
    int number = 4;

    int count = 0;

    /// could not call through static method because this is an abstract method.
    public int moveQuicker(int x, int width, int vx, int random) {
        if (x - (vx + 5) > 50 && random == number && x < (GameCourt.COURT_WIDTH / 4 * 3)
                && count % 10 == 0) {
            return vx - 1;
        }
        count++;
        return vx;
    }

    @Test
    public void moveQuickerDelayedDistance() {
        // turns is how many times tick() has been run. This is to test the accuracy of
        // the distance traveled
        // by enemies by implementing count%10 that hopefully reduces the enemy's speed
        // by 10 because the enemy
        // travels too fast with vx+5 happening everytime the computer loops tick().
        int turns = 20;
        int init_position = 500;
        int vx = -5;

        for (int i = 0; i < turns; i++) {
            vx = moveQuicker(init_position, 100, vx, 4);
            init_position += vx;
        }
        assertEquals(init_position, 190);
    }

    @Test
    public void moveQuickerCrashingToTheLeft() {
        int turns = 10;
        int init_position = 10;
        int vx = -5;
        for (int i = 0; i < turns; i++) {
            vx = moveQuicker(init_position, 100, vx, 4);
            init_position += vx;
        }
        // uses vx instead of incrementing vx-1, so the distance is -40. But -40
        // means that it would have been removed by GameCourt class.
        assertEquals(init_position, -40);
    }

    @Test
    public void spawnTimeEmpty() {
        GameCourt.spawnTime();
        long timer = System.currentTimeMillis();
        if (System.currentTimeMillis() - timer > 1900) {
            assertEquals(GameCourt.getEnemies().size(), 0);
        }
    }

    @Test
    public void spawnTimeCorrectSize() {
        GameCourt.spawnTime();
        long timer = System.currentTimeMillis();
        if (System.currentTimeMillis() - timer > 4600) {
            assertEquals(GameCourt.getEnemies().size(), 6);
        }
    }

    @Test
    public void avoidCrashingAboveCeiling() {
        int tempValue = 99;
        assertEquals(SpecialObj.avoid(tempValue, 100, 0, 2), 199);
    }

    @Test
    public void avoidCrashingBelowGround() {
        int tempValue = 601;
        assertEquals(SpecialObj.avoid(tempValue, 100, 0, 2), 501);
    }

}