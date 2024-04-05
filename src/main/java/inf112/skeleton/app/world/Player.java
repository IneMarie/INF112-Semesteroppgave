package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Input;
import inf112.skeleton.app.animation.PositionLerp;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;

public class Player implements IEntity {

    private PositionLerp positionLerp;
    private Vector2i position;
    private World world;
    private static final Texture texture = Textures.Player.texture;


    public Player(Vector2i position, World world) {
        this.position = position;
        this.world = world;
        positionLerp = new PositionLerp(this);
    }


    public Vector2 getScreenPosition() {
        return positionLerp.getLerpedPosition();
    }

    public void setPosition(Vector2i position) {
        this.position = position;
    }

    public Vector2i getPosition() {
        return position;
    }

    public int getRow() {
        return position.y();
    }


    public void update(float deltaSeconds) {


        Vector2i input = Input.playerMove();

        Vector2i movement = input;
        if (input.x() != 0) {
            movement = new Vector2i(input.x(), 0);
        } else if (input.y() != 0) {
            movement = new Vector2i(0, input.y());
        }

        world.moveEntity(this, movement);
        positionLerp.update(deltaSeconds);
    }

    public void draw(SpriteBatch sb) {
        Vector2 v2 = positionLerp.getLerpedPosition();
        sb.draw(texture, v2.x, v2.y, 1, 1);
    }


    @Override
    public Tile getTile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTile'");
    }

}
