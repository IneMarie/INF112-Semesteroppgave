package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.geometry.Vector2i;

/**
 * The Input module of libgdx is broken on my machine.
 * While playing and printing the output of some of the functions, I found that Gdx.Input.isKeyJustPressed did not
 * always work.
 * The return value from Gdx.Input.isKeyPressed would go from false to true on a frame without the call to
 * Gdx.Input.isKeyJustPressed returning true many times.
 * <p>
 * So the class exists for a couple of reasons:
 * <ul>
 *     <li>To recreate the isKeyJustPressed function by using the isKeyPressed function from libgdx</li>
 *     <li>To add isKeyJustReleased</li>
 * </ul>
 */
public class AmateurInput {


    private static final boolean[] prevKeyPressed = new boolean[com.badlogic.gdx.Input.Keys.MAX_KEYCODE];
    private static final boolean[] currKeyPressed = new boolean[com.badlogic.gdx.Input.Keys.MAX_KEYCODE];

    /**
     * Should be called every frame regardless of the current game-state.
     * gdx.Input.isKeyJustPressed is broken on my machine, and only works like 80% of the time,
     * while isKeyPressed always registers correctly.
     * So use isKeyJustPressed, isKeyPressed and isKeyJustReleased from this class instead.
     * This update is required to keep track of the current states of all the keys.
     */
    public static void update() {
        for (int key = 0; key < com.badlogic.gdx.Input.Keys.MAX_KEYCODE; key++) {
            prevKeyPressed[key] = currKeyPressed[key];
            currKeyPressed[key] = Gdx.input.isKeyPressed(key);
        }
    }

    public static boolean isKeyJustPressed(int key) {
        return (!prevKeyPressed[key]) && currKeyPressed[key];
    }

    public static boolean isKeyJustReleased(int key) {
        return prevKeyPressed[key] && (!currKeyPressed[key]);
    }

    public static boolean isKeyPressed(int key) {
        return currKeyPressed[key];
    }

    public static int playerMoveY() {

        int y_dir = 0;
        if (
                isKeyJustPressed(com.badlogic.gdx.Input.Keys.W) ||
                        isKeyJustPressed(com.badlogic.gdx.Input.Keys.UP)
        ) {
            y_dir += 1;
        }
        if (
                isKeyJustPressed(com.badlogic.gdx.Input.Keys.S) ||
                        isKeyJustPressed(com.badlogic.gdx.Input.Keys.DOWN)
        ) {
            y_dir -= 1;
        }
        return y_dir;
    }

    public static int playerMoveX() {

        int x_dir = 0;
        if (
                isKeyJustPressed(com.badlogic.gdx.Input.Keys.D) ||
                        isKeyJustPressed(com.badlogic.gdx.Input.Keys.RIGHT)
        ) {
            x_dir += 1;
        }
        if (
                isKeyJustPressed(com.badlogic.gdx.Input.Keys.A) ||
                        isKeyJustPressed(com.badlogic.gdx.Input.Keys.LEFT)
        ) {
            x_dir -= 1;
        }
        return x_dir;

    }

    public static Vector2i playerMove() {
        return new Vector2i(playerMoveX(), playerMoveY());
    }


    private static final BitmapFont font = new BitmapFont();

    public static void debug(SpriteBatch sb, float x, float y) {
        font.draw(sb, "isRightJustPressed: %b\nisRightPressed    : %b".formatted(
                isKeyJustPressed(com.badlogic.gdx.Input.Keys.RIGHT),
                isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)
        ), x, y);
    }
}
