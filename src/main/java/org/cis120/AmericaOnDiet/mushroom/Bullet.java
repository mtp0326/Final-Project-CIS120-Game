package org.cis120.AmericaOnDiet.mushroom;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 *
 * @version 2.1, Apr 2017
 */

import org.cis120.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a circle of a specified color.
 */
public class Bullet extends GameObj {
    public static final int SIZEX = 100;
    public static final int SIZEY = 80;
    public static int INIT_POS_X = Square.INIT_POS_X + (Square.SIZE / 2);
    public static int INIT_POS_Y = Square.INIT_POS_Y;
    public static int INIT_VEL_X = 10;
    public static int INIT_VEL_Y = 0;

    public static final String IMG_ENEMY_FILE = "files/eagle.png";

    private static BufferedImage img;

    public Bullet(int courtWidth, int courtHeight, int x, int y, int vx, int vy) {
        super(
                INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZEX, SIZEY, courtWidth,
                courtHeight
        );

        this.INIT_POS_X = x;
        this.INIT_POS_Y = y;
        this.INIT_VEL_X = vx;
        this.INIT_VEL_Y = vy;

        try {
            if (img == null) {
                img = ImageIO.read(new File("files/eagle.png"));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public int moveQuicker(int x, int width, int vx, int random) {
        return vx;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}