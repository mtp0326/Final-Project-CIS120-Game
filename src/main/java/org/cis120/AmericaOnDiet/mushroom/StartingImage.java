package org.cis120.AmericaOnDiet.mushroom;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StartingImage extends GameObj {

    public static int INIT_POS_X = 0;
    public static int INIT_POS_Y = 0;
    public static int INIT_VEL_X = 0;
    public static int INIT_VEL_Y = 0;

    public static final String IMG_START_FILE = "files/StartingImage.png";
    private static BufferedImage startImg;

    public StartingImage(int courtWidth, int courtHeight, int x, int y, int vx, int vy) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, 1200, 700, courtWidth, courtHeight);

        this.INIT_POS_X = x;
        this.INIT_POS_Y = y;
        this.INIT_VEL_X = vx;
        this.INIT_VEL_Y = vy;

        try {
            if (startImg == null) {
                startImg = ImageIO.read(new File("files/StartingImage.png"));
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
        g.drawImage(startImg, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }

}
