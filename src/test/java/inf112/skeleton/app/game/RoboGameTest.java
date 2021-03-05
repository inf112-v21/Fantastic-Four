package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoboGameTest {
    RoboGame roboGame;
    Player player;

    @Before
    public void setup() {
        roboGame = new RoboGame();
        player = new Player("TestPlayer");
        player.chooseRobot("TestRobot");
        player.setRobotPosition(2f, 2f);
        roboGame.addPlayer(player);
    }

    @Test
    public void testDealProgramCards() {
        roboGame.dealProgramCards();
        Assert.assertEquals(9, player.getReceivedProgramCards().size());
    }

    @Test
    public void testAddPlayer() {
        Player newPlayer = new Player("AnotherTestPlayer");
        Assert.assertTrue(roboGame.getPlayers().contains(newPlayer));
    }
}
