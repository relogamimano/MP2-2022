package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.Connector;
import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.game.icrogue.actor.items.Key;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.game.icrogue.actor.items.Cherry;
import ch.epfl.cs107.play.game.icrogue.area.ConnectorInRoom;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;

public class Level0Room extends ICRogueRoom {

    private final Item staff;
    private final Item cherry;
    private final Item key;

    public enum Level0Connectors implements ConnectorInRoom {
        // ordre des attributs: position, destination, orientation
        W(new DiscreteCoordinates(0, 4), new DiscreteCoordinates(8, 5), Orientation.LEFT),
        S(new DiscreteCoordinates(4, 0), new DiscreteCoordinates(5, 8), Orientation.DOWN),
        E(new DiscreteCoordinates(9, 4), new DiscreteCoordinates(1, 5), Orientation.RIGHT),
        N(new DiscreteCoordinates(4, 9), new DiscreteCoordinates(5, 1), Orientation.UP);
        private final DiscreteCoordinates position;
        private final DiscreteCoordinates destinationCoordinates;
        private final Orientation orientation;

        public static List<DiscreteCoordinates> getAllConnectorsDestionation() {
            List<DiscreteCoordinates>  allConnectorsDestination = new ArrayList<>();
            for (Level0Connectors level0Connectors : Level0Connectors.values()) {
                allConnectorsDestination.add(level0Connectors.destinationCoordinates);
            }
            return allConnectorsDestination;
        }
        public static List<Orientation> getAllConnectorsOrientation() {
            List<Orientation>  allConnectorsOrientation = new ArrayList<>();
            for (Level0Connectors level0Connectors : Level0Connectors.values()) {
                allConnectorsOrientation.add(level0Connectors.orientation);
            }
            return allConnectorsOrientation;
        }
        public static List<DiscreteCoordinates> getAllConnectorsPosition() {
            List<DiscreteCoordinates>  allConnectorsPosition = new ArrayList<>();
            for (Level0Connectors level0Connectors : Level0Connectors.values()) {
                allConnectorsPosition.add(level0Connectors.position);
            }
            return allConnectorsPosition;
        }

        Level0Connectors(DiscreteCoordinates position, DiscreteCoordinates destination, Orientation orientation) {
            this.position = position;
            this.destinationCoordinates = destination;
            this.orientation = orientation;

        }
        @Override
        public int getIndex() {
            return this.ordinal();
        }
        @Override
        public DiscreteCoordinates getDestination() {
            return this.getDestination();
        }

    }
    public Level0Room(DiscreteCoordinates roomCoordinates) {
        // TODO: 02.12.22 Should i add a constant behaviorName ?
        // private String behaviorName = "icroque/Level0Room"

        super(Level0Connectors.getAllConnectorsPosition(), Level0Connectors.getAllConnectorsOrientation(),"icrogue/Level0Room", roomCoordinates, Level0Connectors.getAllConnectorsDestionation());
        key = new Key(this, new DiscreteCoordinates(4, 6), 22);
        staff = new Staff(this, new DiscreteCoordinates(4, 3));
        cherry = new Cherry(this, new DiscreteCoordinates(6, 3));

    }


    @Override
    protected void createArea() {
        // Base
        registerActor(new Background(this, behaviorName));
        registerActor(staff);
        registerActor(cherry);
        registerActor(key);
        for (Connector connector: connectors) {
            registerActor(connector);
        }


    }

    @Override
    public String getTitle() {
        return "icrogue/level0" + super.roomCoordinates.x + super.roomCoordinates.y;
    }


}
