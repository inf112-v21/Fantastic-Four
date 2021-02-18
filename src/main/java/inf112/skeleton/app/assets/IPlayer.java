package inf112.skeleton.app.assets;

import inf112.skeleton.app.assets.cards.IDeck;

public interface IPlayer {
    /**
     * Receive an IDeck of cards to pick from (example: Receive 9 cards to pick 5 from)
     * @param cards The cards to pick from
     */
    void receive(IDeck cards);

    /**
     *
     * @return The damage this player has gotten
     */
    int getDamage();
}
