package inf112.skeleton.app.assets;

import inf112.skeleton.app.assets.cards.IDeck;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;

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

    void executeProgramCard(int i);

    String getPlayerName();

    Robot getRobot();

    ProgramCard revealProgramCard(int registerNumber);

    ProgramCard getProgramCard(int registerNumber);

    void moveRobotByProgramCard(ProgramCard programCard);

    ProgramDeck getReceivedProgramCards();
}
