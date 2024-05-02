package inf112.skeleton.app.animation;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.world.IEntity;

/**
 * PositionLerp calculates a Vector2 representing smoothed out movement for the purposes of drawing moving entities.
 * It watches an entity and linearly interpolates its own position to follow the entities position.
 */
public class PositionLerp {

    IEntity entity;
    Vector2i previous_pos;
    public static final float secondsToMove = 0.1f;
    private boolean isMoving = false;
    private float secondsMoved;
    private Vector2 moveFrom = new Vector2();
    private Vector2 moveTo = new Vector2();
    private Vector2 lerpedPosition;

    public PositionLerp(IEntity entity) {
        this.entity = entity;
        previous_pos = entity.getPosition();
        lerpedPosition = new Vector2(entity.getPosition().x(), entity.getPosition().y());
    }

    /**
     * Looks at this LerpedPosition's IEntity to see if it has moved since the last update.
     * Start's the interpolation process if that is the case.
     * Should be called every frame.
     *
     * @param deltaSeconds time passed since last update call.
     */
    public void update(float deltaSeconds) {
        if (isMoving) {
            if (secondsMoved >= secondsToMove) {
                isMoving = false;
                secondsMoved = secondsToMove;
            }
            var v = moveTo.cpy().sub(moveFrom);
            v.x *= secondsMoved / secondsToMove;
            v.y *= secondsMoved / secondsToMove;
            lerpedPosition.set(moveFrom.cpy().add(v));
            secondsMoved += deltaSeconds;
        }

        if (!previous_pos.equals(entity.getPosition())) {
            secondsMoved = 0.0f;
            isMoving = true;
            moveFrom.set(lerpedPosition);
            moveTo.set(entity.getPosition().x(), entity.getPosition().y());
            previous_pos = entity.getPosition();
        }
    }

    public Vector2 getLerpedPosition() {
        return lerpedPosition;
    }
}
