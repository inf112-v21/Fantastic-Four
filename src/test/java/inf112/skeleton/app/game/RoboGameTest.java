package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.Definitions;
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
        player.setRobotPosition(2, 2);
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
        roboGame.addPlayer(newPlayer);

        Assert.assertTrue(roboGame.getPlayers().contains(newPlayer));
    }

    @Test
    public void testTickFromWAITFORMENUSELECTIONToCHECKMULTIPLAYER() {
        roboGame.currentActivity = new Activity(Definitions.ActivityType.WAIT_FOR_MENU_SELECTION, -1);
        roboGame.gameStarted = true;

        roboGame.tick();

        Assert.assertEquals(Definitions.ActivityType.CHECK_MULTIPLAYER, roboGame.currentActivity.currentType);
    }

    @Test
    public void testTickHALTDoesNotChangeActivity() {
        roboGame.currentActivity = new Activity(Definitions.ActivityType.HALT, -1);
        roboGame.gameStarted = true;

        roboGame.tick();

        Assert.assertEquals(Definitions.ActivityType.HALT, roboGame.currentActivity.currentType);
    }
}
