package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.geometry.Vector2i;

public interface IEntity {
    Vector2i getPosition();

    void setPosition(Vector2i new_position);

    void update(float deltaSeconds);

    void draw(SpriteBatch batch);

    Texture texture();

    Flag.Group flags();
}



