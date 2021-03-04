package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.IPlayer;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.screens.*;
import inf112.skeleton.app.game.Activity.ActivityType;


import java.util.LinkedList;
import java.util.List;

public class RoboGame extends com.badlogic.gdx.Game {

    private IDeck programDeck;
    private IDeck programCardDiscardPile;
    private List<IPlayer> players;
    final int MAX_NUMBER_OF_CARDS = 9;

    RoboRallyClient roboClient;
    RoboRallyServer roboServer;

    String currentMap;

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

    /**
     * Duration of each program card execution in seconds
     */
    private final long PROGRAMCARDDURATION;

    /**
     * The standard duration of each activity that is not instant or unbound
     */
    private final long STANDARDDURATION;

    /**
     * How long to wait for incoming connections
     */
    private final long WAITCONNECTIONDURATION;

    public RoboGame() {
        programDeck = new ProgramDeck();
        programDeck.createDeck();
        programCardDiscardPile = new ProgramDeck();
        players = new LinkedList<>();
        currentActivity = new Activity(ActivityType.OPENMENU, -1);
        lastActivityType = ActivityType.EXECUTEPROGRAMCARDS5;
        multiplayer = false; // Will be changed if the server starts
        gameStarted = false;
        PROGRAMCARDDURATION = 1;
        STANDARDDURATION = 1;
        WAITCONNECTIONDURATION = 60;
    }

    @Override
    public void create() {
        tick();
    }

    public void launchGame() {
        gameActionScreen = new GameActionScreen(this, "example.tmx");
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

    public String getPlayers() {
        return players.toString();
    }

    @Override
    public void render() {
        super.render();
        tick();
    }

    /**
     * The ticker is called several times a second, via the LibGDX render()-method
     */
    private void tick() {
        // TODO Check win conditions before the if-statement

        if (lastActivityType != currentActivity.currentType) {
            System.out.println(currentActivity.currentType);
            lastActivityType = currentActivity.currentType;
        }

        if (currentActivity.currentType.equals(ActivityType.
                OPENMENU)) {
            mainMenuScreen = new MainMenuScreen(this);
            setScreen(mainMenuScreen);
            currentActivity = new Activity(ActivityType.
                    WAITFORMENUSELECTION, -1);
        }
        else if (currentActivity.currentType.equals(ActivityType.
                WAITFORMENUSELECTION)) {
            if (gameStarted) currentActivity = new Activity(ActivityType.CHECKMULTIPLAYER, -1);
        }
        else if (currentActivity.currentType.equals(ActivityType.
                CHECKMULTIPLAYER)) {
            if (gameStarted) {
                if (multiplayer) currentActivity = new Activity(ActivityType.WAITFORCONNECTIONS, WAITCONNECTIONDURATION);
                else currentActivity = new Activity(ActivityType.PICKBOARD, STANDARDDURATION);
            }
        }
        else if (currentActivity.currentType.equals(ActivityType.
                WAITFORCONNECTIONS)) {
            if (currentActivity.hasTimedOut()) {
                // TODO decide if one should add an AI player if noone has connected
                currentActivity = new Activity(ActivityType.PICKBOARD, STANDARDDURATION);
            }
        }
        else if (currentActivity.currentType.equals(ActivityType.
                PICKBOARD)) {
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(ActivityType.DEALCARDS, -1);
            }
        }
        else if (currentActivity.currentType.equals(ActivityType.
                DEALCARDS)) {
            dealProgramCards();
            currentActivity = new Activity(ActivityType.PICKCARDS, STANDARDDURATION);
        }
        else if (currentActivity.currentType.equals(ActivityType.
                PICKCARDS)) {
            if (currentActivity.hasTimedOut()) {
                for (IPlayer player : players) {
                    ProgramDeck cards = player.getReceivedProgramCards();
                    while (cards.deck.size() > 5) cards.deck.pop();
                    player.getRobot().getProgramSheet().registerCards(cards);
                    // TODO 1: Pick 5 cards for each player
                    // TODO 2: Only pick 5 cards for the players that are not finished
                }
                currentActivity = new Activity(ActivityType.EXECUTEPROGRAMCARDS1, PROGRAMCARDDURATION);
            }
        }
        else if (currentActivity.currentType.equals(ActivityType.
                EXECUTEPROGRAMCARDS1)) {
            for (IPlayer player : players) player.executeProgramCard(1);
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(ActivityType.EXECUTEPROGRAMCARDS2, PROGRAMCARDDURATION);
            }
        }
        else if (currentActivity.currentType.equals(ActivityType.
                EXECUTEPROGRAMCARDS2)) {
            for (IPlayer player : players) player.executeProgramCard(2);
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(ActivityType.EXECUTEPROGRAMCARDS3, PROGRAMCARDDURATION);
            }
        }
        else if (currentActivity.currentType.equals(ActivityType.
                EXECUTEPROGRAMCARDS3)) {
            for (IPlayer player : players) player.executeProgramCard(3);
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(ActivityType.EXECUTEPROGRAMCARDS4, PROGRAMCARDDURATION);
            }
        }
        else if (currentActivity.currentType.equals(ActivityType.
                EXECUTEPROGRAMCARDS4)) {
            for (IPlayer player : players) player.executeProgramCard(4);
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(ActivityType.EXECUTEPROGRAMCARDS5, PROGRAMCARDDURATION);
            }
        }
        else if (currentActivity.currentType.equals(ActivityType.
                EXECUTEPROGRAMCARDS5)) {
            for (IPlayer player : players) player.executeProgramCard(5);
            if (currentActivity.hasTimedOut()) {
                currentActivity = new Activity(ActivityType.EXECUTEPROGRAMCARDS5, PROGRAMCARDDURATION);
            }
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
        for (IPlayer player : players) {
            IDeck cards = new ProgramDeck(); // Create a small deck of cards for each player
            cards.draw(MAX_NUMBER_OF_CARDS - player.getDamage());
            player.receive(cards); // Each player receives it's cards
        }
    }

    /**
     * Deal the option cards
     */
    public void dealOptionCards() {

    }

    /**
     * Return unused cards to the discard pile
     * // TODO #1: Evaluate if it is nescessary to keep a discard pile or if the implication that a card is not available in the programDeck is enough.
     * // TODO #2: Expand or make a copy to handle OptionCards
     * @param cards The deck of unused cards to be kept in the discard pile
     */
    public void programCardsToToDiscardPile(IDeck cards) {
        for (ICard card : cards.getCards()) {
            programCardDiscardPile.add(card);
        }
    }
}
