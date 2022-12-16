package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.icrogue.actor.enemy.Enemy;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;

public class Level0EnemyRoom extends Level0Room {

    final private List<Enemy> activeEnemies;

    public Level0EnemyRoom(DiscreteCoordinates roomCoordinates, ArrayList<Enemy> activeEnemies) {
        super(roomCoordinates);
        this.activeEnemies = activeEnemies;

    }
    protected void addActiveEneny(Enemy activeEnemy) {
        activeEnemies.add(activeEnemy);
    }
    public boolean isResolved() {
        return activeEnemies.isEmpty();
    }

    @Override
    protected void createArea() {
        for (Enemy enemy: activeEnemies) {
//            if (!isEnemyDead()) {
//                registerActor(enemy);
//            } else {
//                unregisterActor(enemy);
//            }

        }
        super.createArea();

    }
}
