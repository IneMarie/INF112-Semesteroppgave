package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.AmateurInput;
import inf112.skeleton.app.animation.PositionLerp;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.screens.GameScreen;

public class Player implements IEntity {

    private final PositionLerp positionLerp;
    private Vector2i position;
    private final World world;
    private final GameScreen game;
    private static final Texture texture = Textures.Player.texture;


    public Player(Vector2i position, World world, GameScreen game) {
        this.position = position;
        this.world = world;
        positionLerp = new PositionLerp(this);
        this.game = game;
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

        if (checkIfGoalReached()) {
            game.setGameWon();
        }
    }


    private boolean checkIfGoalReached() {
        if (getPosition().x() == 12 && getPosition().y() == 16) {
            return true;
        } else {
            return false;
        }
    }


    public void draw(SpriteBatch sb) {
        Vector2 v2 = positionLerp.getLerpedPosition();
        sb.draw(texture, v2.x, v2.y, 1, 1);
    }

    @Override
    public Texture texture() {
        return texture;
    }

    private State state = State.Normal;

    private static enum State {
        Normal(Flag.Player),
        PoweredUp(Flag.Player, Flag.Explosive),
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
