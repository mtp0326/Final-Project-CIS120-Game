package org.cis120.AmericaOnDiet.mushroom;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a square of a specified color.
 */
public class Square extends GameObj {
    public static final int SIZE = 100;
    public static final int INIT_POS_X = 50;
    public static final int INIT_POS_Y = (GameCourt.COURT_HEIGHT - SIZE) / 2;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    public static final String IMG_US_FILE = "files/USA.png";

    private static BufferedImage img;

    /**
     * Note that, because we don't need to do anything special when constructing
     * a Square, we simply use the superclass constructor called with the
     * correct parameters.
     */
    public Square(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_US_FILE));
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