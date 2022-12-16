package ch.epfl.cs107.play.game.icrogue.actor.enemy;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.actor.ICRogueActor;
import ch.epfl.cs107.play.game.icrogue.area.level0.Level0;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Enemy extends ICRogueActor {
    private boolean Alive;
    private Sprite sprite;

    public Enemy(Area area, Orientation orientation, DiscreteCoordinates initialPosition){
        super(area, orientation, initialPosition);
    }

    private boolean Death(){
        // le faire mourir selon certaine conditions
        boolean isDead = true;
        return isDead;
    }
    public void setSprite(Sprite sprite) {this.sprite = sprite;}



    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {return Collections.singletonList(getCurrentMainCellCoordinates());}

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith(this, isCellInteraction);

    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);}
}