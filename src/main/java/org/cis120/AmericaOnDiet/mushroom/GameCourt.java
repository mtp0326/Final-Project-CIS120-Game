package org.cis120.AmericaOnDiet.mushroom;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Square square; // the Black Square, keyboard control
    private static long startTime = System.currentTimeMillis();

    private static int hp = 1000;
    private static int score = 0;
    private static int highScore = 0;
    private static int eliminate = 0;
    public static int wave = 1;
    private static boolean waveFlag = false;
    private static int counter = 5;
    private boolean ended = false;

    private static ArrayList<GameObj> enemies = new ArrayList<>();
    private static ArrayList<GameObj> bullets = new ArrayList<>();
    private static ArrayList<GameObj> endings = new ArrayList<>();
    GameObj eSq = new EndingSquares(COURT_WIDTH, COURT_HEIGHT, Color.red);
    private static int[] enemyTime = { 11000, 2000, 1500, 3250, 6500 };
    private static boolean[][] killLocation = new boolean[80][120];
    private static long[] lastEnemy = { 0, 0, 0, 0, 0 };
    private static long[] newLastEnemy = { 0, 0, 0, 0, 0 };

    private static boolean playing = false; // whether the game is running
    private static boolean firstScreen = true;
    private JLabel status; // Current status text, i.e. "Running..."

    // public static final String IMG_ENDING_FILE = "files/ending.png";
    // private static BufferedImage endingImage;

    // Game constants
    public static final int COURT_WIDTH = 1200;
    public static final int COURT_HEIGHT = 800;
    public static final int SQUARE_VELOCITY = 10;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the
        // given INTERVAL. We register an ActionListener with this timer, whose
        // actionPerformed() method is called each time the timer triggers. We
        // define a helper method called tick() that actually does everything
        // that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        /// bullets are not simultaneous with square
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key
        // is pressed, by changing the square's velocity accordingly. (The tick
        // method below actually moves the square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    square.setVy(SQUARE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    square.setVy(-SQUARE_VELOCITY);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bullets.add(
                            new Bullet(
                                    COURT_WIDTH, COURT_HEIGHT,
                                    Square.INIT_POS_X + (Square.SIZE / 2), square.getPyNow(), 10, 0
                            )
                    );
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    endings.removeAll(endings);
                    playing = true;
                    firstScreen = false;
                }
            }

            public void keyReleased(KeyEvent e) {
                square.setVx(0);
                square.setVy(0);
            }
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        square = new Square(COURT_WIDTH, COURT_HEIGHT);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        killLocation = new boolean[80][120];
        eSq = new EndingSquares(COURT_WIDTH, COURT_HEIGHT, Color.red);
        lastEnemy = newLastEnemy;
        counter = 0;
        hp = 1000;
        score = 0;
        eliminate = 0;
        wave = 1;
        waveFlag = false;
        ended = false;

        playing = true;
        status.setText(
                "HighScore: " + highScore + " | Score: " + score + " | HP: " + hp
                        + " | Kills/Wave: " + eliminate + "/" + wave
        );

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    public static void spawn(int number) {
        switch (number) {
            case 0:
                enemies.add(
                        new Cake(
                                COURT_WIDTH, COURT_HEIGHT, COURT_WIDTH - Cake.SIZE,
                                (int) (Math.random() * (COURT_HEIGHT - (Cake.SIZE) * 2)
                                        + Cake.SIZE),
                                (int) (Math.random() * 4 + 1 + wave * 2) * -1, 0
                        )
                );
                break;
            case 1:
                enemies.add(
                        new Donut(
                                COURT_WIDTH, COURT_HEIGHT, COURT_WIDTH - Donut.SIZE,
                                (int) (Math.random() * (COURT_HEIGHT - (Donut.SIZE) * 2)
                                        + Donut.SIZE),
                                (int) (Math.random() * 3 + 4 + wave * 2) * -1, 0
                        )
                );
                break;
            case 2:
                enemies.add(
                        new MandM(
                                COURT_WIDTH, COURT_HEIGHT, COURT_WIDTH - MandM.SIZE,
                                (int) (Math.random() * (COURT_HEIGHT - (MandM.SIZE) * 2)
                                        + MandM.SIZE),
                                (int) (Math.random() * 9 + 14 + wave * 2) * -1, 0
                        )
                );
                break;
            case 3:
                enemies.add(
                        new Marshmallow(
                                COURT_WIDTH, COURT_HEIGHT, COURT_WIDTH - Marshmallow.SIZE,
                                (int) (Math.random() * (COURT_HEIGHT - (Marshmallow.SIZE) * 2)
                                        + Marshmallow.SIZE),
                                (int) (Math.random() * 5 + 9 + wave * 2) * -1, 0
                        )
                );
                break;
            case 4:
                enemies.add(
                        new Pie(
                                COURT_WIDTH, COURT_HEIGHT, COURT_WIDTH - Pie.SIZE,
                                (int) (Math.random() * (COURT_HEIGHT - (Pie.SIZE) * 2) + Pie.SIZE),
                                (int) (Math.random() * 3 + 4 + wave * 2) * -1, 0
                        )
                );
                break;
        }
    }

    public static boolean[][] getKillLocation() {
        return killLocation;
    }

    public static ArrayList<GameObj> getEnemies() {
        return enemies;
    }

    public static void spawnTime() {
        for (int i = 0; i < 5; i++) {
            if ((System.currentTimeMillis() - startTime) >= lastEnemy[i] + enemyTime[i]) {
                spawn(i);
                lastEnemy[i] = (System.currentTimeMillis() - startTime);
            }
        }
    }

    public void resetWave() {
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        counter = 0;
        lastEnemy = newLastEnemy;
        if (hp > 750) {
            hp = 1000;
        } else {
            hp += 250;
        }
        status.setText(
                "HighScore: " + highScore + " | Score: " + score + " | HP: " + hp
                        + " | Kills/Wave: " + eliminate + "/" + wave
        );

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */

    void tick() {
        if (firstScreen) {
            playing = false;
            endings.add(new StartingImage(COURT_WIDTH, COURT_HEIGHT, 0, 0, 0, 0));
        }
        if (playing && !firstScreen) {
            spawnTime();
            int toEraseE = -1;
            for (GameObj en : enemies) {
                en.move();
                en.bounce(en.hitWall());
                if (en.getNumber() == 3 || en.getNumber() == 4) {
                    en.setVx(
                            en.moveQuicker(
                                    en.getPx(), en.getWidth(), en.getVx(), (int) Math.random() * 5
                            )
                    );
                }
                if (en.getPxNow() <= 50) {
                    hp -= en.getWidth();
                    status.setText(
                            "HighScore: " + highScore + " | Score: " + score + " | HP: " + hp
                                    + " | Kills/Wave: " + eliminate + "/" + wave
                    );
                    toEraseE = enemies.indexOf(en);
                }
                if (square.intersects(en)) {
                    score += en.getWidth();
                    eliminate++;
                    try {
                        killLocation[en.getPy() / 10][en.getPx() / 10] = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArrayIndexOutOfBoundsException();
                    }

                    toEraseE = enemies.indexOf(en);
                }
            }
            if (toEraseE != -1) {
                enemies.remove(toEraseE);
            }

            int toEraseB = -1;
            for (GameObj b : bullets) {
                b.move();
                b.bounce(b.hitWall());
                if (b.getPxNow() > (COURT_WIDTH - b.getWidth()) - 5) {
                    if (score >= 50) {
                        score -= 50;
                    }
                    status.setText(
                            "HighScore: " + highScore + " | Score: " + score + " | HP: " + hp
                                    + " | Kills/Wave: " + eliminate + "/" + wave
                    );
                    toEraseB = bullets.indexOf(b);
                }
            }
            if (toEraseB != -1) {
                bullets.remove(toEraseB);
            }
            square.move();

            toEraseE = -1;
            toEraseB = -1;
            for (GameObj b : bullets) {
                for (GameObj en : enemies) {
                    if (b.intersects(en)) {
                        if (counter < en.getCounterLimit()
                                && (en.getNumber() == 1 || en.getNumber() == 4)) {
                            en.setPy(
                                    en.avoid(
                                            en.getPy(), en.getHeight(), counter,
                                            en.getCounterLimit()
                                    )
                            );
                            counter++;
                        } else {
                            score += en.getWidth();
                            eliminate++;
                            killLocation[en.getPy() / 10][en.getPx() / 10] = true;
                            status.setText(
                                    "HighScore: " + highScore + " | Score: " + score + " | HP: "
                                            + hp
                                            + " | Kills/Wave: " + eliminate + "/" + wave
                            );
                            toEraseE = enemies.indexOf(en);
                            toEraseB = bullets.indexOf(b);
                        }
                    }
                }
            }
            if (toEraseE != -1) {
                enemies.remove(toEraseE);
                bullets.remove(toEraseB);
            }

            if (eliminate % 20 == 1) {
                waveFlag = true;
            }

            if (eliminate % 20 == 0 && eliminate != 0 && waveFlag) {
                wave++;
                waveFlag = false;
                resetWave();
            }

            if (hp <= 0) {
                if (score > highScore) {
                    highScore = score;
                    status.setText(
                            "GAMEOVER | NEW HIGHSCORE!: " + score + " | Kills/Wave: " + eliminate
                                    + "/" + wave + " | ^ Grid of where enemies died."
                    );
                } else {
                    status.setText(
                            "GAMEOVER | HighScore: " + highScore + " | Score: " + score
                                    + " | Kills/Wave: "
                                    + eliminate + "/" + wave + " | ^ Grid of where enemies died."
                    );
                }
                enemies = new ArrayList<>();
                bullets = new ArrayList<>();
                ended = true;
                playing = false;

            }
            // update the display
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        square.draw(g);
        for (GameObj en : enemies) {
            en.draw(g);
        }
        for (GameObj b : bullets) {
            b.draw(g);
        }
        for (GameObj e : endings) {
            e.draw(g);
        }
        if (ended) {
            eSq.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}