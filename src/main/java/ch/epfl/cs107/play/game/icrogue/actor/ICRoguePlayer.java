package ch.epfl.cs107.play.game.icrogue.actor;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior;
import ch.epfl.cs107.play.game.icrogue.actor.items.Cherry;
import ch.epfl.cs107.play.game.icrogue.actor.items.Key;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.FireBall;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.ICRogue;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room;
import java.util.Collections;
import java.util.List;

public class ICRoguePlayer extends ICRogueActor implements Interactor {
    private TextGraphics message;
    private final Sprite downSprite;
    private final Sprite rightSprite;
    private final Sprite upSprite;
    private final Sprite leftSprite;

    private boolean staffCollect = false;
//    Level0Room currentRoom;
    private ICRoguePlayer player;
    /// Animation duration in frame number
    private final static int MOVE_DURATION = 8;
    ICRogueInteractionHandler icRogueInteractionHandler = new ICRoguePlayerInteractionHandler();


    public ICRoguePlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
        super(owner, orientation, coordinates);

        downSprite = new Sprite("zelda/player", .75f, 1.5f, this, new RegionOfInterest(0, 0, 16, 32), new Vector(.15f, -.15f));
        rightSprite = new Sprite("zelda/player", .75f, 1.5f, this, new RegionOfInterest(0, 32, 16, 32), new Vector(.15f, -.15f));
        upSprite = new Sprite("zelda/player", .75f, 1.5f, this, new RegionOfInterest(0, 64, 16, 32), new Vector(.15f, -.15f));
        leftSprite = new Sprite("zelda/player", .75f, 1.5f, this, new RegionOfInterest(0, 96, 16, 32), new Vector(.15f, -.15f));

        resetMotion();
    }



    @Override
    public void update(float deltaTime) {
        Keyboard keyboard= getOwnerArea().getKeyboard();

        moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        // TODO: 09.12.22 whould i create a methode throwFireBallIfPressed ? 
        if(keyboard.get(Keyboard.X).isDown() && staffCollect) {
            FireBall fireBall = new FireBall(getOwnerArea(), getOrientation(), getCurrentMainCellCoordinates());
            fireBall.enterArea(getOwnerArea(), getCurrentMainCellCoordinates());
        }

        super.update(deltaTime);
    }


    /**
     * Orientate and Move this player in the given orientation if the given button is down
     * @param orientation (Orientation): given orientation, not null
     * @param b (Button): button corresponding to the given orientation, not null
     */
    private void moveIfPressed(Orientation orientation, Button b){
        if(b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                move(MOVE_DURATION);
            }
        }
    }

    /**
     * Leave an area by unregister this player
     */
    public void leaveArea(){
        getOwnerArea().unregisterActor(this);
    }

    /**
     *
     * @param area (Area): initial area, not null
     * @param position (DiscreteCoordinates): initial position, not null
     */
    public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    @Override
    // TODO: 06.12.22 verifier switch
    public void draw(Canvas canvas) {
        switch (getOrientation()){
            case UP:
                upSprite.draw(canvas);
                break;
            case DOWN:
                downSprite.draw(canvas);
                break;
            case RIGHT:
                rightSprite.draw(canvas);
                break;
            case LEFT:
                leftSprite.draw(canvas);
                break;
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList (getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        // TODO: 09.12.22 is there a better way to access the W key ?
        return getOwnerArea().getKeyboard().get(Keyboard.W).isDown();
    }



    @Override
    public boolean takeCellSpace() {return true;} // non traversable
    @Override
    public boolean isCellInteractable() {
        return true;
    }
    @Override
    public boolean isViewInteractable() {
        return true;
    }
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith(this, isCellInteraction);
    }

    public void interactWith(Interactable other,boolean isCellInteraction) {
        other.acceptInteraction(icRogueInteractionHandler, isCellInteraction);
    }

    private class ICRoguePlayerInteractionHandler implements ICRogueInteractionHandler {

        @Override
        public void interactWith(Cherry other, boolean isCellInteraction) {
            other.collect();

        }
        @Override
        public void interactWith(Staff other, boolean isCellInteraction) {
            other.collect();
            staffCollect = true;

        }
        @Override
        public void interactWith(Key other, boolean isCellInteraction) {
            other.collect();
        }

        @Override
        public void interactWith(ICRogueBehavior.ICRogueCell other, boolean isCellInteraction) {
            ICRogueInteractionHandler.super.interactWith(other, isCellInteraction);
        }

        @Override
        public void interactWith(ICRoguePlayer other, boolean isCellInteraction) {
            ICRogueInteractionHandler.super.interactWith(other, isCellInteraction);
        }
    }
}
