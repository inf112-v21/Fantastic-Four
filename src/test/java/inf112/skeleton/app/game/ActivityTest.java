package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.Definitions;
import org.junit.Assert;
import org.junit.Test;

import static java.lang.System.currentTimeMillis;

public class ActivityTest {

    @Test
    public void testHasTimedOut() throws InterruptedException {
        int durationInSeconds = 1;
        Activity activity = new Activity(Definitions.ActivityType.OPEN_MENU, durationInSeconds);
        int secToMillis = 1000;
        long start = currentTimeMillis();

        while (start + durationInSeconds * secToMillis > currentTimeMillis()) {
            // spin-waiter
        }

        Assert.assertTrue(activity.hasTimedOut());
    }

    @Test
    public void testHasNotTimedOut() throws InterruptedException {
        int durationInSeconds = 1;
        Activity activity = new Activity(Definitions.ActivityType.OPEN_MENU, durationInSeconds);
        Assert.assertFalse(activity.hasTimedOut());
    }
}
