package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.Definitions;

/**
 * An activity is something that the RoboGame application has to perform. This includes handling the UI, handling the
 * rules of RoboRally and any other activities that has to happen at a certain time
 */
public class Activity {
    final long startingTime;
    final long duration;
    final Definitions.ActivityType currentType;
    final int MILLIS_TO_SECONDS = 1000;
    long lastTimeDiff;

    /**
     *
     * @param activity The current activity
     * @param duration Duration of this phase in seconds, use a negative number if there is no bound.
     */
    public Activity(Definitions.ActivityType activity, long duration) {
        currentType = activity;
        startingTime = System.currentTimeMillis();
        this.duration = duration;
        lastTimeDiff = startingTime;
    }

    /**
     *
     * @return True if the current activity has timed out
     */
    public boolean hasTimedOut() {
        long timeDiff = ((startingTime + duration * MILLIS_TO_SECONDS) - System.currentTimeMillis()) / 1000;
        if (lastTimeDiff != timeDiff) {
            System.out.println(timeDiff);
            lastTimeDiff = timeDiff;
        }
        return duration > 0 && (startingTime + duration * MILLIS_TO_SECONDS) <= System.currentTimeMillis();
    }
}
