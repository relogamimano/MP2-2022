package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.game.icrogue.actor.items.Cherry;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;

// TODO: 03.12.22 what are good practice of access modifier with classes ? 
public class Level0Room extends ICRogueRoom {
    Item staff;
    Item cherry;
    public Level0Room(DiscreteCoordinates roomCoordinates) {
        // TODO: 02.12.22 Should i add a constant behaviorName ?
        super("icrogue/Level0Room", roomCoordinates);
        staff = new Staff(this, Orientation.DOWN, new DiscreteCoordinates(4, 3));
        cherry = new Cherry(this, Orientation.DOWN, new DiscreteCoordinates(6, 3));
    }

    @Override
    protected void createArea() {
        // Base
        // TODO: 05.12.22 c'est pourquoi faire le getter ?
        registerActor(new Background(this, behaviorName));
        registerActor(staff);
        registerActor(cherry);
    }

    @Override
    public String getTitle() {
        return "icrogue/level0" + super.roomCoordinates.x + super.roomCoordinates.y;
    }


}
