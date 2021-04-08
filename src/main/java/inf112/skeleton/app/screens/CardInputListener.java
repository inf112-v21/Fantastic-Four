package inf112.skeleton.app.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import inf112.skeleton.app.assets.cards.CardUI;
import inf112.skeleton.app.assets.cards.ProgramCard;

public class CardInputListener extends InputListener {
    final ImageButton cardButton;
    final GameActionScreen gas;
    final int x;
    final int y;
    final int width;
    final int height;
    final int index;
    final Label priority;

    public CardInputListener(ImageButton cardButton, GameActionScreen gas, Label priority, int x, int y, int width, int height, int index) {
        this.cardButton = cardButton;
        this.gas = gas;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.index = index;
        this.priority = priority;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (gas.roboGame.localPlayer.getChosenProgramCards().size() < 5) {
            ImageButton back = CardUI.createTextureButton(("/cards/SLOT"));
            back.setSize(width, height);
            back.setPosition(this.x, this.y);
            cardButton.setPosition(gas.cardPositions.removeFirst(), 0);
            priority.setPosition((float) (cardButton.getX()+width*.7), (float) (cardButton.getY()+height*.8));

            gas.pickedCardsStage.addActor(cardButton);
            gas.pickedCardsStage.addActor(priority);

            ProgramCard pickedCard = gas.picked.get(index);
            gas.chosen.add(pickedCard);
            gas.roboGame.localPlayer.addChosenProgramCard(pickedCard);
            priority.setAlignment(500);
        }
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
    }
}
