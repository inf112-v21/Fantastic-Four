package inf112.skeleton.app.assets;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assets.cards.ProgramCard;

import java.util.*;

public class Robot {

    private final static int NUMBER_OF_ROBOTS = 8;
    private final static String[] robotNames = {"Twonky", "Squash Bot", "Twitch", "Zoom Bot",
                                                "Hammer Bot", "Spin Bot", "Hulk X90", "Trundle Bot"};
    private static HashSet<String> availableRobots = new HashSet<>(Arrays.asList(robotNames));

    private String robotName;
    private ProgramSheet programSheet;
    //private LocationAndOrientation locationAndOrientation;             The robots coordinates on the board
    //private Orientation orientation;


    public Robot() throws InstantiationException {
        if (availableRobots.isEmpty()) throw new InstantiationException("No robots are available...");
        try {
            for (String robotName : availableRobots) {
                if (availableRobots.contains(robotName)) {
                    this.robotName = robotName;
                    setRobotToUnavailable(robotName);
                    this.programSheet = new ProgramSheet();
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }


    }

    public Robot(String robotName) throws InstantiationException {
        if (!availableRobots.contains(robotName)) throw new InstantiationException("The selected robot is unavailable...");
        this.robotName = robotName;
        setRobotToUnavailable(robotName);
        this.programSheet = new ProgramSheet();
    }


    private void setRobotToUnavailable(String robotName) {
        availableRobots.remove(robotName);
    }


    public ProgramSheet getProgramSheet() {
        return this.programSheet;
    }

    @Override
    public String toString() {
        return this.robotName;
    }


    public void moveByProgramCard(ProgramCard programCard) {
    }

    public void pushToPosition(Vector2 position) {
    }
}
