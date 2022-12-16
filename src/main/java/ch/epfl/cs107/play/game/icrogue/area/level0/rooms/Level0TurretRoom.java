package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.icrogue.actor.enemy.Enemy;
import ch.epfl.cs107.play.game.icrogue.actor.enemy.Turret;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import java.util.ArrayList;

public class Level0TurretRoom extends Level0EnemyRoom{
    private final Turret turret1;
    private final Turret turret2;
    public Level0TurretRoom(DiscreteCoordinates roomCoordinates, ArrayList<Enemy> activeEnemies) {
        super(roomCoordinates, activeEnemies);
        turret1 = new Turret(this, new DiscreteCoordinates(1, 8));
        turret2 = new Turret(this, new DiscreteCoordinates(8,1));
    }

    @Override
    protected void createArea() {
        super.createArea();
        registerActor(turret1);
        registerActor(turret2);
    }
}
