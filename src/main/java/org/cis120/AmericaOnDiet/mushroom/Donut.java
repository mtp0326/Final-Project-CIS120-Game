package org.cis120.AmericaOnDiet.mushroom;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 *
 * @version 2.1, Apr 2017
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a circle of a specified color.
 */
public class Donut extends GameObj {
    public static final int SIZE = 80;
    private static int initPosX = GameCourt.COURT_WIDTH - SIZE;
    private static int initPosY = (int) (Math.random() * (GameCourt.COURT_HEIGHT - (SIZE) * 2)
            + SIZE);
    private static int initVelX = (int) (Math.random() * 5 + 3 + GameCourt.wave * 2) * -1;
    private static int initVelY = 0;
    private static int number = 1;
    private static int counterLimit = 1;

    public static final String IMG_ENEMY_FILE = "files/donut.png";

    private static BufferedImage img;

    public Donut(int courtWidth, int courtHeight, int x, int y, int vx, int vy) {
        super(initVelX, initVelY, initPosX, initPosY, SIZE, SIZE, courtWidth, courtHeight);

        this.initPosX = x;
        this.initPosY = y;
        this.initVelX = vx;
        this.initVelY = vy;

        try {
            if (img == null) {
                img = ImageIO.read(new File("files/donut.png"));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }

    public int getCounterLimit() {
        return counterLimit;
    }

    public int moveQuicker(int x, int width, int vx, int random) {
        return vx;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}