package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.geometry.Vector2i;

public interface IWorld {

    /**
     * The world is responsible for determining whether the Entity is allowed to move.
     *
     * @return true if entity moved.
     */
    boolean moveEntity(IEntity entity, Vector2i movement);

    void update(float deltaSeconds);

    void draw(SpriteBatch batch);
}
