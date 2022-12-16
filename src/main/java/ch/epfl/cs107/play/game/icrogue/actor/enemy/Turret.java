package ch.epfl.cs107.play.game.icrogue.actor.enemy;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.actor.enemy.Enemy;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import static ch.epfl.cs107.play.game.areagame.actor.Orientation.DOWN;

public class Turret extends Enemy {
    public Turret(Area area, DiscreteCoordinates initialPosition) {
        super(area, DOWN , initialPosition);
        setSprite(new Sprite("icrogue/static_npc", 1.5f, 1.5f,
                this , null , new Vector(-0.25f, 0)));
    }



}
