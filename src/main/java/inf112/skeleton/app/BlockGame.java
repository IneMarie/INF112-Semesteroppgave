package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.world.World;


public class BlockGame implements ApplicationListener, Input.StateMachine {
    private SpriteBatch batch;
    private CameraController2D worldCamera;
    private CameraController2D uiCamera;
    private Input.State inputState;
    private World world;

    @Override
    public void create() {
        // Called at startup

        batch = new SpriteBatch();
        Gdx.graphics.setForegroundFPS(60);

        worldCamera = new CameraController2D(16);
        worldCamera.screenAnchor.x = 0f;
        worldCamera.screenAnchor.y = 0f;

        uiCamera = new CameraController2D(720);
        uiCamera.screenAnchor.x = 0f;
        uiCamera.screenAnchor.y = 0f;


        // Endre "lavaRiseTimer" variablen for å endre hvor mange sekunder det tar før lavaen stiger :)
        // lava = new Lava(0, 5, player, this);

        Input.stateMachine = this;
        inputState = Input.State.GamePlay;

        world = new World(this);
    }

    @Override
    public void dispose() {
        // Called at shutdown
        // Unload assets

        batch.dispose();
        Textures.dispose();
    }

    /**
     * Updates the game state before drawing.
     *
     * @param deltaSeconds Time passed since last frame.
     */
    public void update(float deltaSeconds) {
        // Don't handle input this way – use event handlers!
        world.update(deltaSeconds);

//        if (playerIsAlive) {
//            player.update(deltaSeconds);
//            lava.updateLava(deltaSeconds);
//        }
    }

    /**
     * Called once per frame after update.
     */
    public void draw() {
        ScreenUtils.clear(Color.BLACK);

        worldCamera.begin(batch);
        batch.begin();
        { // Draw world
            world.draw(batch);
            // lava.draw(batch);
        }
        batch.end();

        uiCamera.begin(batch);
        batch.begin();
        { // Draw ui
        }
        batch.end();
    }

    /**
     * The main loop of the game.
     * Gets called every frame by libGDX.
     */
    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        draw();
    }

    @Override
    public void resize(int width, int height) {
        worldCamera.onResize(width, height);
        uiCamera.onResize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public Input.State getState() {
        return inputState;
    }

    // Getter metode for batch
    public SpriteBatch getBatch() {
        return batch;
    }

    public void setGameOver() {
    }
}
