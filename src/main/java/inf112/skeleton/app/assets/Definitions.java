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
        EXECUTE_PROGRAMCARDS_1,
        EXECUTE_PROGRAMCARDS_2,
        EXECUTE_PROGRAMCARDS_3,
        EXECUTE_PROGRAMCARDS_4,
        EXECUTE_PROGRAMCARDS_5,
        HALT
    }

    public enum Setup {
        CHOOSE_COURSE, // denne heter PICK_BOARD over
        CHOOSE_ROBOT,
        DETERMINE_FIRST
    }

    public enum HowToPlay {
        DEAL_CARDS,
        ARRANGE_CARDS,
        ANNOUNCE_INTENT,
        COMPLETE_REGISTERS,
        CLEANUP
    }

    public enum CompleteRegisters {
        REVEAL_PROGRAMCARDS,
        ROBOTS_MOVE,
        BOARD_ELEMENTS_MOVE,
        LASERS_FIRE,
        TOUCH_CHECK_POINT
    }

    public enum CleanUp {
        SINGLE_WRENCH,
        WIPE_CLEAN_REGISTERS
    }
}
