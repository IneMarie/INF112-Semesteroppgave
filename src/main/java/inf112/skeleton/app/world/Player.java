package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.AmateurInput;
import inf112.skeleton.app.animation.PositionLerp;
import inf112.skeleton.app.assets.SoundEffect;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;

/**
 * A special entity controlled by the player of the game.
 * It defines much of its own logic, but it still has to ask the world to move itself by use of World.moveEntity.
 */
public class Player implements IEntity {

    private final PositionLerp positionLerp;
    private Vector2i position;
    private final World world;

    private State state = State.Normal;
    private float powerUpTimer = 0f;
    


    public Player(Vector2i position, World world) {
        this.position = position;
        this.world = world;
        positionLerp = new PositionLerp(this);
    }

    /**
     * Sets the Player state to PoweredUp.
     * @param durationSeconds number of seconds the power-up state will be active
     */
    public void powerUp(float durationSeconds) {
        state = State.PoweredUp;
        powerUpTimer = durationSeconds;
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


        Vector2i input = AmateurInput.playerMove();

        Vector2i movement = input;
        if (input.x() != 0) {
            movement = new Vector2i(input.x(), 0);
        } else if (input.y() != 0) {
            movement = new Vector2i(0, input.y());
        }

        world.moveEntity(this, movement);
        positionLerp.update(deltaSeconds);

        if (state.equals(State.PoweredUp) && powerUpTimer > 0f) {
            powerUpTimer -= deltaSeconds;
        } else {
            state = State.Normal;
        }
    }

    public void draw(SpriteBatch sb) {
        Vector2 v2 = positionLerp.getLerpedPosition();
        sb.draw(Textures.Player.texture, v2.x, v2.y, 1, 1);
        if (state.equals(State.PoweredUp)) {
            sb.draw(Textures.HammerPlayerImage.texture, v2.x, v2.y, 1, 1);
        }
    }

    @Override
    public Texture texture() {
        return Textures.Player.texture;
    }


    private enum State {
        Normal(Flag.Player),
        PoweredUp(Flag.Player, Flag.Breaking),
        ;

        private final Flag.Group flags;

        State(Flag... flags) {
            this.flags = Flag.join(flags);
        }
    }

    @Override
    public Flag.Group flags() {
        return state.flags;
    }

}
