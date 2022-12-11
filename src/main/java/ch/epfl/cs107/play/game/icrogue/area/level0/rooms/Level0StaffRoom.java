package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


public class Level0StaffRoom extends Level0ItemRoom{
    private Item staff;
    public Level0StaffRoom(DiscreteCoordinates coordinates) {
        super(coordinates);
        this.staff = new Staff(this, coordinates);
    }
}
