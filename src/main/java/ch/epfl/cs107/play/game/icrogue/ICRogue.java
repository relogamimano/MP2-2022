package ch.epfl.cs107.play.game.icrogue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.game.icrogue.area.Level;
import ch.epfl.cs107.play.game.icrogue.area.level0.Level0;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room.Level0Connectors;
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
    private Level0Room currentRoom;
    private Area currentArea;

    private Level0 level0;

    private void initLevel(){

        DiscreteCoordinates startingCoords = new DiscreteCoordinates(2,2);
        level0 = new Level0(4, 2, new DiscreteCoordinates(0,0));
        // selon les instruction de la prof
        // une fonction qui demande a level : ajoute moi les aires de tes connecteurs.
        level0.addRooms(this);
        currentRoom = (Level0Room) level0.getRoom(new DiscreteCoordinates(0,0));
        player = new ICRoguePlayer(currentRoom, Orientation.UP, startingCoords, getTitle());
        setCurrentArea(currentRoom.getTitle(), false);
//        Level0Room startRoom = (Level0Room)setCurrentArea(currentRoom.getTitle(), false);
        setCurrentArea();
        currentRoom.registerActor(player);



    }

//    protected void switchRoom() {
//
//        player.leaveArea();
//
//        areaIndex = (areaIndex==0) ? 1 : 0;
//
//        ICRogueRoom currentArea = (ICRogueRoom)setCurrentArea(areas[areaIndex], false);
//        player.enterArea(currentArea, currentArea.getPlayerSpawnPosition());
//
//
//    }

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

    }
    @Override
    public void update(float deltaTime) {

        Keyboard keyboard = getCurrentArea().getKeyboard();
        resetIfPressed(keyboard.get(Keyboard.R));

        super.update(deltaTime);

    }




    private void resetIfPressed(Button b) {
        if(b.isDown()) {
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

