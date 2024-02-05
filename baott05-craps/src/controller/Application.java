/**
 * Author: Bao Trinh
 * Course: TCSS 305
 * Assignment: 6 - Game of Craps
 */

package controller;

import view.*;
import javax.swing.*;

/**
 * This is the main class for the application.
 */
public class Application {
    /**
     * The main method of the application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a new GameWindow
            JFrame frame = new GameWindow();
            // Set the default close operation
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Make the window visible
            frame.setVisible(true);
        });
    }
}
