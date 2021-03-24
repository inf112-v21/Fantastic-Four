package inf112.skeleton.app.assets.cards;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ProgramDeckTest {

	final ProgramDeck cardDeck = new ProgramDeck();
	final List<ProgramCard> testDeck = cardDeck.createDeck();

	@Test
	void testSizeOfDeck() {
		System.out.println(testDeck.size());
		assertEquals(84, testDeck.size());
	}

	@Test
	void testSizeOfDeckAfterDraw() {
		List<ProgramCard> testDrawDeck = cardDeck.draw(5);
		assertEquals(79, testDeck.size());
		assertEquals(5, testDrawDeck.size());
	}

}