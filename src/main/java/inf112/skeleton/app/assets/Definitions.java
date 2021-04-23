package inf112.skeleton.app.assets;

public class Definitions {

    public enum Direction {
        RIGHT,
        DOWN,
        LEFT,
        UP
    }

    public enum ActivityType {
        OPEN_MENU,
        WAIT_FOR_MENU_SELECTION,
        CHECK_MULTIPLAYER,
        WAIT_FOR_CONNECTIONS,
        PICK_BOARD,
        DEAL_CARDS,
        ARRANGE_CARDS_AND_ANNOUNCE_INTENT,
        COMPLETE_REGISTERS,
        HALT
    }

}
