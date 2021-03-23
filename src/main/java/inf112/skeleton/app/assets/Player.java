package inf112.skeleton.app.assets;

import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.ProgramCard;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final long TIMEBETWEENMOVES = 50;
    private String playerName;
    private List<ProgramCard> receivedProgramCards;
    private List<ProgramCard> chosenProgramCards;
    private int damage;
    private int life;
    public int x, y, lastX, lastY, archiveX, archiveY, directionIndex;
    private boolean powerDown;

    public static final int MIN_NUMBER_OF_LIFE_TOKENS = 0;
    public static final int MAX_NUMBER_OF_LIFE_TOKENS = 3; // or 4 if 5 or more players

    public static final int MIN_NUMBER_OF_DAMAGE_TOKENS = 0;
    public static final int MAX_NUMBER_OF_DAMAGE_TOKENS = 9;

    long lastMove;


    public Player(String playerName) {
        this(playerName, 1, 1);
    }

    public Player(String playerName, int x, int y) {
        this.playerName = playerName;
        directionIndex = 3;
        lastMove = System.currentTimeMillis();
        this.x = x;
        this.y = y;
        archiveX = x;
        archiveY = y;
        lastX = -1;
        lastY = -1;
        chosenProgramCards = new ArrayList<>();
    }

    private void registerSelectedCards(List<ICard> cards) {

    }

    public void receiveProgramCardsToPick(List<ProgramCard> cards) {
        this.receivedProgramCards = cards;
    }

    public void receiveChosenProgramCards(List<ProgramCard> cards) {
        this.chosenProgramCards = cards;
    }

    public int getDamage() {
        return damage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ProgramCard getProgramCard(int registerNumber) {
        return chosenProgramCards.get(registerNumber);
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
        while (lastMove + TIMEBETWEENMOVES > System.currentTimeMillis()) {
            // spin waiter TODO improve
        }
        lastMove = System.currentTimeMillis();
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

    public List<ProgramCard> getReceivedProgramCards() {
        return receivedProgramCards;
    }

    public List<ProgramCard> getChosenProgramCards() {
        return chosenProgramCards;
    }

    public void addChosenProgramCard(ProgramCard card) {
        System.out.println("Adding " + card.getProgramCardType().toString()); // TODO for debugging purposes
        chosenProgramCards.add(card);
    }

    public boolean hasChosenProgramCards() {
        return chosenProgramCards.size() == 5;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                '}';
    }
}

