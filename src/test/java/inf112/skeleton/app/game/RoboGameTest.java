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
        roboGame.addPlayer(newPlayer);

        Assert.assertTrue(roboGame.getPlayers().contains(newPlayer));
    }

    @Test
    public void testTickFromWAITFORMENUSELECTIONToCHECKMULTIPLAYER() {
        roboGame.currentActivity = new Activity(Activity.ActivityType.WAITFORMENUSELECTION, -1);
        roboGame.gameStarted = true;

        roboGame.tick();

        Assert.assertEquals(Activity.ActivityType.CHECKMULTIPLAYER, roboGame.currentActivity.currentType);
    }

    @Test
    public void testTickHALTDoesNotChangeActivity() {
        roboGame.currentActivity = new Activity(Activity.ActivityType.HALT, -1);
        roboGame.gameStarted = true;

        roboGame.tick();

        Assert.assertEquals(Activity.ActivityType.HALT, roboGame.currentActivity.currentType);
    }
}
