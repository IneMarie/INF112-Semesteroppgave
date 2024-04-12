package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.animation.PositionLerp;
import inf112.skeleton.app.geometry.Vector2i;

public class Entity implements IEntity {

    private Vector2i position;
    private Tile tile;
    private final PositionLerp positionLerp;

    public Entity(Tile tile, Vector2i position) {
        this.position = position;
        this.tile = tile;
        positionLerp = new PositionLerp(this);
    }

    public void setPosition(Vector2i position) {
        this.position = position;
    }

    @Override
    public Vector2i getPosition() {
        return position;
    }


    public void draw(SpriteBatch sb) {
        Vector2 v2 = positionLerp.getLerpedPosition();
        sb.draw(tile.texture, v2.x, v2.y, 1, 1);
    }

    @Override
    public Texture texture() {
        return tile.texture;
    }

    @Override
    public Flag.Group flags() {
        return tile.flags;
    }

    @Override
    public void update(float deltaSeconds) {
        positionLerp.update(deltaSeconds);
    }
}
