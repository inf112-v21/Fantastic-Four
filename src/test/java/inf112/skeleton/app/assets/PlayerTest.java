package inf112.skeleton.app.assets;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramCard.ProgramCardType;
import inf112.skeleton.app.assets.cards.ProgramDeck;

class PlayerTest {

	ProgramDeck cardDeck = new ProgramDeck();
	Player testPlayer = new Player("TestPlayer");
	List<ProgramCard> testDeck = cardDeck.createDeck();
	List<ProgramCard> draw9 = cardDeck.draw(9);
	List<ProgramCard> definedProgramCards = new ArrayList<ProgramCard>();
	ProgramCard move3 = new ProgramCard(ProgramCardType.MOVE3, 250);
	ProgramCard move2 = new ProgramCard(ProgramCardType.MOVE2, 250);
	ProgramCard move1 = new ProgramCard(ProgramCardType.MOVE2, 250);

	@Test
	void TestPlayerRecievesNineCards() {
		testPlayer.receiveProgramCardsToPick(draw9);
		List<ProgramCard> received = testPlayer.getReceivedProgramCards();
		assertEquals(9, received.size());
		assertEquals(received, testPlayer.getReceivedProgramCards());

	}

	@Test
	void TestPlayerRecieves8DamageZeroTokensLeft() {
		testPlayer.receiveDamage(5);
		testPlayer.receiveDamage(3);
		testPlayer.receiveDamage(1);
		int testTokensAfterDamage = testPlayer.MAX_NUMBER_OF_DAMAGE_TOKENS - (testPlayer.getDamage());
		assertEquals(0, testTokensAfterDamage);

	}

	@Test
	void TestPlayerCanNotReceveMoreDamagesThanHasTokens() {
		testPlayer.receiveDamage(10);
		assertTrue(testPlayer.MAX_NUMBER_OF_DAMAGE_TOKENS <= testPlayer.getDamage());
	}


	@Test
	void TestPlayerCheckIfChosenProgramCardAreCorrect() {
		testPlayer.addChosenProgramCard(move1);
		testPlayer.addChosenProgramCard(move2);
		testPlayer.addChosenProgramCard(move3);
		assertEquals(move1, testPlayer.getChosenProgramCards().get(0));
		assertEquals(move2, testPlayer.getChosenProgramCards().get(1));
		assertEquals(move3, testPlayer.getChosenProgramCards().get(2));
		assertEquals(3, testPlayer.getChosenProgramCards().size());
	}
	
	@Test
	void TestPlayerName() {
		assertFalse(testPlayer.getPlayerName().equals("PlayerTest"));
	}
		
}