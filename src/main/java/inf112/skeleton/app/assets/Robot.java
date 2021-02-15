package inf112.skeleton.app.assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Robot {

    private final static int NUMBER_OF_ROBOTS = 8;
    private final static String[] robotNames = {"A", "B", "C"};
    private static HashMap<String, Boolean> availableRobots;
    static {
        for (String name : robotNames) availableRobots.put(name, true);
    }

    private String robotName;

    public Robot() throws InstantiationException {
        if (!availableRobots.containsValue(true)) throw new InstantiationException();
        for (String robotName : availableRobots.keySet()) {
            if (availableRobots.get(robotName)) {
                this.robotName = robotName;
                break;
            }
        }
        updateAvailableRobots(robotName);

    }

    private void updateAvailableRobots(String robotName) {
        availableRobots.put(robotName, false);
    }





}
