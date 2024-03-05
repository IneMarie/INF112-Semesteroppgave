package inf112.skeleton.app.world;

import inf112.skeleton.app.geometry.Vector2i;

public interface IWorld {

    /**
     * The world is responsible for determining whether the Entity is allowed to move.
     * @return New position
     */
    Vector2i moveEntity(IEntity entity, Vector2i movement);
}
