package com.neub.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Shakil Ahmed Munna 
 * @author Md Mahadi Hasan Nahid
 * 
 * North East University Bangladesh (NEUB)
 * nahid@neub.edu.bd 
 * @version 1.0
 * 
 */
public class GameLogic extends JPanel
        implements KeyListener, MouseListener, MouseMotionListener, Runnable {

    int RyDirection;
    private int RxDirection;
    public int w = 33;
    public int h = 33;
    private Image dbImage;
    private Graphics dbg;
    int x, y, xDirection, yDirection;
    int rx, ry, rw, rh;
    int p1 = 0;
    int p2 = 0;
    boolean GameStarted = false;
    public String pc;

    public GameLogic() {

    }

    @Override
    public void run() {
        try {

            while (true) {
                move();

                Thread.sleep(3);
            }

        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);

    }

    // random coin
    Random c1 = new Random();
    int cx = c1.nextInt(440 + 8);

    Random c2 = new Random();
    int cy = c2.nextInt(440 + 8);

    Rectangle c = new Rectangle(cx, cy, 20, 20);

    // for menu button
    Rectangle btnStart = new Rectangle(195, 140, 100, 25);
    Rectangle btnQuit = new Rectangle(195, 200, 100, 25);

    // For player
    Rectangle r1 = new Rectangle(132, 82, 33, 33);
    Rectangle r2 = new Rectangle(122, 185, 33, 33);

	// red line
    // make a wall ----->>>
    Rectangle wa1 = new Rectangle(78, 232, 222, 17);

    @Override
    public void paintComponent(Graphics g) {
        if (!GameStarted) {

            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.setColor(Color.BLACK);
            g.drawString("Coin Collect", 165, 100);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(btnStart.x, btnStart.y, btnStart.width, btnStart.height);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.setColor(Color.BLACK);
            g.drawString("Start Game", btnStart.x + 17, btnStart.y + 17);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(btnQuit.x, btnQuit.y, btnQuit.width, btnQuit.height);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.setColor(Color.BLACK);
            g.drawString("Quit Game", btnQuit.x + 17, btnQuit.y + 17);

        } else {

            g.setColor(Color.black); // player one color
            g.fillOval(r1.x, r1.y, r1.width, r1.height); // player one
            repaint();
            x = r2.x;
            y = r2.y;
            w = r2.width;
            h = r2.height;
            // ============== ( p 2 ) ==================

            int cp1 = 122;
            int cp2 = 212;
            int cp3 = 12;
            g.setColor(new Color(cp1, cp2, cp3)); // player two color
            g.fillOval(x, y, w, h); // player two
            // ----------------------------->>>

            g.setColor(Color.orange); // coin color
            g.fillOval(c.x, c.y, c.width, c.height); // coin

            if (p1 >= 5) {
                g.setFont(new Font("Arial", Font.BOLD, 15));
                g.setColor(new Color(255, 20, 147)); // player one coin count
                // color
                g.drawString("Player One : " + p1, 22, 22);
                g.setColor(Color.black);
                g.fillRect(8, 12, 10, 10);

            }
            if (p2 >= 5) {
                g.setFont(new Font("Arial", Font.BOLD, 15));
                g.setColor(new Color(255, 20, 147)); // player two coin count
                // color

                g.drawString("Player Two : " + p2, 380, 22);
                g.setColor(Color.blue);
                g.fillRect(365, 12, 10, 10);

            }

            if (p1 >= 100) {
                g.setColor(Color.PINK);
                g.drawString(" Player One Win  ", 180, 210);// System.exit(0);
                r1.width = 0;
                r1.height = 0;
                r2.width = 0;
                r2.height = 0;
                p1 = 100;
            }

            if (p2 >= 100) {
                g.setFont(new Font("Arial", Font.BOLD, 15));

                g.setColor(Color.PINK);
                g.drawString(" Player Two Win  ", 180, 210);// System.exit(0);
                r1.width = 0;
                r1.height = 0;
                r2.width = 0;
                r2.height = 0;
                p2 = 100;

            }

        }

        if (r1.intersects(c)) { // player 1 and coin intersects here
            p1 += 5;
            c.x = c1.nextInt(440 + 8);
            c.y = c2.nextInt(440 + 8);

            r1.width++;
            r1.height++;
            // System.out.println(r1.width + " " + r1.height);
            // System.out.println("player one : " + p1);

        }

        if (r2.intersects(c)) { // player 1 and coin intersects here

            p2 += 5;
            c.x = c1.nextInt(440 + 8);
            c.y = c2.nextInt(440 + 8);

            r2.width++;
            r2.height++;
            // System.out.println("player two : " + p2);

        }

        repaint();

    }

    public void move() {

        r2.x += xDirection;
        r2.y += yDirection;

        r1.x += RxDirection;
        r1.y += RyDirection;

        if (r2.x <= 0) {
            r2.x = 0;
        } else {
            r2.x += -5;
        }
        if (r2.x >= GameFrame.windowX - 50) {
            r2.x = GameFrame.windowX - 50;
        } else {
            r2.x += 5;
        }
        if (r2.y <= 0) {
            r2.y = 0;
        } else {
            r2.y += -5;
        }
        if (r2.y >= GameFrame.windowY - 50) {
            r2.y = GameFrame.windowY - 50;
        } else {
            r2.y += 5;
        }

        // --------------
        if (r1.x <= 0) {
            r1.x = 0;
        } else {
            r1.x += -5;
        }
        if (r1.x >= GameFrame.windowX - 50) {
            r1.x = GameFrame.windowX - 50;
        } else {
            r1.x += 5;
        }
        if (r1.y <= 0) {
            r1.y = 0;
        } else {
            r1.y += -5;
        }
        if (r1.y >= 440) {
            r1.y = 440;
        } else {
            r1.y += +5;
        }
        // ------------------------

    }

    public void setXdir(int xDir) {
        xDirection = xDir;
    }

    public void setYdir(int yDir) {
        yDirection = yDir;
    }
    // ===========================================================

    public void setRXdir(int xDir) {
        RxDirection = xDir;
    }

    public void setRYdir(int yDir) {
        RyDirection = yDir;
    }

    // =============================================================
    @Override
    public void keyPressed(KeyEvent e) {

        int KeyCode = e.getKeyCode();

        if (KeyCode == e.VK_LEFT) {
            /*
             * if (x <= 0) { x = 0; } else { x += -5; }
             * 
             * System.out.println("l");
             */
            setXdir(-1);
        }

        if (KeyCode == e.VK_RIGHT) {
            /*
             * if (x >= 460) { x = 460; } else { x += 5; }
             * 
             * System.out.println("R");
             */
            setXdir(+1);
        }

        if (KeyCode == e.VK_UP) {
            /*
             * if (y <= 30) { y = 0; } else { y += -5; }
             */
            setYdir(-1);

        }
        if (KeyCode == e.VK_DOWN) {
            /*
             * if (y >= 440) { y = 440; } else { y += +5; }
             */

            setYdir(+1);
        }
        // ====================================

        if (KeyCode == e.VK_E) { // for up
			/*
             * if (y >= 440) { y = 440; } else { y += +5; }
             */

            setRYdir(-1);
        }

        if (KeyCode == e.VK_X) { // for down
			/*
             * if (y >= 440) { y = 440; } else { y += +5; }
             */

            setRYdir(+1);
        }

        if (KeyCode == e.VK_S) { // for left
			/*
             * if (x <= 0) { x = 0; } else { x += -5; }
             * 
             * System.out.println("l");
             */
            setRXdir(-1);
        }

        if (KeyCode == e.VK_F) { // for right
			/*
             * if (x >= 460) { x = 460; } else { x += 5; }
             * 
             * System.out.println("R");
             */
            setRXdir(+1);
        }

        // ========================================
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int Keycode = e.getKeyCode();

        if (Keycode == e.VK_LEFT) {
            setXdir(0);
        }

        if (Keycode == e.VK_RIGHT) {
            setXdir(0);
        }

        if (Keycode == e.VK_UP) {
            setYdir(0);
        }
        if (Keycode == e.VK_DOWN) {
            setYdir(0);
        }

        if (Keycode == e.VK_E) {
            setRYdir(0);
            System.out.println("here");
        }

        if (Keycode == e.VK_X) {
            setRYdir(0);
        }
        if (Keycode == e.VK_S) {
            setRXdir(0);
        }

        if (Keycode == e.VK_F) {
            setRXdir(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    // ======================== Mouse ========================//
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mx > 195 && mx < 195 + btnStart.width && my > 155 && my < 155 + btnStart.height) {
            GameStarted = true;
            // System.out.println("hola");
        }

        if (mx > 195 && mx < 195 + btnQuit.width && my > 200 + 17 && my < 200 + 17 + btnQuit.height) {
            System.exit(0);
            // System.out.println("exit");
        }

    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
