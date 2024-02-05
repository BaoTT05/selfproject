package test;

import model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testBankAmount() {
        Game.setBankAmount(100);
        assertEquals(100, Game.getBankAmount());

        Game.setBankAmount(Game.getBankAmount() + 50);
        assertEquals(150, Game.getBankAmount());

        Game.setBankAmount(Game.getBankAmount() - 50);
        assertEquals(100, Game.getBankAmount());
    }

    @Test
    void testBetAmount() {
        Game.setBetAmount(20);
        assertEquals(20, Game.getBetAmount());
    }

    @Test
    void testDiceRolls() {
        for (int i = 0; i < 1000; i++) {
            Game.rollDice();
            assertTrue(Game.getDie1Value() >= 1 && Game.getDie1Value() <= 6);
            assertTrue(Game.getDie2Value() >= 1 && Game.getDie2Value() <= 6);
        }
    }

    @Test
    void testWinLoseScenarios() {
        Game.playAgain();
        Game.rollDice();
        if (Game.getTotalDieValue() == 7 || Game.getTotalDieValue() == 11) {
            assertEquals(1, Game.getWinState());
        }

        Game.playAgain();
        Game.rollDice();
        if (Game.getTotalDieValue() == 2 || Game.getTotalDieValue() == 3 || Game.getTotalDieValue() == 12) {
            assertEquals(-1, Game.getWinState());
        }

        Game.playAgain();
        Game.rollDice();
        int points = Game.getTotalDieValue();
        while (Game.getWinState() == 0) {
            Game.rollDice();
            if (Game.getTotalDieValue() == points) {
                assertEquals(1, Game.getWinState());
            }
        }

        Game.playAgain();
        Game.rollDice();
        while (Game.getWinState() == 0) {
            Game.rollDice();
            if (Game.getTotalDieValue() == 7) {
                assertEquals(-1, Game.getWinState());
            }
        }
    }

    @Test
    void testPlayerWinCount() {
        Game.setPlayerWinCount(5);
        assertEquals(5, Game.getPlayerWinCount());

        Game.setPlayerWinCount(Game.getPlayerWinCount() + 1);
        assertEquals(6, Game.getPlayerWinCount());
    }

    @Test
    void testHouseWinCount() {
        Game.setHouseWinCount(3);
        assertEquals(3, Game.getHouseWinCount());

        Game.setHouseWinCount(Game.getHouseWinCount() + 1);
        assertEquals(4, Game.getHouseWinCount());
    }

    @Test
    void testReset() {
        Game.setBankAmount(100);
        Game.setPlayerWinCount(5);
        Game.setHouseWinCount(3);

        Game.reset();

        assertEquals(0, Game.getBankAmount());
        assertEquals(0, Game.getPlayerWinCount());
        assertEquals(0, Game.getHouseWinCount());
    }

    @Test
    void testPlayAgain() {
        Game.setBetAmount(20);
        Game.rollDice();

        Game.playAgain();

        assertEquals(0, Game.getBetAmount());
        assertEquals(0, Game.getDie1Value());
        assertEquals(0, Game.getDie2Value());
        assertEquals(0, Game.getTotalDieValue());
        assertEquals(0, Game.getWinState());
    }
}

