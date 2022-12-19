package ch.epfl.cs107.play.game.icrogue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.area.Level;
import ch.epfl.cs107.play.game.icrogue.area.level0.Level0;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;


public class ICRogue extends AreaGame {

    public final static float CAMERA_SCALE_FACTOR = 13.f;

    private ICRoguePlayer player;


    public final static DiscreteCoordinates spawnPosition = new DiscreteCoordinates(2, 2);

    public final static DiscreteCoordinates spawnRoomPosition = new DiscreteCoordinates(1, 1);
//    private final String[] areas = {"icrogue/Level0Room"};
//    private int areaIndex;
    /**
     * Add all the areas
     */
    private Level0Room currentRoom;
    private Area currentArea;

    private Level0 level0;
    private Level level;

    private void initLevel() {
        DiscreteCoordinates startingCoords = new DiscreteCoordinates(2, 2);
        level0 = new Level0(4, 2, startingCoords);
        level0.addRooms(this);
        currentRoom = (Level0Room) level0.getRoom(spawnRoomPosition);
//        level = new Level(true, startingCoords, new int[]{1,2,3}, 4, 2);
//        level.addRooms(this);
//        currentRoom = (Level0Room) level.getRoom(spawnRoomPosition);
        player = new ICRoguePlayer(currentRoom, Orientation.UP, startingCoords, getTitle());
        setCurrentArea(currentRoom.getTitle(), false);
        setCurrentArea();
        currentRoom.registerActor(player);
    }

    protected void switchRoom() {
        player.leaveArea();
        Level0Room nextRoom = (Level0Room) setCurrentArea(player.getInteractionConnector().getDestination(), false);
        player.enterArea(nextRoom, player.getInteractionConnector().getDestinationSpawnCoordinates());
    }

    private void generateRandomMap() {
        //placement des salles
        //generation des salles
    }

    @Override
    protected Area setCurrentArea() {
        currentArea = currentRoom;
        return currentArea;
    }


    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            initLevel();
            return true;
        }
        return false;
    }

    @Override
    public void update(float deltaTime) {

        Keyboard keyboard = getCurrentArea().getKeyboard();
        resetIfPressed(keyboard.get(Keyboard.R));
        if (player.isInInteractionWithConnector()) {
            switchRoom();
        }


        super.update(deltaTime);

    }


    private void resetIfPressed(Button b) {
        if (b.isDown()) {
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

}

