package ch.epfl.cs107.play.game.icrogue.actor.enemy;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Arrow;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import static ch.epfl.cs107.play.game.areagame.actor.Orientation.UP;

public class Turret extends Enemy {
    private final static float COOLDOWN = 20.0f;
    private int count;
    private boolean isNorthWestOrientated;
    public Turret(Area area, DiscreteCoordinates initialPosition, boolean isNorthWestOrientated) {
        super(area, UP , initialPosition);
        this.isNorthWestOrientated = isNorthWestOrientated;

        setSprite(new Sprite("icrogue/static_npc", 1.5f, 1.5f,
                this , null , new Vector(-0.25f, 0)));

    }
    @Override
    public void update(float deltaTime) {
        count++;
        if (count > COOLDOWN) {
            count = 0;
            if(isNorthWestOrientated) {
                Arrow arrowLeft = new Arrow(getOwnerArea(), getOrientation().hisLeft(), getCurrentMainCellCoordinates());
                arrowLeft.enterArea(getOwnerArea(), getCurrentMainCellCoordinates());
                Arrow arrow = new Arrow(getOwnerArea(), getOrientation(), getCurrentMainCellCoordinates());
                arrow.enterArea(getOwnerArea(), getCurrentMainCellCoordinates());
            } else {
                Arrow arrowLeft = new Arrow(getOwnerArea(), getOrientation().hisRight(), getCurrentMainCellCoordinates());
                arrowLeft.enterArea(getOwnerArea(), getCurrentMainCellCoordinates());
                Arrow arrow = new Arrow(getOwnerArea(), getOrientation().opposite(), getCurrentMainCellCoordinates());
                arrow.enterArea(getOwnerArea(), getCurrentMainCellCoordinates());
            }
        }
        //if()

        super.update(deltaTime);
    }
/*     TENTATIVES:
        for(count = 0 , count < , count++){
            float dt = count/2.0f;
            if (dt == COOLDOWN){
                Arrow arrow = new Arrow(getOwnerArea(),getOrientation(),getCurrentMainCellCoordinates());
                arrow.enterArea(getOwnerArea(), getCurrentMainCellCoordinates());
            }



       do{
            Arrow arrow = new Arrow(getOwnerArea(),getOrientation(),getCurrentMainCellCoordinates());
            arrow.enterArea(getOwnerArea(), getCurrentMainCellCoordinates());
         } while(dt  2.f);

         super.update(deltaTime); //bloque le programme il n'affiche plus rien (probable bug avec enterArea)
    */





}