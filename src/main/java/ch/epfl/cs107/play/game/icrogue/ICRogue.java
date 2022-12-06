package ch.epfl.cs107.play.game.icrogue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;

public class ICRogue extends AreaGame {

    public final static float CAMERA_SCALE_FACTOR = 13.f;

    private ICRoguePlayer player;
    private final String[] areas = {"icrogue/Level0Room"};
    private int areaIndex;
    /**
     * Add all the areas
     */
    // TODO: 03.12.22 check if currentRoom and currentArea is well declared ?
    Level0Room currentRoom;
    Area currentArea;

    private void initLevel(){
        // TODO: 02.12.22 Verifer initLevel()
        currentRoom = new Level0Room(new DiscreteCoordinates(0,0));
        DiscreteCoordinates coords = new DiscreteCoordinates(2,2);
        addArea(currentRoom);
        player = new ICRoguePlayer(currentRoom, Orientation.UP, coords, getTitle());
        Level0Room startRoom = (Level0Room)setCurrentArea(currentRoom.getTitle(), false);
        setCurrentArea();
        currentRoom.registerActor(player);


//        faire rentrer currentRoom ou icRoguePlayer dans currentArea
//        player.enterArea(currentArea, coords);
//        player.centerCamera();
    }

    protected void reset(){

    }

    // TODO: 03.12.22 Should i create a methode just to do one thing in the constructor ? Well Declared ?
    @Override
    protected Area setCurrentArea() {
        currentArea = currentRoom;
        return currentArea;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {


        if (super.begin(window, fileSystem)) {
            initLevel();
            areaIndex = 0;
            initArea(areas[areaIndex]);
            return true;
        }
        return false;
    }

    private void initArea(String areaKey) {

        ICRogueRoom area = (ICRogueRoom)setCurrentArea(areaKey, true);
//        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
//        player = new ICRoguePlayer(area, Orientation.DOWN, coords,"ghost.1");
//        player.enterArea(area, coords);
//        player.centerCamera();

    }
    @Override
    public void update(float deltaTime) {
//        if(player.isWeak()){
//            switchArea();
//        }
        Keyboard keyboard = getCurrentArea().getKeyboard();
        resetIfPressed(keyboard.get(Keyboard.R));

        super.update(deltaTime);

    }

    private void resetIfPressed(Button b) {
        if(b.isDown()) {
            // TODO: 06.12.22 voir si on peut ecrire ca autrement
            initLevel();

        }
    }



    @Override
    public void end() {
    }

    @Override
    public String getTitle() {
        return "ICRogue";
    }

//    protected void switchArea() {
//
//        player.leaveArea();
//
//        areaIndex = (areaIndex==0) ? 1 : 0;
//
//        ICRogueRoom currentArea = (ICRogueRoom) setCurrentArea(areas[areaIndex], false);
//        player.enterArea(currentArea, currentArea.getPlayerSpawnPosition());
//
//        player.strengthen();
//    }

}

