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
 * This class represents the roll dice action in the application.
 */
public class RollDice extends JPanel {
    private final JTextField points;
    private final JButton rollDiceButton;

    /**
     * Constructor for the RollDice class.
     * @param listener the action listener for the roll dice button
     */
    public RollDice(ActionListener listener) {
        super(new GridLayout(2, 1, 0, 15));

        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(listener);
        rollDiceButton.setMnemonic('A');

        add(rollDiceButton);

        points = new JTextField(10);
        points.setEditable(false);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Point:"));
        panel.add(points);

        add(panel);
    }

    /**
     * Enable or disable the roll dice action.
     * @param enabled true to enable, false to disable
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        rollDiceButton.setEnabled(enabled);
    }

    /**
     * Update point value
     */
    public void update() {
        points.setText(Integer.toString(Game.getPoints()));
    }
}
