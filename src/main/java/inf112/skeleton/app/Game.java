package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.world.IEntity;
import inf112.skeleton.app.world.IWorld;
import inf112.skeleton.app.world.Lava;
import inf112.skeleton.app.world.Player;
import inf112.skeleton.app.world.Tile;

public class Game implements ApplicationListener, IWorld, Input.StateMachine {
	private SpriteBatch batch;
	private BitmapFont font;
	private Sound bellSound;
	private CameraController2D worldCamera;
	private CameraController2D uiCamera;
	private Input.State inputState;
	Player player;
  private Lava lava;

	@Override
	public void create() {
		// Called at startup

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);

		bellSound = Gdx.audio.newSound(Gdx.files.internal("blipp.ogg"));
		Gdx.graphics.setForegroundFPS(60);

		worldCamera = new CameraController2D(16);
		worldCamera.screenAnchor.x = 0f;
		worldCamera.screenAnchor.y = 0f;

		uiCamera = new CameraController2D(720);
		uiCamera.screenAnchor.x = 0f;
		uiCamera.screenAnchor.y = 0f;

		player = new Player(new Vector2i(0, 0), this);

    // Endre "lavaRiseTimer" variablen for å endre hvor mange sekund det tar før lavaen stiger :)
    lava = new Lava(0, 10);

		Input.stateMachine = this;
		inputState = Input.State.GamePlay;
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
	 * @param deltaSeconds Time passed since last frame.
	 */
	public void update(float deltaSeconds) {
		// Don't handle input this way – use event handlers!
		player.update(deltaSeconds);

    lava.updateLava(deltaSeconds);
		
	}

	/**
	 * Called once per frame after update.
	 */
	public void draw() {
		ScreenUtils.clear(Color.BLACK);

		worldCamera.begin(batch);
		batch.begin();
		{ // Draw world
			player.draw(batch);
			Tile.Snake.draw(batch, Vector2.Zero);
      lava.draw(batch);
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
	public Vector2i moveEntity(IEntity entity, Vector2i movement) {
		return entity.getPosition().add(movement);
	}

	@Override
	public Input.State getState() {
		return inputState;
	}

  // Getter metode for batch
  public SpriteBatch getBatch() {
    return batch;
  }
}
