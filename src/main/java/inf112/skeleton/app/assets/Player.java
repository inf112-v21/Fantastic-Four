package inf112.skeleton.app.assets;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;

public class Player implements IPlayer{

    private String playerName;
    private Robot robot;
    private ProgramDeck receivedProgramCards;



    public Player(String playerName) {
        this.playerName = playerName;
        try {
            this.robot = new Robot();
        } catch (InstantiationException e) {
            e.getMessage();
        }
    }

    public Player(String playerName, String robotName) {
        this.playerName = playerName;
        try {
            this.robot = new Robot(robotName);
        } catch (InstantiationException e) {
            e.getMessage();
        }

    }
    

    private void registerSelectedCards(ProgramDeck cards) {
        getRobot().getProgramSheet().registerCards(cards);
    }


    @Override
    public void receive(IDeck cards) {
        this.receivedProgramCards = (ProgramDeck) cards;
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

    public void moveRobotByProgramCard(ProgramCard programCard) {
        getRobot().moveByProgramCard(programCard);
    }

    public void pushRobotToPosition(Vector2 position) {
        getRobot().pushToPosition(position);
    }


}

