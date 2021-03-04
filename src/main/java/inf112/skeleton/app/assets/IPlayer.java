package inf112.skeleton.app.assets;

import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;

import java.util.List;

public interface IPlayer {
    /**
     * Receive an IDeck of cards to pick from (example: Receive 9 cards to pick 5 from)
     * @param cards The cards to pick from
     */
    void receive(List<ICard> cards);

    /**
     *
     * @return The damage this player has gotten
     */
    int getDamage();

    String getPlayerName();

    Robot getRobot();

    ProgramCard revealProgramCard(int registerNumber);

    ProgramCard getProgramCard(int registerNumber);

    void moveRobotByProgramCard(ProgramCard programCard);

    List<ICard> getReceivedProgramCards();
}
