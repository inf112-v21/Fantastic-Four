package inf112.skeleton.app.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.ProgramCard;

import java.util.List;

public class Player {

    private static final long TIMEBETWEENMOVES = 1000;
    private String playerName;
    private List<ProgramCard> programCards;
    private int damage, life;
    private boolean powerDown;
    private Vector2 position, archiveMarkerPosition;
    private Definitions.Direction direction;

    public static final int MIN_NUMBER_OF_LIFE_TOKENS = 0;
    public static final int MAX_NUMBER_OF_LIFE_TOKENS = 3; // or 4 if 5 or more players

    public static final int MIN_NUMBER_OF_DAMAGE_TOKENS = 0;
    public static final int MAX_NUMBER_OF_DAMAGE_TOKENS = 9;

    long lastMove;

    public Player(String playerName) {
        this.playerName = playerName;
        direction = Definitions.Direction.NORTH;
        lastMove = System.currentTimeMillis();
        position = new Vector2(1, 1);
    }

    private void registerSelectedCards(List<ICard> cards) {

    }

    public void receive(List<ProgramCard> cards) {
        this.programCards = cards;
    }

    public void chooseRobot(String robotName) {
    }

    public int getDamage() {
        return damage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ProgramCard revealProgramCard(int registerNumber) {
        return getProgramCard(registerNumber);
    }

    public ProgramCard getProgramCard(int registerNumber) {
        return programCards.get(registerNumber);
    }

    public void loseLife(int lifeTokens) throws IllegalArgumentException {
        int updatedLifeTokens = Math.max((this.life - lifeTokens), this.MIN_NUMBER_OF_LIFE_TOKENS);
        if (MAX_NUMBER_OF_LIFE_TOKENS < updatedLifeTokens)
            throw new IllegalArgumentException("Cannot lose a negative amount of life tokens");
        this.life = updatedLifeTokens;
    }

    public void receiveDamage(int damageTokens) throws IllegalArgumentException {
        int updatedDamageTokens = Math.min((this.damage + damageTokens), this.MAX_NUMBER_OF_DAMAGE_TOKENS);
        if (updatedDamageTokens < MIN_NUMBER_OF_DAMAGE_TOKENS)
            throw new IllegalArgumentException("Cannot receive a negative amount of damage tokens");
        this.damage = updatedDamageTokens;
    }

    // === MOVE LOGIC ===
    public Vector2 getRobotPosition() {
        return position;
    }

    public void setRobotPosition(float x, float y) {
        position.set(x, y);
    }

    public Vector2 getArchiveMarkerPosition() {
        return archiveMarkerPosition;
    }

    public void setArchiveMarkerPosition(float x, float y) {
        archiveMarkerPosition.set(x, y);
    }

    public void moveRobotByProgramCard(ProgramCard programCard) {
        System.out.println(programCard);
        int[] moveAndRotate = ProgramCard.interpretType(programCard.getProgramCardType());
        position.setAngle(position.angle() + moveAndRotate[1]);

        for (int step = 0; step < moveAndRotate[0]; step++) moveOneStep();
    }

    public void moveOneStep() {
        while (lastMove + TIMEBETWEENMOVES > System.currentTimeMillis()) {
            // spin waiter TODO improve
        }
        lastMove = System.currentTimeMillis();
        if (direction == Definitions.Direction.NORTH) {
            position.set(position.x, position.y + 1);
        }
        else if (direction == Definitions.Direction.WEST) {
            position.set(position.x - 1, position.y);
        }
        else if (direction == Definitions.Direction.SOUTH) {
            position.set(position.x, position.y - 1);
        }
        else {
            position.set(position.x + 1, position.y);
        }
    }

//    private void move(int dx, int dy) {
//        playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);
//        playerPosition.set(playerPosition.x + dx, playerPosition.y + dy);
//    }

    public List<ProgramCard> getProgramCards() {
        return programCards;
    }

    public void pushRobotToPosition(Vector2 position) {
        this.position.set(position.x, position.y);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", position=" + position +
                ", archiveMarkerPosition=" + archiveMarkerPosition +
                ", direction=" + direction +
                '}';
    }
}

