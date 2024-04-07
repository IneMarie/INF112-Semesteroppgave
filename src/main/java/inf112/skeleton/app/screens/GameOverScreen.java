package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.AmateurInput;


public class GameOverScreen implements Screen {
    private final ScreenManager screenManager;
    private final BitmapFont font;
    private final SpriteBatch batch;

    public GameOverScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        font = new BitmapFont();
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Tegner tekst
        batch.begin();
        font.setColor(Color.WHITE);
        float x = Gdx.graphics.getWidth() / 2f - 100;
        float y = Gdx.graphics.getHeight() / 2f;
        font.getData().setScale(2, 2);
        font.draw(batch, "Denne teksten e cursed -- FIKS", x, y); // Fikset skaleringen
        float secondLineY = y - font.getLineHeight();
        font.draw(batch, "Press ENTER to try again!", x, secondLineY);

        batch.end();

        // Sjekker om Enter
        if (AmateurInput.isKeyJustPressed(Input.Keys.ENTER)) {
            screenManager.restartGame();
            screenManager.showGameScreen();
        }
    }

    @Override
    public void resize(int width, int height) {
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
        font.dispose();
        batch.dispose();
    }
}
