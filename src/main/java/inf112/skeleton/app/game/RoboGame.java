package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.Definitions;
import inf112.skeleton.app.assets.Definitions.ActivityType;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.screens.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoboGame extends com.badlogic.gdx.Game {

    private ProgramDeck programDeck;
    private List<Player> players;
    public Player localPlayer;
    final int MAX_NUMBER_OF_CARDS = 9;

    RoboRallyClient roboClient;
    RoboRallyServer roboServer;

    Activity currentActivity;
    ActivityType lastActivityType;

    boolean gameStarted;
    boolean multiplayer;

    //Declaration of screens
    MainMenuScreen mainMenuScreen;
    MultiplayerSetupScreen multiplayerSetupScreen;
    GameOverScreen gameOverScreen;
    RulesScreen rulesScreen;
    GameActionScreen gameActionScreen;
    List<ProgramCard> cards;
    /**
     * Duration of each program card execution in seconds
     */
    private final long PROGRAMCARD_DURATION;

    /**
     * The standard duration of each activity that is not instant or unbound
     */
    private final long STANDARD_DURATION;

    /**
     * How long to wait for incoming connections
     */
    private final long WAITCONNECTIONDURATION;

    public RoboGame() {
        programDeck = new ProgramDeck();
        programDeck.createDeck();
        players = new LinkedList<>();
        currentActivity = new Activity(Definitions.ActivityType.OPEN_MENU, -1);
        lastActivityType = Definitions.ActivityType.EXECUTE_PROGRAMCARDS_5;
        multiplayer = false; // Will be changed if the server starts
        gameStarted = false;
        PROGRAMCARD_DURATION = 1;
        STANDARD_DURATION = 1;
        WAITCONNECTIONDURATION = 5;
    }

    @Override
    public void create() {
    }

    public void launchGame() {
//        addPlayer(new Player("Player 2", 8, 4)); // TODO For testing purposes, remove

        gameActionScreen = new GameActionScreen(this, "exchange.tmx");
        setScreen(gameActionScreen);
        gameStarted = true;
    }

    public void launchStartScreen() {
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }

    public void initiateMultiplayer() {
        multiplayerSetupScreen = new MultiplayerSetupScreen(this);
        setScreen(multiplayerSetupScreen);
        multiplayer = true;
    }

    public void connectToHost(String serverIp, String nickname) {
        roboClient = new RoboRallyClient(this);
        roboClient.connectToServer(serverIp, nickname);
    }

    public void startHost(String nickname) {
        roboServer = new RoboRallyServer(this, nickname);
        roboServer.startServer();
    }

    public void addPlayer(Player player) {
        players.add(player);
        System.out.println(players.toString());
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void render() {
        super.render();
        tick();
    }

    /**
     * The ticker is called several times a second, via the LibGDX render()-method
     */
    void tick() {
        // TODO Check win conditions before the if-statement

        // Print the current activity
        if (lastActivityType != currentActivity.currentType) {
            System.out.println(currentActivity.currentType);
            lastActivityType = currentActivity.currentType;
        }

        if (currentActivity.currentType.equals(Definitions.ActivityType.
                OPEN_MENU)) {
            mainMenuScreen = new MainMenuScreen(this);
            setScreen(mainMenuScreen);
            currentActivity = new Activity(Definitions.ActivityType.
                    WAIT_FOR_MENU_SELECTION, -1);
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                WAIT_FOR_MENU_SELECTION)) {
            if (gameStarted) currentActivity = new Activity(Definitions.ActivityType.CHECK_MULTIPLAYER, -1);
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                CHECK_MULTIPLAYER)) {
            if (gameStarted) {
                if (multiplayer) currentActivity = new Activity(Definitions.ActivityType.WAIT_FOR_CONNECTIONS, WAITCONNECTIONDURATION);
                else currentActivity = new Activity(Definitions.ActivityType.PICK_BOARD, STANDARD_DURATION);
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                WAIT_FOR_CONNECTIONS)) {
            if (currentActivity.hasTimedOut()) {
                // TODO decide if one should add an AI player if noone has connected
                currentActivity = new Activity(Definitions.ActivityType.PICK_BOARD, STANDARD_DURATION);
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                PICK_BOARD)) {
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(Definitions.ActivityType.DEAL_CARDS, STANDARD_DURATION);
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                DEAL_CARDS)) {
            dealProgramCards();
            gameActionScreen.showCards();
            currentActivity = new Activity(Definitions.ActivityType.PICK_CARDS, 10);
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                PICK_CARDS)) {
            if (currentActivity.hasTimedOut()) {
                for (Player player : players) {
                    if (!player.hasChosenProgramCards()) {
                        System.out.println(player + " has not chosen programcards"); // TODO remove
                        List<ProgramCard> cards = player.getReceivedProgramCards();
                        while (cards.size() > 5) cards.remove(0);
                        player.receiveChosenProgramCards(cards);
                    }
                }
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_1, PROGRAMCARD_DURATION);
                gameActionScreen.hideCards();
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                EXECUTE_PROGRAMCARDS_1)) {
            for (Player player : players) {
                player.moveRobotByProgramCard(player.getProgramCard(0));
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_2, PROGRAMCARD_DURATION);
            }
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_2, PROGRAMCARD_DURATION);
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                EXECUTE_PROGRAMCARDS_2)) {
            for (Player player : players) {
                player.moveRobotByProgramCard(player.getProgramCard(1));
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_3, PROGRAMCARD_DURATION);
            }
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_3, PROGRAMCARD_DURATION);
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                EXECUTE_PROGRAMCARDS_3)) {
            for (Player player : players) {
                player.moveRobotByProgramCard(player.getProgramCard(2));
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_4, PROGRAMCARD_DURATION);
            }
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_4, PROGRAMCARD_DURATION);
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                EXECUTE_PROGRAMCARDS_4)) {
            for (Player player : players) {
                player.moveRobotByProgramCard(player.getProgramCard(3));
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_5, PROGRAMCARD_DURATION);
            }
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_5, PROGRAMCARD_DURATION);
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                EXECUTE_PROGRAMCARDS_5)) {
            for (Player player : players) {
                player.moveRobotByProgramCard(player.getProgramCard(4));
                currentActivity = new Activity(Definitions.ActivityType.HALT, PROGRAMCARD_DURATION);
            }
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(Definitions.ActivityType.HALT, STANDARD_DURATION);
            }
        }
        else if (currentActivity.currentType.equals(Definitions.ActivityType.
                HALT)) {
                // No content
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Deal the program cards
     */
    public void dealProgramCards() {
        for (Player player : players) {
            cards = new ArrayList(); // Create a small deck of cards for each player
            cards.addAll(programDeck.draw(MAX_NUMBER_OF_CARDS - player.getDamage()));
            player.receiveProgramCardsToPick(cards); // Each player receives it's cards
            System.out.println();
            for (ProgramCard c : cards) {
            	System.out.println(c.getProgramCardType());
            }
            
        }
    }

    /**
     * Deal the option cards
     */
    public void dealOptionCards() {

    }
    public List<ProgramCard> getdealProgramCards() {
    	dealProgramCards();
		return cards;
    	
    }
}
