package ch.epfl.cs107.play.game.icrogue.actor;


import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public abstract class Connector extends ICRogueActor implements Interactable {
    private Sprite sprite;
    private String directionArea;
    private DiscreteCoordinates directionCoordinates;
    final private int NO_KEY_ID = 22;
    private State state;
    public enum State {
        OPEN,
        CLOSED,
        LOCKED,
        INVISIBLE;
    }


    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Connector(Area area, Orientation orientation, DiscreteCoordinates position, State state) {
        super(area, orientation, position);
        this.state = state;
        switch (state) {
            case LOCKED:
                this.sprite = new Sprite("icrogue/door_"+orientation.ordinal(), (orientation.ordinal()+1)%2+1, orientation.ordinal()%2+1, this);
                break;
            case INVISIBLE:
                this.sprite = new Sprite("icrogue/invisibleDoor_"+orientation.ordinal(), (orientation.ordinal()+1)%2+1, orientation.ordinal()%2+1, this);
                break;
            case CLOSED:
                this.sprite = new Sprite("icrogue/door_"+orientation.ordinal(), (orientation.ordinal()+1)%2+1, orientation.ordinal()%2+1, this);
                break;
            default:
                this.sprite = null;
        }
    }




    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        DiscreteCoordinates coord = getCurrentMainCellCoordinates();
        return List.of(coord, coord.jump(new Vector((getOrientation().ordinal()+1)%2, getOrientation().ordinal()%2)));
    }


    @Override
    public boolean takeCellSpace() {
        if (state == State.OPEN) {
            return false; // traversable
        }
        return true;// non traversable
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

    }
}
