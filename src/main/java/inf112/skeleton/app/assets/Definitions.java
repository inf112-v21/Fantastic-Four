package inf112.skeleton.app.assets;

public class Definitions {

    enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }

    public enum ActivityType {
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
        EXECUTEPROGRAMCARDS5,
        HALT
    }
}
