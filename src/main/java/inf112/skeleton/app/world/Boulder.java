package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.animation.PositionLerp;
import inf112.skeleton.app.geometry.Vector2i;


// TODO: move-method

public class Boulder implements IEntity {


    private Vector2i position;
    private World world;
    private PositionLerp positionLerp;


    public Boulder(Vector2i position, World world) {
        this.position = position;
        this.world = world;
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
        sb.draw(Tile.Boulder.texture, v2.x, v2.y, 1, 1);
    }


    @Override
    public Tile getTile() {
        return Tile.Boulder;
    }

    @Override
    public void update(float deltaSeconds) {
        positionLerp.update(deltaSeconds);
    }


}
