package inf112.skeleton.app.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.skeleton.app.AmateurInput;
import inf112.skeleton.app.CameraController2D;
import inf112.skeleton.app.assets.Textures;

public class WinScreen implements Screen {
    private final ScreenManager screenManager;
    private final SpriteBatch batch;
    private CameraController2D uiCamera;
    private static final Texture win = Textures.Win.texture;

    public WinScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        batch = new SpriteBatch();

        uiCamera = new CameraController2D(720);
        uiCamera.screenAnchor.x = 0f;
        uiCamera.screenAnchor.y = 0f;

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(0x090909ff));

        // Tegner tekst
        batch.begin();

        batch.draw(win, 0, 0, 1419 / 2, 1515 / 2);

        batch.end();

        uiCamera.begin(batch);

        // Sjekker om Enter
        if (AmateurInput.isKeyJustPressed(Input.Keys.ENTER)) {
            screenManager.restartGame();
            screenManager.showGameScreen();
        }

    }

    @Override
    public void resize(int width, int height) {
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
    public void dispose() {
        batch.dispose();
    }
}

