package ch.epfl.cs107.play.game.icrogue.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCell;
import ch.epfl.cs107.play.game.icrogue.actor.Connector;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.actor.items.Cherry;
import ch.epfl.cs107.play.game.icrogue.actor.items.Key;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
public interface ICRogueInteractionHandler extends AreaInteractionVisitor {
    default void interactWith(Connector other, boolean isCellInteraction) {}
    default void interactWith(Cherry other, boolean isCellInteraction){}
    default void interactWith(Staff other, boolean isCellInteraction){}
    default void interactWith(Key other, boolean isCellInteraction) {}
    default void interactWith(ICRogueCell other, boolean isCellInteraction){}
    default void interactWith(ICRoguePlayer other, boolean isCellInteraction){}


}
