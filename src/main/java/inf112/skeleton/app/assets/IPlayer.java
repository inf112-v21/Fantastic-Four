package inf112.skeleton.app.assets;

import inf112.skeleton.app.assets.cards.IDeck;

public interface IPlayer {
    void receive(IDeck cards); // Receive an IDeck of cards to pick from (example: Receive 9 cards to pick 5 from)
}
