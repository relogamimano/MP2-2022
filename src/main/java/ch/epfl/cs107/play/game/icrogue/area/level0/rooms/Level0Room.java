package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

// TODO: 03.12.22 what are good practice of access modifier with classes ? 
public class Level0Room extends ICRogueRoom {

    public Level0Room(DiscreteCoordinates roomCoordinates) {
        // TODO: 02.12.22 Should i add a constant behaviorName ?
        super("icrogue/Level0Room", roomCoordinates);
    }

    @Override
    protected void createArea() {
        // Base
        // TODO: 05.12.22 c'est pourquoi faire le getter ?
        registerActor(new Background(this, behaviorName));
    }

    @Override
    public String getTitle() {
        return "icrogue/level0" + super.roomCoordinates.x + super.roomCoordinates.y;
    }


}
