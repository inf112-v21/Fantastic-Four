package inf112.skeleton.app.game;

import org.junit.Assert;
import org.junit.Test;

import static java.lang.System.currentTimeMillis;

public class ActivityTest {

    @Test
    public void testHasTimedOut() throws InterruptedException {
        int durationInSeconds = 1;
        Activity activity = new Activity(Activity.ActivityType.OPENMENU, durationInSeconds);
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
        Activity activity = new Activity(Activity.ActivityType.OPENMENU, durationInSeconds);
        Assert.assertFalse(activity.hasTimedOut());
    }
}
