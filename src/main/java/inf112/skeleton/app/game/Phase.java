package inf112.skeleton.app.game;


public class Phase {
    long startingTime;
    long duration;
    Phases current;
    Phases next;
    final int MILLIS_TO_SECONDS = 1000;

    enum Phases {
        OPENMENU,
        INITIALIZE,
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
     * @param phase The current phase
     * @param duration Duration of this phase in seconds, -1 if there is no bound
     * @param next The next phase to occur
     */
    public Phase(Phases phase, long duration, Phases next) {
        current = phase;
        startingTime = System.currentTimeMillis();
        this.duration = duration;
        this.next = next;
    }

    /**
     *
     * @return True if the current phase has timed out
     */
    public boolean hasTimedOut() {
        return duration > 0 && (startingTime + duration * MILLIS_TO_SECONDS) <= System.currentTimeMillis();
    }
}
