package inf112.skeleton.app.animation;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.world.IEntity;

public class PositionLerp {

    IEntity entity;
    Vector2i previous_pos;
    private static final float secondsToMove = 0.1f;
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
