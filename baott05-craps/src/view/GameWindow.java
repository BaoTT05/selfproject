/**
 * Author: Bao Trinh
 * Course: TCSS 305
 * Assignment: 6 - Game of Craps
 */
package view;

import model.Game;
import model.Sound;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Represents the main window of the Game Of Craps.
 * This class initiates all game components, handles actions of the user and manages the game flow.
 */
public class GameWindow extends JFrame implements ActionListener {
    /**
     * A UI element that represents the current roll of the dice.
     */
    private final CurrentRoll currentRoll;

    /**
     * A UI element displaying and managing the user's bank balance.
     */
    private final Bank bank;

    /**
     * A UI element displaying and managing the user's betting amount.
     */
    private final Bet bet;

    /**
     * A UI element showing the total wins.
     */
    private final WinTotals winTotals;

    /**
     * A UI element allowing the user to roll the dice.
     */
    private final RollDice rollDice;

    /**
     * A button user can press to play the game again.
     */
    private final JButton playAgain;

    /**
     * A label displaying messages to the user.
     */
    private final JLabel message;

    /**
     * Menu item to start the game.
     */
    private final JMenuItem startMenuItem;

    /**
     * Menu item to reset the game.
     */
    private final JMenuItem resetMenuItem;

    /**
     * Sound played when the dice is rolled.
     */
    private final Sound diceRollSound;

    /**
     * Sound played when the player loses.
     */
    private final Sound playerLooseSound;

    /**
     * Sound played when the player wins.
     */
    private final Sound playerWinSound;

    /**
     * GameWindow constructor.
     *
     * @throws HeadlessException
     */
    public GameWindow() throws HeadlessException {
        setTitle("Game Of Craps");
        setSize(720, 480);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");

        startMenuItem = new JMenuItem("Start");
        resetMenuItem = new JMenuItem("Reset");
        /**
         * Menu item to exit the game.
         */
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        gameMenu.add(startMenuItem);
        gameMenu.add(resetMenuItem);
        gameMenu.add(exitMenuItem);


        JMenu helpMenu = new JMenu("Help");

        /**
         * Menu item showing about information.
         */
        JMenuItem aboutMenuItem = new JMenuItem("About");
        /**
         * Menu item showing rules for playing the game.
         */
        JMenuItem rulesMenuItem = new JMenuItem("Rules");
        /**
         * Menu item showing keyboard shortcuts for various operations.
         */
        JMenuItem shortcutsMenuItem = new JMenuItem("Shortcuts");

        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);
        helpMenu.add(shortcutsMenuItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        currentRoll = new CurrentRoll();
        currentRoll.setBorder(new TitledBorder("Current Roll"));

        bank = new Bank(this);
        bank.setBorder(new TitledBorder("Bank"));

        bet = new Bet(this);
        bet.setBorder(new TitledBorder("Bet"));

        winTotals = new WinTotals();
        winTotals.setBorder(new TitledBorder("Win Totals"));
        winTotals.setPreferredSize(new Dimension(250, 100));

        rollDice = new RollDice(this);

        message = new JLabel();
        message.setForeground(Color.blue);

        playAgain = new JButton("Play Again");
        playAgain.addActionListener(this);
        playAgain.setMnemonic('S');

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 25));
        panel.add(message);
        panel.add(playAgain);

        add(rollDice);
        add(currentRoll);
        add(bank);
        add(panel);
        add(winTotals);
        add(bet);

        startMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        resetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));

        startMenuItem.addActionListener(this);
        resetMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);
        rulesMenuItem.addActionListener(this);
        shortcutsMenuItem.addActionListener(this);

        diceRollSound = new Sound(new File("resource/dice_roll.wav"));
        playerLooseSound = new Sound(new File("resource/player_loose.wav"));
        playerWinSound = new Sound(new File("resource/player_win.wav"));

        reset();
        update();
    }

    /**
     * Performs associated action when an action event occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "Set Bank" -> {
                    if (bank.isValidAmount() && bank.getAmount() > 0) {
                        Game.setBankAmount(bank.getAmount());
                        bank.setEnabled(false);
                        bet.setEnabled(true);
                    } else
                        JOptionPane.showMessageDialog(this, "Please set valid amount greater than 0.");
                }
                case "Roll Dice" -> {
                    diceRollSound.play();
                    Game.rollDice();
                    update();

                    if (Game.getWinState() == 1)
                        playerWin();
                    if (Game.getWinState() == -1)
                        houseWin();
                }
                case "Play Again" -> {
                    playAgain();
                    bank.setEnabled(false);
                    bet.setEnabled(true);
                }
                case "+$1" -> Game.setBetAmount(Game.getBetAmount() + 1);
                case "+$5" -> Game.setBetAmount(Game.getBetAmount() + 5);
                case "+$10" -> Game.setBetAmount(Game.getBetAmount() + 10);
                case "+$50" -> Game.setBetAmount(Game.getBetAmount() + 50);
                case "+$100" -> Game.setBetAmount(Game.getBetAmount() + 100);
                case "+$500" -> Game.setBetAmount(Game.getBetAmount() + 500);
            }
        } else if (e.getSource() instanceof JMenuItem) {
            switch (e.getActionCommand()) {
                case "Start" -> {
                    if (bank.isValidAmount() && bank.getAmount() > 0 && bet.isValidAmount() && bet.getAmount() >= 0) {
                        if (bet.getAmount() <= bank.getAmount())
                            startGame();
                        else
                            JOptionPane.showMessageDialog(this, "Bet amount must not exceed bank amount.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Please set valid bank amount and valid bet amount before playing.");
                    }
                }
                case "Reset" -> reset();
                case "Exit" -> {
                    int option = JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (option == 0)
                        System.exit(0);
                }
                case "About" -> {
                    String about = "Name: Bao Trinh\nApp Version: 1.0.0\nJava Version: 21";
                    JOptionPane.showMessageDialog(this, about, "About", JOptionPane.INFORMATION_MESSAGE);
                }
                case "Rules" -> {
                    String rules = """
                            A player rolls two dice, the sum of the dices are calculated.
                            The first roll:
                            \t* If the sum is 7 or 11 on the first roll the player wins.
                            \t* If the sum is 2, 3 or 12 the player loses, the house wins.
                            \t* If the sum is 4, 5, 6, 8, 9, or 10, that sum becomes player's point.
                            Continue rolling given the player's point
                            Now the player must roll the sum equal to player's point before rolling
                             a 7 in order to win.
                            If they roll a 7 before rolling the sum equal to player's point that they
                             got on the first roll the player loses, the house wins.""";
                    JOptionPane.showMessageDialog(this, rules, "Rules", JOptionPane.INFORMATION_MESSAGE);
                }
                case "Shortcuts" -> {
                    String shortcuts = "CTRL + S: Start Game\nCTRL + R: Reset\nCTRL + A: About\nALT + A: Roll Dice\nALT + S: Play Again";
                    JOptionPane.showMessageDialog(this, shortcuts, "Shortcuts", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        update();
    }

    /**
     * Starts the game.
     */
    private void startGame() {
        Game.setBetAmount(bet.getAmount());
        rollDice.setEnabled(true);
        bet.setEnabled(false);

        startMenuItem.setEnabled(false);
        resetMenuItem.setEnabled(true);

        Game.setBankAmount(Game.getBankAmount() - Game.getBetAmount());
    }

    /**
     * Update all UI elements text
     */
    public void update() {
        bank.update();
        bet.update();
        currentRoll.update();
        rollDice.update();
        winTotals.update();
    }

    /**
     * Resets the game to its initial state.
     */
    private void reset() {
        playAgain();
        Game.reset();
        bank.setEnabled(true);
    }

    /**
     * Restarts the game after a win or loss.
     */
    private void playAgain() {
        rollDice.setEnabled(false);
        playAgain.setEnabled(false);
        bet.setEnabled(false);
        resetMenuItem.setEnabled(false);
        startMenuItem.setEnabled(true);
        Game.playAgain();
        message.setText("");
    }

    /**
     * Conducts actions after the player wins.
     */
    private void playerWin() {
        playerWinSound.play();
        JOptionPane.showMessageDialog(this, "Player Won", "Game End", JOptionPane.INFORMATION_MESSAGE);

        Game.setPlayerWinCount(Game.getPlayerWinCount() + 1);
        playAgain.setEnabled(true);
        rollDice.setEnabled(false);
        message.setText("Winner!");

        Game.setBankAmount(Game.getBankAmount() + Game.getBetAmount() * 2);
    }

    /**
     * Conducts actions after the house wins.
     */
    private void houseWin() {
        playerLooseSound.play();
        JOptionPane.showMessageDialog(this, "House Won", "Game End", JOptionPane.INFORMATION_MESSAGE);

        Game.setHouseWinCount(Game.getHouseWinCount() + 1);
        playAgain.setEnabled(true);
        rollDice.setEnabled(false);
        message.setText("Looser!");

        if (bank.getAmount() == 0) {
            JOptionPane.showMessageDialog(this, "Bank is empty, session over.");

            playAgain.setEnabled(false);
        }
    }
}
