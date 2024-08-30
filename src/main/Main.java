package main;

import javax.swing.*;
import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window =new JFrame("Thunder");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gamepanel gamePanel =new Gamepanel();
        window.add(gamePanel);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();

    }
}