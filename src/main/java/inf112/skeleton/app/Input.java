package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.geometry.Vector2i;

public class Input {

    public enum State{
        GamePlay,
        Menu;
    }

    public interface StateMachine {
        State getState();
    }

    public static StateMachine stateMachine = null;

    public static int playerMoveY() {
        if ( stateMachine.getState() != State.GamePlay ) {
            return 0;
        }

        int y_dir = 0;
        if (
                Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.W) ||
                Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.UP)
        ) {
            y_dir += 1;
        }
        if (
                Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.S) ||
                Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.DOWN)
        ) {
            y_dir -= 1;
        }
        return y_dir;
    }

    public static int playerMoveX() {
        if ( stateMachine.getState() != State.GamePlay ) {
            return 0;
        }

        int x_dir = 0;
        if (
                Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.D) ||
                Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.RIGHT)
        ) {
            x_dir += 1;
        }
        if (
                Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.A) ||
                Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.LEFT)
        ) {
            x_dir -= 1;
        }
        return x_dir;

    }

    public static Vector2i playerMove() {
        return new Vector2i(playerMoveX(), playerMoveY());
    }
}
