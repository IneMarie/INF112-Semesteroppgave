package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.geometry.Vector2i;

public class Wall implements IEntity {

    private Vector2i position;

    public Wall(Vector2i position) {
        this.position = position;
    }

    @Override
    public Vector2i getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2i new_position) {
        this.position = new_position;
    }

    @Override
    public Tile getTile() {
        return Tile.Wall;
    }

    @Override
    public void update(float deltaSeconds) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                Tile.Wall.texture,
                position.x(),
                position.y(),
                1,
                1
        );
    }
}
