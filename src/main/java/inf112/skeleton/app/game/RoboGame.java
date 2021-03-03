package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.assets.IPlayer;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.OptionDeck;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.mechanics.map.Map;
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

    //Declaration of screens
    MainMenuScreen mainMenuScreen;
    MultiplayerSetupScreen multiplayerSetupScreen;
    GameOverScreen gameOverScreen;
    RulesScreen rulesScreen;
    GameActionScreen gameActionScreen;
    Map map;

    public RoboGame() {
        programDeck = new ProgramDeck();
        programDeck.createDeck();
        optionDeck = new OptionDeck();
        optionDeck.createDeck();
        programCardDiscardPile = new ProgramDeck();
        optionCardDiscardPile = new OptionDeck();
        players = new LinkedList<>();
    }

    @Override
    public void create() {
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }

    public void launchGame() {
        gameActionScreen = new GameActionScreen(this, "example.tmx");
        setScreen(gameActionScreen);
    }

    public void launchStartScreen() {
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }

    public void initiateMultiplayer() {
        multiplayerSetupScreen = new MultiplayerSetupScreen(this);
        setScreen(multiplayerSetupScreen);
    }

    public void connectToHost(String serverIp, String nickname) {
        roboClient = new RoboRallyClient(this);
        roboClient.connectToServer(serverIp, nickname);
    }

    public void startHost(String nickname) {
        roboServer = new RoboRallyServer(this);
        roboServer.startServer(nickname);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void render () {
        super.render();
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
