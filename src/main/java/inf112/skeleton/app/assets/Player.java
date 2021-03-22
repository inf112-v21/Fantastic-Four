package inf112.skeleton.app.assets;

import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.ProgramCard;

import java.util.List;

public class Player {

    private String playerName;
    private List<ProgramCard> programCards;
    private int damage;
    private int life;
    public int x, y, lastX, lastY, archiveX, archiveY, directionIndex;
    private boolean powerDown;

    public static final int MIN_NUMBER_OF_LIFE_TOKENS = 0;
    public static final int MAX_NUMBER_OF_LIFE_TOKENS = 3; // or 4 if 5 or more players

    public static final int MIN_NUMBER_OF_DAMAGE_TOKENS = 0;
    public static final int MAX_NUMBER_OF_DAMAGE_TOKENS = 9;

    public Player(String playerName) {
        this(playerName, 1, 1);
    }
    public Player(String playerName, int x, int y) {
        this.playerName = playerName;
        directionIndex = 3;
        this.x = x;
        this.y = y;
        archiveX = x;
        archiveY = y;
        lastX = -1;
        lastY = -1;
    }

    private void registerSelectedCards(List<ICard> cards) {

    }

    public void receive(List<ProgramCard> cards) {
        this.programCards = cards;
    }

    public int getDamage() {
        return damage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ProgramCard getProgramCard(int registerNumber) {
        return programCards.get(registerNumber);
    }

    // === DAMAGE LOGIC ===
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
    public void setRobotPosition(int x, int y) {
        lastX = this.x;
        lastY = this.y;
        this.x = x;
        this.y = y;
    }

    public void setArchiveMarkerPosition(int x, int y) {
        archiveX = x;
        archiveY = y;
    }

    public void moveRobotByProgramCard(ProgramCard programCard) {
        System.out.println(programCard); // TODO only for testing purposes

        int[] moveAndRotate = ProgramCard.interpretType(programCard.getProgramCardType());

        directionIndex += moveAndRotate[1];
        directionIndex %= Definitions.Direction.values().length;
        while (directionIndex < 0) directionIndex += Definitions.Direction.values().length;

        for (int step = 0; step < moveAndRotate[0]; step++) moveOneStep();
    }

    public void moveOneStep() {
        Definitions.Direction direction = Definitions.Direction.values()[directionIndex];
        if (direction == Definitions.Direction.UP) {
            y++;
        }
        else if (direction == Definitions.Direction.LEFT) {
            x--;
        }
        else if (direction == Definitions.Direction.DOWN) {
            y--;
        }
        else {
            x++;
        }
    }

    public List<ProgramCard> getProgramCards() {
        return programCards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                '}';
    }
}

