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
public class Marshmallow extends GameObj {
    public static final int SIZE = 80;
    private static int initPosX = GameCourt.COURT_WIDTH - SIZE;
    private static int initPosY = (int) (Math.random() * (GameCourt.COURT_HEIGHT - (SIZE) * 2)
            + SIZE);
    private static int initVelX = (int) (Math.random() * 3 + 6 + GameCourt.wave * 2) * -1;
    private static int initVelY = 0;
    private static int number = 3;
    private static int count = 0;

    public static final String IMG_ENEMY_FILE = "files/marshmallow.png";

    private static BufferedImage img;

    public Marshmallow(int courtWidth, int courtHeight, int x, int y, int vx, int vy) {
        super(initVelX, initVelY, initPosX, initPosY, SIZE, SIZE, courtWidth, courtHeight);

        this.initPosX = x;
        this.initPosY = y;
        this.initVelX = vx;
        this.initVelY = vy;

        try {
            if (img == null) {
                img = ImageIO.read(new File("files/marshmallow.png"));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }

    public int moveQuicker(int x, int width, int vx, int random) {
        if (x - (vx + 5) > 50 && random == number && x < (GameCourt.COURT_WIDTH / 4 * 3)
                && count % 10 == 0) {
            return vx - 1;
        }
        count++;
        return vx;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}