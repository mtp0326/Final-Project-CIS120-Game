package org.cis120.AmericaOnDiet.mushroom;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 *
 * @version 2.1, Apr 2017
 */

import org.cis120.AmericaOnDiet.mushroom.GameCourt;
import org.cis120.AmericaOnDiet.mushroom.GameObj;

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a square of a specified color.
 */
public class EndingSquares extends GameObj {
    public static final int SIZE = 10;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    private static boolean[][] tempKillLocation = new boolean[80][120];

    private Color color;

    /**
     * Note that, because we don't need to do anything special when constructing
     * a Square, we simply use the superclass constructor called with the
     * correct parameters.
     */
    public EndingSquares(int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);

        this.color = color;
    }

    public int moveQuicker(int x, int width, int vx, int random) {
        return vx;
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < GameCourt.COURT_HEIGHT / 10 + 1; i++) {
            g.drawLine(0, i * 10, 1200, i * 10);
        }
        for (int j = 0; j < GameCourt.COURT_WIDTH / 10 + 1; j++) {
            g.drawLine(j * 10, 0, j * 10, 800);
        }
        tempKillLocation = GameCourt.getKillLocation();
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 120; j++) {
                if (tempKillLocation[i][j] == true) {
                    g.setColor(this.color);
                    g.fillRect(j * 10, i * 10, this.getWidth(), this.getHeight());
                }
            }
        }

    }
}