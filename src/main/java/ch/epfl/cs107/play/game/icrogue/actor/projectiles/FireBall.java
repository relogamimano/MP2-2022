package ch.epfl.cs107.play.game.icrogue.actor.projectiles;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class FireBall extends Projectile {

    final int DEFAULT_MOVE_DURATION = 10;

    final int DEFAULT_DAMAGE = 1;

    Sprite fireBallSprite;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public FireBall(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, 5);
        fireBallSprite = new Sprite("zelda/fire", 1f, 1f, this, new RegionOfInterest(0, 0, 16, 16), new Vector(0, 0));
    }



//    public void throwFireBall(Orientation orientation) {
//        orientate(orientation);
//        move(DEFAULT_MOVE_DURATION);
//
//    }



    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return null;
    }

    @Override
    public boolean takeCellSpace() {
        return false;
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

    }
}
