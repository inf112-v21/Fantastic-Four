package inf112.skeleton.app.assets;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;

import java.util.HashSet;

public class Player implements IPlayer{

    private String playerName;
    private Robot robot;
    private ProgramDeck receivedProgramCards;



    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player(String playerName, String robotName) {
        this.playerName = playerName;
    }
    

    private void registerSelectedCards(ProgramDeck cards) {
        getRobot().getProgramSheet().registerCards(cards);
    }


    @Override
    public void receive(IDeck cards) {
        this.receivedProgramCards = (ProgramDeck) cards;
    }

    public void chooseRobot(String robotName) throws InstantiationException {
        robot = new Robot(robotName);
    }


    public String[] getRobotNames() {
        return Robot.getRobotNames();
    }

    public HashSet<String> getAvailableRobots() {
        return Robot.getAvailableRobots();
    }


    @Override
    public int getDamage() {
        return 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Robot getRobot() {
        return robot;
    }

    public ProgramCard revealProgramCard(int registerNumber) {
        return getProgramCard(registerNumber);
    }

    public ProgramCard getProgramCard(int registerNumber) {
        return getRobot().getProgramSheet().getProgramCard(registerNumber);
    }

    public Vector2 getRobotPosition() {
        return robot.getRobotPosition();
    }

    public void setRobotPosition(float x, float y) {
        robot.setRobotPosition(x,y);
    }

    public Vector2 getArchiveMarkerPosition() {
        return robot.getArchiveMarkerPosition();
    }

    public void setArchiveMarkerPosition(float x, float y) {
        robot.setArchiveMarkerPosition(x,y);
    }


    public void moveRobotByProgramCard(ProgramCard programCard) {
        getRobot().moveByProgramCard(programCard);
    }

    public void pushRobotToPosition(Vector2 position) {
        getRobot().pushToPosition(position);
    }


}

