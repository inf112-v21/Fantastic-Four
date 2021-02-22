package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.IPlayer;
import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.screens.MainMenuScreen;

import java.util.LinkedList;
import java.util.List;

public class RoboGame extends com.badlogic.gdx.Game {

    IDeck deck;
    IDeck discardPile;
    List<IPlayer> players;
    final int MAX_NUMBER_OF_CARDS = 9;

    //Declaration of screens
    MainMenuScreen mainMenuScreen;

    public RoboGame() {
        deck = new ProgramDeck();
        //deck.create() // TODO Match with the name of Edyta's method for creating all program cards.
        discardPile = new ProgramDeck();
        players = new LinkedList<>();

    }

    @Override
    public void create() {
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }

    @Override
    public void render () {
        super.render();
    }
    
    @Override
    public void dispose() {
        super.dispose();
    }

    public void dealProgramCards() {
        for (IPlayer player : players) {
            IDeck cards = new ProgramDeck(); // Create a small deck of cards for each player
            //cards.deal(MAX_NUMBER_OF_CARDS - player.getDamage()); // TODO Match with the name of Edyta's method for creating all cards and getDamage() in Player
            player.receive(cards); // Each player receives it's cards
        }
    }

    public void programCardsToToDiscardPile(IDeck cards) {
        for (ICard card : cards.getCards()) { // TODO Get a stack from cards, like cards.getCards()
            discardPile.add(card);
        }
    }
}
