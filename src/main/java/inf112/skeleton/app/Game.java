package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.world.IEntity;
import inf112.skeleton.app.world.IWorld;
import inf112.skeleton.app.world.Player;

public class Game implements ApplicationListener, IWorld {
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture spriteImage;
	private Sound bellSound;
	private CameraController2D worldCamera;
	private CameraController2D uiCamera;
	Player player;

	@Override
	public void create() {
		// Called at startup

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);

		spriteImage = new Texture(Gdx.files.internal("Dungeon Crawl Stone Soup Full/player/base/orc_male.png"));
		bellSound = Gdx.audio.newSound(Gdx.files.internal("blipp.ogg"));
		Gdx.graphics.setForegroundFPS(60);

		worldCamera = new CameraController2D(16);
		worldCamera.screenAnchor.x = 0f;
		worldCamera.screenAnchor.y = 0f;

		uiCamera = new CameraController2D(720);
		uiCamera.screenAnchor.x = 0f;
		uiCamera.screenAnchor.y = 0f;

		player = new Player(new Vector2i(0, 0), this);

	}

	@Override
	public void dispose() {
		// Called at shutdown
		// Unload assets

		batch.dispose();
		font.dispose();
		spriteImage.dispose();
		bellSound.dispose();
	}

	/**
	 * Updates the game state before drawing.
	 * @param deltaSeconds Time passed since last frame.
	 */
	public void update(float deltaSeconds) {
		// Don't handle input this way – use event handlers!
		player.update(deltaSeconds);
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
}
