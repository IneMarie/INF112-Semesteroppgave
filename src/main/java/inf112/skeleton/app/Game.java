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

public class Game implements ApplicationListener {
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture spriteImage;
	private Sound bellSound;
	private CameraController2D camera;

	@Override
	public void create() {
		// Called at startup

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		spriteImage = new Texture(Gdx.files.internal("obligator.png"));
		bellSound = Gdx.audio.newSound(Gdx.files.internal("blipp.ogg"));
		Gdx.graphics.setForegroundFPS(60);

		camera = new CameraController2D(16, 16);
		camera.screenAnchor = new Vector2(0,0);
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
		if (Gdx.input.justTouched()) { // check for mouse click
			bellSound.play();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) { // check for key press
			Gdx.app.exit();
		}

	}

	/**
	 * Called once per frame after update.
	 */
	public void draw() {
		camera.setTarget(new Vector2(0,0));
		camera.beginWorld(batch);

		ScreenUtils.clear(Color.BLACK);

		batch.begin();
		{ // Draw images here
			font.draw(batch, "Hello, World!", 200, 200);
			batch.draw(spriteImage, 0, 0, 1, 1);
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
		camera.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
