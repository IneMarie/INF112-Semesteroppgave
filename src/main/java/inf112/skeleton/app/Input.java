package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.geometry.Vector2i;

public class Input {


    public static int playerMoveY() {
        int y_dir = 0;
        y_dir += Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.W) ? 1 : 0;
        y_dir -= Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.S) ? 1 : 0;
        return y_dir;
    }

    public static int playerMoveX() {
        int x_dir = 0;
        x_dir += Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.D) ? 1 : 0;
        x_dir -= Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.A) ? 1 : 0;
        return x_dir;

    }

    public static Vector2i playerMove() {
        return new Vector2i(playerMoveX(), playerMoveY());
    }
}
