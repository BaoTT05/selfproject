/**
 * Author: Bao Trinh
 * Course: TCSS 305
 * Assignment: 6 - Game of Craps
 */
package model;

import java.util.Random;

public class Game {
    private static int bankAmount = 0;
    private static int betAmount = 0;
    private static int playerWinCount = 0;
    private static int houseWinCount = 0;
    private static int points = 0;
    private static int die1Value = 0;
    private static int die2Value = 0;
    private static int totalDieValue = 0;
    private static final Random random = new Random();
    private static boolean firstRoll = true;
    private static int winState = 0;

    public static void rollDice() {
        die1Value = random.nextInt(1, 7);
        die2Value = random.nextInt(1, 7);
        totalDieValue = die1Value + die2Value;

        if (firstRoll) {
            firstRoll = false;
            points = totalDieValue;

            if (totalDieValue == 7 || totalDieValue == 11)
                winState = 1;
            if (totalDieValue == 2 || totalDieValue == 3 || totalDieValue == 12)
                winState = -1;
        } else {
            if (totalDieValue == points)
                winState = 1;
            if (totalDieValue == 7)
                winState = -1;
        }
    }

    public static void reset() {
        bankAmount = 0;
        playerWinCount = 0;
        houseWinCount = 0;

        playAgain();
    }

    public static void playAgain() {
        points = 0;
        die1Value = 0;
        die2Value = 0;
        totalDieValue = 0;
        betAmount = 0;

        firstRoll = true;
        winState = 0;
    }

    public static int getBankAmount() {
        return bankAmount;
    }

    public static void setBankAmount(int bankAmount) {
        Game.bankAmount = bankAmount;
    }

    public static int getBetAmount() {
        return betAmount;
    }

    public static void setBetAmount(int betAmount) {
        Game.betAmount = betAmount;
    }

    public static int getPlayerWinCount() {
        return playerWinCount;
    }

    public static void setPlayerWinCount(int playerWinCount) {
        Game.playerWinCount = playerWinCount;
    }

    public static int getHouseWinCount() {
        return houseWinCount;
    }

    public static void setHouseWinCount(int houseWinCount) {
        Game.houseWinCount = houseWinCount;
    }

    public static int getPoints() {
        return points;
    }

    public static int getDie1Value() {
        return die1Value;
    }

    public static int getDie2Value() {
        return die2Value;
    }

    public static int getTotalDieValue() {
        return totalDieValue;
    }

    public static int getWinState() {
        return winState;
    }
}
