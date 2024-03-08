package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Input;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;

public class Player implements IEntity {

    private Vector2 screenPosition;
    private Vector2i position;
    private IWorld world;
    private static final Texture texture = Textures.Player.texture;


    public Player(Vector2i position, IWorld world) {
        this.position = position;
        this.world = world;
        screenPosition = new Vector2(position.x(), position.y());
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



    private static final float secondsToMove = 0.1f;
    private boolean isMoving = false;
    private float secondsMoved;
    private Vector2 moveFrom = new Vector2();
    private Vector2 moveTo = new Vector2();
    public void update(float deltaSeconds) {

        if (isMoving) {
            if (secondsMoved >= secondsToMove) {
                isMoving = false;
                secondsMoved = secondsToMove;
            }
            var v = moveTo.cpy().sub(moveFrom);
            v.x *= secondsMoved / secondsToMove;
            v.y *= secondsMoved / secondsToMove;
            screenPosition.set(moveFrom.cpy().add(v));
            secondsMoved += deltaSeconds;
        }

        Vector2i input = Input.playerMove();

        Vector2i movement = input;
        if (input.x() != 0) {
             movement = new Vector2i(input.x(), 0);
        } else if (input.y() != 0) {
            movement = new Vector2i(0, input.y());
        }

        var new_position = world.moveEntity(this, movement);
        

        if (!position.equals(new_position)) {
            secondsMoved = 0.0f;
            isMoving = true;
            moveFrom.set(screenPosition);
            moveTo.set(new_position.x(), new_position.y());
            position = new_position;
            // Prints position in terminal for each move player makes (testing), TODO: remove when finished!
            System.out.println(position);
        }
    }

    public void draw(SpriteBatch sb) {
        sb.draw(texture, screenPosition.x, screenPosition.y, 1, 1);
    }


    @Override
    public Tile getTile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTile'");
    }

}
