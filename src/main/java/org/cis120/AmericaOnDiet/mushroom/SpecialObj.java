package org.cis120.AmericaOnDiet.mushroom;

import java.awt.*;

public abstract class SpecialObj {
    private int number;

    public static int avoid(int y, int height, int counter, int counterLimit) {
        if (counter < counterLimit) {
            if (y - height > 0) {
                return y - 100;
            } else if (y + height * 2 < GameCourt.COURT_HEIGHT) {
                return y + height;
            }
        }
        return y;
    }

    public abstract int moveQuicker(int x, int width, int vx, int random);

}
