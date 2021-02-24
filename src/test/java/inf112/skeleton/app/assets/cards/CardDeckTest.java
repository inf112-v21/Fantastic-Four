package inf112.skeleton.app.assets.cards;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashSet;
import java.util.Stack;

class CardDeckTest {

	CardDeck cardDeck = new CardDeck();
	Stack<ICard> testDeck = cardDeck.createDeck();

	@Test
	void testSizeOfDeck() {
		assertEquals(84, testDeck.size());
	}

	@Test
	void testSizeOfDeckAfterDraw() {
		Stack<ICard> testDrawDeck = cardDeck.drawCardsFromDeck(5, testDeck);
		assertEquals(79, testDeck.size());
		assertEquals(5, testDrawDeck.size());
	}

	@Test
	void testCheckIfAllCardsTypesAdded() {
		LinkedHashSet<String> namesInCreatedDeck = new LinkedHashSet<String>();
		LinkedHashSet<String> names = new LinkedHashSet<String>();
		names.add("move1");
		names.add("move2");
		names.add("move3");
		names.add("backup");
		names.add("rotateRight");
		names.add("rotateLeft");
		names.add("uTurn");

		for (ICard card : testDeck) {
			namesInCreatedDeck.add(card.getName());
		}

		assertTrue(names.size() == namesInCreatedDeck.size() && namesInCreatedDeck.containsAll(names)
				&& names.containsAll(namesInCreatedDeck));
	}

}
