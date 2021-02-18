package inf112.skeleton.app.assets;

import inf112.skeleton.app.assets.cards.IDeck;

public class Player implements IPlayer{

    private String playerName;
    private Robot robot;

    public Player(String playerName) {//throws InstantiationException {
        this.playerName = playerName;
        try {
            this.robot = new Robot();
        } catch (InstantiationException e) {
            e.getMessage();
        }

    }

    public Player(String playerName, String robotName) {//throws InstantiationException {
        this.playerName = playerName;
        try {
            this.robot = new Robot(robotName);
        } catch (InstantiationException e) {
            e.getMessage();
        }

    }


    @Override
    public void receive(IDeck cards) {

    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String toString() {
        return playerName;
    }

    public Robot getRobot() {
        return robot;
    }
}

