package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;

public class Game implements ApplicationListener {
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture spriteImage;
	private Sound bellSound;
	private Rectangle spriteRect;
	private Rectangle screenRect = new Rectangle();
	private float dx = 1, dy = 1;

	@Override
	public void create() {
		// Called at startup

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		spriteImage = new Texture(Gdx.files.internal("obligator.png"));
		spriteRect = new Rectangle(1, 1, spriteImage.getWidth() / 2f, spriteImage.getHeight() / 2f);
		bellSound = Gdx.audio.newSound(Gdx.files.internal("blipp.ogg"));
		Gdx.graphics.setForegroundFPS(60);
	}

	@Override
	public void dispose() {
		// Called at shutdown

		// Graphics and sound resources aren't managed by Java's garbage collector, so
		// they must generally be disposed of manually when no longer needed. But,
		// any remaining resources are typically cleaned up automatically when the
		// application exits, so these aren't strictly necessary here.
		// (We might need to do something like this when loading a new game level in
		// a large game, for instance, or if the user switches to another application
		// temporarily (e.g., incoming phone call on a phone, or something).
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
		Rectangle.tmp.set(spriteRect);
		Rectangle.tmp.x += dx;
		Rectangle.tmp2.set(spriteRect);
		Rectangle.tmp2.y += dy;
		if (screenRect.contains(Rectangle.tmp))
			spriteRect.x += dx;
		else
			dx = -dx;
		if (screenRect.contains(Rectangle.tmp2))
			spriteRect.y += dy;
		else
			dy = -dy;

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

		ScreenUtils.clear(Color.WHITE);

		batch.begin();
		{ // Draw images here
			font.draw(batch, "Hello, World!", 200, 200);
			font.draw(batch, "spriteRect = " + spriteRect.toString(), 10, 20);
			font.draw(batch, "screenRect = " + screenRect.toString(), 10, 40);
			batch.draw(spriteImage, spriteRect.x, spriteRect.y, spriteRect.width, spriteRect.height);
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
		// Called whenever the window is resized (including with its original site at
		// startup)

		screenRect.width = width;
		screenRect.height = height;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
