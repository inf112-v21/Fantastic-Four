package inf112.skeleton.app.assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Robot {

    private final static int NUMBER_OF_ROBOTS = 8;
    private final static String[] robotNames = {"Twonky", "Squash Bot", "Twitch", "Zoom Bot",
                                                "Hammer Bot", "Spin Bot", "Hulk X90", "Trundle Bot"};
    private static HashMap<String, Boolean> availableRobots = new HashMap<>();
    static {
        for (String name : robotNames) availableRobots.put(name, true);
    }

    private String robotName;
    private ProgramSheet programSheet;


    public Robot() throws InstantiationException {
        if (!availableRobots.containsValue(true)) throw new InstantiationException("No robots are available...");
        for (String robotName : availableRobots.keySet()) {
            if (availableRobots.get(robotName)) {
                this.robotName = robotName;
                setRobotToUnavailable(robotName);
                this.programSheet = new ProgramSheet();
                break;
            }
        }

    }

    public Robot(String robotName) throws InstantiationException {
        if (!availableRobots.get(robotName)) throw new InstantiationException("The selected robot is unavailable...");
        this.robotName = robotName;
        setRobotToUnavailable(robotName);
        this.programSheet = new ProgramSheet();
    }


    private void setRobotToUnavailable(String robotName) {
        availableRobots.put(robotName, false);
    }


    public ProgramSheet getProgramSheet() {
        return this.programSheet;
    }

    @Override
    public String toString() {
        return this.robotName;
    }


}
