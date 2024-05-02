package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;

/**
 * The interface of moving entities within a World.
 */
public interface IEntity {

    /**
     * May be used as a dummy value to avoid null-checks everywhere.
     */
    IEntity dummy = new IEntity() {

        Vector2i position = Vector2i.zero;

        @Override
        public Vector2i getPosition() {
            return position;
        }

        @Override
        public void setPosition(Vector2i new_position) {
            position = new_position;
        }

        @Override
        public void update(float deltaSeconds) {
            throw new RuntimeException("The IEntity dummy object should not be updated");
        }

        @Override
        public void draw(SpriteBatch batch) {
            throw new RuntimeException("The IEntity dummy object should not be drawn");
        }

        @Override
        public Texture texture() {
            return Textures.Missing.texture;
        }

        @Override
        public Flag.Group flags() {
            return Flag.Group.None;
        }
    };

    static IEntity BasicEntity(Vector2i pos, Tile tile) {
        return new Entity(tile, pos);
    }

    Vector2i getPosition();

    void setPosition(Vector2i new_position);

    void update(float deltaSeconds);

    void draw(SpriteBatch batch);

    Texture texture();

    Flag.Group flags();
}



