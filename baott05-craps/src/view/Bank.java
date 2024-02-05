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
 * This class represents a bank in the application.
 */
public class Bank extends JPanel {
    private final JTextField amount;
    private final JButton setBankButton;

    /**
     * Constructor for the Bank class.
     * @param listener the action listener for the set bank button
     */
    public Bank(ActionListener listener) {
        super(new GridLayout(2, 1, 0, 15));
        amount = new JTextField("0", 10);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("$"));
        panel.add(amount);

        add(panel);

        setBankButton = new JButton("Set Bank");
        setBankButton.addActionListener(listener);

        add(setBankButton);
    }

    /**
     * Enable or disable the bank.
     * @param enabled true to enable, false to disable
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setBankButton.setEnabled(enabled);
        amount.setEnabled(enabled);
    }

    /**
     * Get the amount in the bank.
     * @return the amount in the bank
     */
    public int getAmount() {
        return Integer.parseInt(amount.getText());
    }

    /**
     * Update the bank amount
     */
    public void update() {
        amount.setText(Integer.toString(Game.getBankAmount()));
    }

    /**
     * Check if the amount is valid.
     * @return true if the amount is valid, false otherwise
     */
    public boolean isValidAmount() {
        return !amount.getText().isBlank() && amount.getText().matches("\\d+");
    }
}
