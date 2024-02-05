/**
 * Author: Bao Trinh
 * Course: TCSS 305
 * Assignment: 6 - Game of Craps
 */
package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the current roll in the application.
 */
public class CurrentRoll extends JPanel {
    private final JTextField die1;
    private final JTextField die2;
    private final JTextField total;

    /**
     * Constructor for the CurrentRoll class.
     */
    public CurrentRoll() {
        super(new GridLayout(3, 2, 0, 10));

        die1 = new JTextField(10);
        die2 = new JTextField(10);
        total = new JTextField(10);

        die1.setEditable(false);
        die2.setEditable(false);
        total.setEditable(false);

        add(new JLabel("Die 1:"));
        add(die1);
        add(new JLabel("Die 2:"));
        add(die2);
        add(new JLabel("Total:"));
        add(total);
    }

    /**
     * Update values of die1, die2 and total
     */
    public void update() {
        this.die1.setText(Integer.toString(Game.getDie1Value()));
        this.die2.setText(Integer.toString(Game.getDie2Value()));
        this.total.setText(Integer.toString(Game.getTotalDieValue()));
    }
}
