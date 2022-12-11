package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.game.icrogue.actor.items.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0KeyRoom extends Level0ItemRoom{
    private Item key;
    public Level0KeyRoom(DiscreteCoordinates coordinates, int idKey) {
        super(coordinates);
        this.key = new Key(this, coordinates, idKey);
    }
}
