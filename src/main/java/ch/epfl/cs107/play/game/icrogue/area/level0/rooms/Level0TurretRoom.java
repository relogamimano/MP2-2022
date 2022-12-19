package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.enemy.Enemy;
import ch.epfl.cs107.play.game.icrogue.actor.enemy.Turret;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level0TurretRoom extends Level0EnemyRoom{
    private final Turret turret1;
    private final Turret turret2;
    public Level0TurretRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);
        turret1 = new Turret(this, new DiscreteCoordinates(1, 8),false);
        turret2 = new Turret(this, new DiscreteCoordinates(8,1),true);
        addActiveEneny(turret1);
        addActiveEneny(turret2);
    }


    @Override
    protected void createArea() {
        super.createArea();
        registerActor(turret1);
        registerActor(turret2);
    }
}
