package ch.epfl.cs107.play.game.icrogue.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class ICRogueRoom extends Area {
    private ICRogueBehavior behavior;
    protected DiscreteCoordinates roomCoordinates;
    protected String behaviorName;
    //================= Constructeur =================//
    public ICRogueRoom(String behaviorName, DiscreteCoordinates roomCoordinates){
        this.roomCoordinates = roomCoordinates;
        this.behaviorName = behaviorName;
    }

    protected abstract void createArea();

    // TODO: 06.12.22 si erreur null peut etre changé en une string par defaut 
    @Override
    public String getTitle() {
        return null;
    }

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

