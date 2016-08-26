package com.neub.game;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;

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
public class GameFrame {

    static int windowX = 800;
    static int windowY = 500;

    public GameFrame() {

        try {

            JFrame gameFrame = new JFrame();
            GameLogic gameLogic = new GameLogic();
            Thread gameThread = new Thread(gameLogic);
            gameThread.start();
            gameFrame.add(gameLogic);
            gameFrame.addKeyListener(gameLogic);
            gameFrame.addMouseListener(gameLogic);

            gameFrame.setTitle("Coin Collect");
            gameFrame.setBackground(new Color(255, 250, 205));
            gameFrame.setSize(windowX, windowY);
            gameFrame.setVisible(true);
            gameFrame.setResizable(false);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setLocationRelativeTo(null);// center 

        } catch (HeadlessException e) {

            System.out.println(e.getMessage());
        }

    }

}
