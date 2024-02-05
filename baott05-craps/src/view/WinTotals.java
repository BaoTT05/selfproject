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
 * This class represents the win totals in the application.
 */
public class WinTotals extends JPanel {
    private final JTextField playerWin;
    private final JTextField houseWin;

    /**
     * Constructor for the WinTotals class.
     */
    public WinTotals() {
        super(new GridLayout(2, 2, 0, 25));
        playerWin = new JTextField("0");
        houseWin = new JTextField("0");

        playerWin.setEditable(false);
        houseWin.setEditable(false);

        add(new JLabel("Player Win Total:"));
        add(playerWin);
        add(new JLabel("House Win Total:"));
        add(houseWin);
    }

    /**
     * Update win total values of player wins and house wins
     */
    public void update() {
        playerWin.setText(Integer.toString(Game.getPlayerWinCount()));
        houseWin.setText(Integer.toString(Game.getHouseWinCount()));
    }
}
