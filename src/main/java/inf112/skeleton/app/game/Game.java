package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.IPlayer;
import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.OptionDeck;
import inf112.skeleton.app.assets.cards.ProgramDeck;

import java.util.LinkedList;
import java.util.List;

public class Game {

    IDeck programDeck;
    IDeck optionDeck;
    IDeck programCardDiscarPile;
    IDeck optionCardDiscardPile;
    List<IPlayer> players;
    final int MAX_NUMBER_OF_CARDS = 9;

    public Game() {
        programDeck = new ProgramDeck();
        programDeck.createDeck();
        optionDeck = new OptionDeck();
        optionDeck.createDeck();
        programCardDiscarPile = new ProgramDeck();
        optionCardDiscardPile = new OptionDeck();
        players = new LinkedList<>();
    }

    public void dealProgramCards() {
        for (IPlayer player : players) {
            IDeck cards = new ProgramDeck(); // Create a small deck of cards for each player
            cards.draw(MAX_NUMBER_OF_CARDS - player.getDamage());
            player.receive(cards); // Each player receives it's cards
        }
    }

    public void dealOptionCards() {

    }

    /**
     * Return unused cards to the discard pile
     * // TODO #1: Evaluate if it is nescessary to keep a discard pile or if the implication that a card is not available in the programDeck is enough.
     * // TODO #2: Expand or make a copy to handle OptionCards
     * @param cards The deck of unused cards to be returned to the discard pile
     */
    public void programCardsToToDiscardPile(IDeck cards) {
        for (ICard card : cards.getCards()) {
            programCardDiscarPile.add(card);
        }
    }
}
