package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;

public interface IEntity {

    IEntity dummy = new IEntity() {
        @Override
        public Vector2i getPosition() {
            return Vector2i.zero;
        }

        @Override
        public void setPosition(Vector2i new_position) {}

        @Override
        public void update(float deltaSeconds) {}

        @Override
        public void draw(SpriteBatch batch) {}

        @Override
        public Texture texture() {
            return Textures.Player.texture;
        }

        @Override
        public Flag.Group flags() {
            return Flag.Group.None;
        }
    };

    Vector2i getPosition();

    void setPosition(Vector2i new_position);

    void update(float deltaSeconds);

    void draw(SpriteBatch batch);

    Texture texture();

    Flag.Group flags();
}



