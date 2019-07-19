

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * This file demonstrates various Layout managers that Swing uses to position
 * JComponents. The most important ones are FlowLayout (the default for JPanel),
 * GridLayout, and BorderLayout.
 * 
 * See http://docs.oracle.com/javase/tutorial/uiswing/layout/index.html
 * for more information
 */
public class Game implements Runnable {

    public void run() {
        JFrame frame = new JFrame("Battleship");
        Battleship battleship = new Battleship();
        frame.getContentPane().add(battleship);
        
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        battleship.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }

}
