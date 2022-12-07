package ch.epfl.cs107.play.game.icrogue.actor.projectiles;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.actor.ICRogueActor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class Projectile extends ICRogueActor {

    private int nbFrames;

    private Sprite fireBallSprite;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Projectile(Area area, Orientation orientation, DiscreteCoordinates position, int nbFrames) {
        super(area, orientation, position);
        this.nbFrames = nbFrames;

    }
    @Override
    public void draw(Canvas canvas) {
        fireBallSprite.draw(canvas);
    }
    @Override
    public boolean takeCellSpace() {
        return false;
    }

    // ajout douteux

    

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);
        move(nbFrames);
    }
}
