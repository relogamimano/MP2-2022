package ch.epfl.cs107.play.game.icrogue.area;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.icrogue.RandomHelper;
import ch.epfl.cs107.play.game.icrogue.actor.Connector;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;


public class Level {
    private ICRogueRoom[][] map;
    private DiscreteCoordinates startPosition;
    private DiscreteCoordinates bossPosition;
    private String startingRoomTitle;
    private int nbRooms;
    // TODO: 18.12.22 initialisation de random utile ?
    final Random random = new Random();

    protected enum MapState {
        NULL(0), // Empty space
        PLACED(1), // The room has been placed but not yet explored by the room placement algorithm
        EXPLORED(2), // The room has been placed and explored by the algorithm
        BOSS_ROOM(3), // The room is a boss room
        CREATED(4); // The room has been instantiated in the room map
        static public MapState intToMapState(int i) {
            assert (i >= 0 && i <= 4);
            return switch (i) {
                case 0 -> MapState.NULL;
                case 1 -> MapState.PLACED;
                case 2 -> MapState.EXPLORED;
                case 3 -> MapState.BOSS_ROOM;
                case 4 -> MapState.CREATED;
                default -> MapState.NULL;
            };
        }
        private final int i;
        MapState(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return Integer.toString(ordinal());
        }
    }

    public Level(boolean randomMap , DiscreteCoordinates startPosition , int[] roomsDistribution , int width , int height) {
        nbRooms = IntStream.of(roomsDistribution).sum();
        if(!randomMap) {
            map = new ICRogueRoom[width][height];
            this.startPosition =  startPosition;
            this.bossPosition = new DiscreteCoordinates(0,0);
        } else {
            generateRandomMap();
        }
//        generateRandomMap();
    }

    private void generateRandomMap() {
        map = new ICRogueRoom[nbRooms][nbRooms];
        generateRandomRoomPlacement();

    }


    protected MapState[][] generateRandomRoomPlacement() {
        MapState[][] mapStates = new MapState[nbRooms][nbRooms];
        for (int i = 0; i < mapStates.length; i++) {
            for (int j = 0; j < mapStates[i].length; j++) {
                mapStates[i][j] = MapState.NULL;
            }
        }
        mapStates[(int)(nbRooms/2)][(int)(nbRooms/2)] = MapState.PLACED;
        int roomsToPlace = nbRooms - 1;

        for (int i = 1; i < mapStates.length-1; i++) {
            for (int j = 1; j < mapStates[i].length-1; j++) {
                if(mapStates[i][j] == MapState.PLACED && mapStates[i][j] != MapState.EXPLORED) {
                    int freeSpots = 0;
                    int[] neighbors = new int[4];
                    for (int k = 0; k < 4; k++) {
                        int x = i - (int) Math.sin((Math.PI*k)/2);
                        int y = j - (int) Math.cos((Math.PI*k)/2);
                        MapState neighbor = mapStates[x][y];
                        if (neighbor == MapState.NULL) {
                            freeSpots++;
                            neighbors[k] = k;
                        }
                    }


                    int max = Math.min(roomsToPlace, freeSpots);
                    final int randomNumber = random.nextInt(0 , max);// TODO: 18.12.22 RandomHelper.roomGenerator.nextInt(borneMin , borneMax)
                    final List<Integer> randomOrder = RandomHelper.chooseKInList(randomNumber, List.of(0,1,2,3));
                    for (int k = 0; k < randomOrder.size(); k++) {
//                        neighbors[randomOrder.get(k)] = MapState.PLACED;
                    }
                    mapStates[i][j] = MapState.EXPLORED;
                }
            }
        }

        printMap(mapStates);
        return mapStates;
    }

    private void printMap(MapState [][] map) {
        System.out.println("Generated map:");
        System.out.print("  | ");
        for (int j = 0; j < map[0].length; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.print("--|-");
        for (int j = 0; j < map[0].length; j++) {
            System.out.print("--");
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void addRooms(AreaGame areaGame) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    areaGame.addArea(map[i][j]);
                }

            }
        }
    }
    public ICRogueRoom getRoom(DiscreteCoordinates coords) {
        return map[coords.x][coords.y];
    }

    public ICRogueRoom[][] getRooms() {
        return map;
    }



    protected void setRoom(DiscreteCoordinates coords, ICRogueRoom room) {
        map[coords.x][coords.y] = room;
    }
    protected void setRoomConnectorDestination(DiscreteCoordinates coords, String destination, ConnectorInRoom connector) {
        map[coords.x][coords.y].connectors.get(connector.getIndex()).destination = destination;
    }
    protected void setRoomConnector(DiscreteCoordinates coords, String destination, ConnectorInRoom connector) {
        map[coords.x][coords.y].connectors.get(connector.getIndex()).setDestination(destination);
        map[coords.x][coords.y].connectors.get(connector.getIndex()).setState(Connector.State.CLOSED);
    }
    protected void lockRoomConnector(DiscreteCoordinates coords, ConnectorInRoom connector, int keyId) {
        map[coords.x][coords.y].connectors.get(connector.getIndex()).setKeyID(keyId);
        map[coords.x][coords.y].connectors.get(connector.getIndex()).setState(Connector.State.LOCKED);

    }
    protected void setCoordinatesToStarterRoomTitle(DiscreteCoordinates coordinates) {
        startingRoomTitle = "icrogue/level0" + coordinates.x + coordinates.y;

    }
}
