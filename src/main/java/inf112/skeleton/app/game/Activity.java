package inf112.skeleton.app.game;

/**
 * An activity is something that the RoboGame application has to perform. This includes handling the UI, handling the
 * rules of RoboRally and any other activities that has to happen at a certain time
 */
public class Activity {
    long startingTime;
    long duration;
    ActivityType currentType;
    final int MILLIS_TO_SECONDS = 1000;

    enum ActivityType {
        OPENMENU,
        WAITFORMENUSELECTION,
        CHECKMULTIPLAYER,
        WAITFORCONNECTIONS,
        PICKBOARD,
        DEALCARDS,
        PICKCARDS,
        EXECUTEPROGRAMCARDS1,
        EXECUTEPROGRAMCARDS2,
        EXECUTEPROGRAMCARDS3,
        EXECUTEPROGRAMCARDS4,
        EXECUTEPROGRAMCARDS5
    }

    /**
     *
     * @param activity The current activity
     * @param duration Duration of this phase in seconds, use a negative number if there is no bound.
     */
    public Activity(ActivityType activity, long duration) {
        currentType = activity;
        startingTime = System.currentTimeMillis();
        this.duration = duration;
    }

    /**
     *
     * @return True if the current activity has timed out
     */
    public boolean hasTimedOut() {
        return duration > 0 && (startingTime + duration * MILLIS_TO_SECONDS) <= System.currentTimeMillis();
    }
}
