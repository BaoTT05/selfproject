/**
 * Author: Bao Trinh
 * Course: TCSS 305
 * Assignment: 6 - Game of Craps
 */
package view;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class represents a bet in the application.
 */
public class Bet extends JPanel {
    private final JTextField amount;
    private final JButton d1;
    private final JButton d5;
    private final JButton d10;
    private final JButton d50;
    private final JButton d100;
    private final JButton d500;

    /**
     * Constructor for the Bet class.
     * @param listener the action listener for the bet buttons
     */
    public Bet(ActionListener listener) {
        super(new GridLayout(7, 1, 0, 5));
        amount = new JTextField("0", 10);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("$"));
        panel.add(amount);

        add(panel);

        d1 = new JButton("+$1");
        d5 = new JButton("+$5");
        d10 = new JButton("+$10");
        d50 = new JButton("+$50");
        d100 = new JButton("+$100");
        d500 = new JButton("+$500");

        d1.addActionListener(listener);
        d5.addActionListener(listener);
        d10.addActionListener(listener);
        d50.addActionListener(listener);
        d100.addActionListener(listener);
        d500.addActionListener(listener);

        add(d1);
        add(d5);
        add(d10);
        add(d50);
        add(d100);
        add(d500);
    }

    /**
     * Enable or disable the bet.
     * @param enabled true to enable, false to disable
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        d1.setEnabled(enabled);
        d5.setEnabled(enabled);
        d10.setEnabled(enabled);
        d50.setEnabled(enabled);
        d100.setEnabled(enabled);
        d500.setEnabled(enabled);

        amount.setEnabled(enabled);
    }

    /**
     * Get the amount of the bet.
     * @return the amount of the bet
     */
    public int getAmount() {
        return Integer.parseInt(amount.getText());
    }

    /**
     * Update bet amount
     */
    public void update() {
        amount.setText(Integer.toString(Game.getBetAmount()));
    }

    /**
     * Check if the amount is valid.
     * @return true if the amount is valid, false otherwise
     */
    public boolean isValidAmount() {
        return !amount.getText().isBlank() && amount.getText().matches("\\d+");
    }
}
