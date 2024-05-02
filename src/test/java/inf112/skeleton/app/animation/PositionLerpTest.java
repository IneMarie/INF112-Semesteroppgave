package inf112.skeleton.app.animation;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.world.IEntity;
import org.junit.jupiter.api.Test;

class PositionLerpTest {

    @Test
    void update() {
        IEntity entity = IEntity.dummy;
        final var posLerp = new PositionLerp(entity);
        final var targetI = new Vector2i(100, 100);
        final var targetF = new Vector2(targetI.x(), targetI.y());

        entity.setPosition(new Vector2i(100, 100));
        posLerp.update(0f); // Interpolation happens on the next frame.

        for (int i = 0; i < 100; i++) {
            posLerp.update(PositionLerp.secondsToMove / 100f);
            assert !posLerp.getLerpedPosition().equals(targetF);
        }

        posLerp.update(PositionLerp.secondsToMove);
        assert posLerp.getLerpedPosition().equals(targetF);
        posLerp.update(PositionLerp.secondsToMove); // should never move after this
        assert posLerp.getLerpedPosition().equals(targetF);
        posLerp.update(PositionLerp.secondsToMove);
        assert posLerp.getLerpedPosition().equals(targetF);
    }
}