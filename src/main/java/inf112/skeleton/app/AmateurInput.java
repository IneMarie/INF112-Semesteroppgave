package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
 *     <li>To add a layer between the actual input and the program to programmatically simulate key presses.</li>
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

    /**
     * Programmatically press key.
     * Useful for testing.
     *
     * @param key the key to press
     */
    public static void press(int key) {
        prevKeyPressed[key] = false;
        currKeyPressed[key] = true;
    }

    /**
     * Programmatically release key.
     * Useful for testing.
     *
     * @param key the key to press
     */
    public static void release(int key) {
        prevKeyPressed[key] = true;
        currKeyPressed[key] = false;
    }

    /**
     * Programmatically hold key.
     * Useful for testing.
     *
     * @param key the key to press
     */
    public static void hold(int key) {
        prevKeyPressed[key] = true;
        currKeyPressed[key] = true;
    }

    /**
     * Check if the given key was just pressed on this frame.
     *
     * @param key the key to check
     * @return true if the key is down this frame and was up the previous frame, false otherwise
     */
    public static boolean isKeyJustPressed(int key) {
        return (!prevKeyPressed[key]) && currKeyPressed[key];
    }

    /**
     * Check if the given key was just released on this frame.
     *
     * @param key the key to check
     * @return true if the key is up this frame and was down the previous frame, false otherwise
     */
    public static boolean isKeyJustReleased(int key) {
        return prevKeyPressed[key] && (!currKeyPressed[key]);
    }

    /**
     * Checks if the given key is down.
     *
     * @param key the key to check
     * @return true if the key is down now.
     */
    public static boolean isKeyPressed(int key) {
        return currKeyPressed[key];
    }

    /**
     * A simple abstraction on to define the controls of player movement.
     *
     * @return 1 if the player is trying to move up, -1 if the player is trying to move down and 0 if the player is not
     * trying to move
     */
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

    /**
     * A simple abstraction on to define the controls of player movement.
     *
     * @return 1 if the player is trying to move right, -1 if the player is trying to move left and 0 if the player is not
     * trying to move
     */
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

    /**
     * A simple abstraction on to define the controls of player movement.
     *
     * @return a vector representing the direction the player is trying to move, or the zero-vector if the player is not
     * trying to move
     */
    public static Vector2i playerMove() {
        return new Vector2i(playerMoveX(), playerMoveY());
    }


    private static final BitmapFont font = new BitmapFont();

}
