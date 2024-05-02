package inf112.skeleton.app.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import inf112.skeleton.app.AmateurInput;

public class ScreenManager extends Game {

    private Screen titleScreen;
    private Screen gameScreen;
    private Screen gameOverScreen;
    private Screen winScreen;
    private Screen infoScreen;

    @Override
    public void create() {

        // Creates screens
        restartGame();
        titleScreen = new TitleScreen(this);
        infoScreen = new InfoScreen(this);
        gameOverScreen = new GameOverScreen(this);
        winScreen = new WinScreen(this);

        // ---- IMPORTANT FOR TESTING ----
        // Choose which screen the game starts on
        setScreen(titleScreen);
    }

    @Override
    public void render() {
        AmateurInput.update();
        super.render();
    }

    /**
     * Switches to titleScreen
     */
    public void showTitleScreen() {
        setScreen(titleScreen);
    }

    /**
     * Switches to gameScreen
     */
    public void showGameScreen() {
        setScreen(gameScreen);
    }

    /**
     * Switches to GameOverScreen
     */
    public void showGameOverScreen() {
        setScreen(gameOverScreen);
    }

    /**
     * Switches to winScreen
     */
    public void showWinScreen() {
        setScreen(winScreen);
    }

    /**
     * Switches to infoScreen
     */
    public void showInfoScreen() {
      setScreen(infoScreen);
    }

    /**
     * Starts a new game
     */
    public void restartGame() {
        gameScreen = new GameScreen(this);
    }

}

// Inspirasjon fra https://happycoding.io/tutorials/libgdx/game-screens#the-libgdx-approach
