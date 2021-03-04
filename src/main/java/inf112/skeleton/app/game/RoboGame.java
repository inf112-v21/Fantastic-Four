package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.assets.IPlayer;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.OptionDeck;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.screens.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoboGame extends com.badlogic.gdx.Game {

    private IDeck programDeck;
    private IDeck optionDeck;
    private IDeck programCardDiscardPile;
    private IDeck optionCardDiscardPile;
    private List<IPlayer> players;
    final int MAX_NUMBER_OF_CARDS = 9;

    RoboRallyClient roboClient;
    RoboRallyServer roboServer;

    String currentMap;

    Phase currentPhase;
    boolean gameStarted;
    boolean multiplayer;

    //Declaration of screens
    MainMenuScreen mainMenuScreen;
    MultiplayerSetupScreen multiplayerSetupScreen;
    GameOverScreen gameOverScreen;
    RulesScreen rulesScreen;
    GameActionScreen gameActionScreen;

    public RoboGame() {
        programDeck = new ProgramDeck();
        programDeck.createDeck();
        optionDeck = new OptionDeck();
        optionDeck.createDeck();
        programCardDiscardPile = new ProgramDeck();
        optionCardDiscardPile = new OptionDeck();
        players = new LinkedList<>();
        currentPhase = new Phase(Phase.Phases.OPENMENU, -1, Phase.Phases.INITIALIZE);
        multiplayer = false; // Will be changed if the server starts
        gameStarted = false;
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
    public void render () {
        super.render();
        tick();
    }

    private void tick() {
        if (Phase.Phases.OPENMENU.equals(currentPhase.current)) {
            mainMenuScreen = new MainMenuScreen(this);
            setScreen(mainMenuScreen);
            currentPhase = new Phase(currentPhase.next, -1, Phase.Phases.CHECKMULTIPLAYER);
        }
        else if (Phase.Phases.INITIALIZE.equals(currentPhase.current)) {
            if (gameStarted) currentPhase = new Phase(Phase.Phases.CHECKMULTIPLAYER, -1, Phase.Phases.WAITFORCONNECTIONS);
        }
        else if (Phase.Phases.CHECKMULTIPLAYER.equals(currentPhase.current)) {
            if (gameStarted) {
                if (multiplayer) currentPhase = new Phase(Phase.Phases.WAITFORCONNECTIONS, 60, Phase.Phases.PICKBOARD);
                else currentPhase = new Phase(Phase.Phases.PICKBOARD, -1, Phase.Phases.DEALCARDS);
            }
        }
        else if (Phase.Phases.WAITFORCONNECTIONS.equals(currentPhase.current)) {
            if (currentPhase.hasTimedOut()) {
                currentPhase = new Phase(currentPhase.next, -1, Phase.Phases.DEALCARDS);
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
