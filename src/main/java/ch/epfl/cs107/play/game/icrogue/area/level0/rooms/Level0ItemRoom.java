package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.List;

abstract public class Level0ItemRoom extends Level0Room {
    private List<Item> items;

    public Level0ItemRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
