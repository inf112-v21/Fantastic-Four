package inf112.skeleton.app.assets;

public class Player implements IPlayer{

    private String playerName;
    private ProgramSheet programSheet;
    private Robot robot;

    public Player(String playerName) {//throws InstantiationException {
        this.playerName = playerName;
        this.programSheet = new ProgramSheet(playerName);
        try {
            this.robot = new Robot();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

}

