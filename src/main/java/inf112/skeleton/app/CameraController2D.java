package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Abstraction on top of Camera and Viewport.
 * use: setTarget, zoomToFit and screenAnchor to control view of the player.
 */
public class CameraController2D {

    /**
     * x and y should both be in the inclusive range from 0 to 1.
     * <p></p>
     * <p>
     *     <b>Examples:</b>
     *     <ul>
     *         <li>Vector2( 0.5, 0.5 ) places the middle of the game-window on the camera's target.</li>
     *         <li>Vector2( 0.0, 0.0 ) places the bottom-left corner of the game-window on the camera's target.</li>
     *         <li>Vector2( 1.0, 0.5 ) places the middle right side of the game-window on the camera's target.</li>
     *     </ul>
     * </p>
     */
    public Vector2 screenAnchor = new Vector2(.5f, .5f);
    private final Camera camera;
    private final Viewport viewport;

    /**
     * The given dimensions will be scaled up to fit the window size dynamically
     * while this CameraController2D is in use.
     */
    public CameraController2D(int worldWidth, int worldHeight) {
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(worldWidth, worldHeight, camera);
    }


    /**
     * The given dimensions will be scaled up to fit the window size dynamically
     * while this CameraController2D is in use.
     * @param worldSize Will be used as both width and height.
     */
    public CameraController2D(int worldSize) {
        this(worldSize, worldSize);
    }

    /**
     * Call before any drawing operations for the world of the game.
     * Player, map, enemies, etc.
     */
    public void begin(SpriteBatch spriteBatch) {
        viewport.apply();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    public void setTarget(Vector2 position) {
        camera.position.x = position.x + viewport.getWorldWidth() * (screenAnchor.x + .5f);
        camera.position.y = position.y + viewport.getWorldHeight() * (screenAnchor.y + .5f);
    }

    /**
     * Sets the Zoom level to fit the given width and height exactly in the window.
     * @param width of the desired view of the world
     * @param height of the desired view of the world
     */
    public void zoomToFit(float width, float height) {
        viewport.setWorldSize(width, height);
    }

    private static final Vector2 v = new Vector2();
    public Vector2 mousePosition() {
        v.x = Gdx.input.getX();
        v.y = Gdx.input.getY();
        return viewport.unproject(v);
    }

    /**
     * Must call when the game window is resized for the rest of the methods to work correctly.
     * @param width of the window
     * @param height of the window
     */
    public void onResize(int width, int height) {
        this.viewport.update(width, height, true);
    }
}
