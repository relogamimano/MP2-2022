package ch.epfl.cs107.play.game.icrogue.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior;
import ch.epfl.cs107.play.game.icrogue.actor.Connector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

public abstract class ICRogueRoom extends Area {
    private ICRogueBehavior behavior;
    protected DiscreteCoordinates roomCoordinates;
    protected String behaviorName;

    protected List<Connector> connectors;
    protected List<DiscreteCoordinates> connectorsCoordinates;
    private List<Orientation> orientations;

    private List<DiscreteCoordinates> directionRoomCoordinates;// douteux

    //================= Constructeur =================//

    public ICRogueRoom(List<DiscreteCoordinates> connectorsCoordinates, List<Orientation> orientations,
                String behaviorName, DiscreteCoordinates roomCoordinates){
        // TODO: 10.12.22 intialization douteuse 
        connectors = new ArrayList<>();
        this.connectorsCoordinates = connectorsCoordinates;
        this.orientations = orientations;
        this.roomCoordinates = roomCoordinates;
        this.behaviorName = behaviorName;

        for (int i = 0; i < connectorsCoordinates.size(); i++) {
            // TODO: 10.12.22 .opposite() well used ? 
            connectors.add(new Connector(this, orientations.get(i).opposite(),connectorsCoordinates.get(i), Connector.State.INVISIBLE));

        }

    }

    // TODO: 10.12.22 createArea() abstract ? Should we intialize connecotrs in ICRogueRooom or in Level0ROom ?
    protected abstract void createArea();


    @Override
    public String getTitle() {
        return null;
    } // TODO: 06.12.22 si erreur null peut etre changé en une string par defaut

    @Override
    public float getCameraScaleFactor() {
        return 11;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            // Set the behavior map
            behavior = new ICRogueBehavior(window, behaviorName);
            setBehavior(behavior);
            createArea();
            return true;
        }
        return false;
    }


}

