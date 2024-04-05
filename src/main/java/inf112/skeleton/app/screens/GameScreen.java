package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.skeleton.app.CameraController2D;
import inf112.skeleton.app.Input;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.world.World;


public class GameScreen implements Input.StateMachine, Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private Sound bellSound;
    private CameraController2D worldCamera;
    private CameraController2D uiCamera;
    private Input.State inputState;
    private final ScreenManager screenManager;
    private World world;


    public GameScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        bellSound = Gdx.audio.newSound(Gdx.files.internal("blipp.ogg"));
        Gdx.graphics.setForegroundFPS(60);

        worldCamera = new CameraController2D(16);
        worldCamera.screenAnchor.x = 0f;
        worldCamera.screenAnchor.y = -0.5f;

        uiCamera = new CameraController2D(720);
        uiCamera.screenAnchor.x = 0f;
        uiCamera.screenAnchor.y = 0f;

        Input.stateMachine = this;
        inputState = Input.State.GamePlay;

        this.world = new World(this);

    }

    @Override
    public void dispose() {
        // Called at shutdown
        // Unload assets

        batch.dispose();
        font.dispose();
        bellSound.dispose();
        Textures.dispose();
    }

    /**
     * Updates the game state before drawing.
     *
     * @param deltaSeconds Time passed since last frame.
     */
    public void update(float deltaSeconds) {
        world.update(deltaSeconds);
        var v = new Vector2(0, world.getPlayerScreenPosition().y);
        worldCamera.setTarget(v);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);

        ScreenUtils.clear(Color.BLACK);

        worldCamera.begin(batch);
        batch.begin();
        { // Draw world
            world.draw(batch);
        }
        batch.end();

        uiCamera.begin(batch);
        batch.begin();
        { // Draw ui
        }
        batch.end();

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
    public void hide() {

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
        System.out.println("Spilleren døde av lava :(");
        screenManager.showGameOverScreen();
    }
}
