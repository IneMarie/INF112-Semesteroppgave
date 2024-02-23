package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jdk.jshell.spi.ExecutionControl;

public class CameraController2D {

    /**
     * x and y should be in range 0 to 1
     * Vector2(.5f,.5f) would point the middle of the screen to the camera's target.
     */
    public Vector2 screenAnchor = new Vector2(.5f, .5f);
    private final Camera camera;
    private final Viewport viewportWorld;

    //TODO possibly implement a separate viewport to draw the ui with pixel coordinates instead of world coordinates.
    private Viewport viewportUI;

    public CameraController2D(int worldWidth, int worldHeight) {
        this.camera = new OrthographicCamera();
        this.viewportWorld = new FitViewport(worldWidth, worldHeight, camera);
    }

    public void beginWorld(SpriteBatch spriteBatch) {
        viewportWorld.apply();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    public void beginUI(SpriteBatch spriteBatch) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("UI draw mode not implemented.");
    }

    public void setTarget(Vector2 position) {
        var v = new Vector2(
                position.x + viewportWorld.getWorldHeight() * (screenAnchor.x + .5f),
                position.y + viewportWorld.getWorldWidth() * (screenAnchor.y + .5f)
        );
        camera.position.x = v.x;
        camera.position.y = v.y;
    }

    /**
     * Sets the Zoom level to fit the given width and height exactly in the window.
     * @param width of the desired view of the world
     * @param height of the desired view of the world
     */
    public void zoomToFit(float width, float height) {
        viewportWorld.setWorldSize(width, height);
    }

    /**
     * Must call when the game window is resized for the rest of the methods to work correctly.
     * @param width of the window
     * @param height of the window
     */
    public void resize(int width, int height) {
        this.viewportWorld.update(width, height, true);
    }
}
