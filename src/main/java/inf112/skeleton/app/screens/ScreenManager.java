package inf112.skeleton.app.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.GameLogic;

public class ScreenManager extends Game {
  
  private Screen titleScreen;
  private Screen gameScreen;
  private Screen gameOverScreen;
  private GameLogic gameLogic;


  @Override
  public void create() {
      // Oppretter screens
      titleScreen = new TitleScreen(this);
      gameLogic = new GameLogic(this);
      gameScreen = new GameScreen(this, gameLogic);
      gameOverScreen = new GameOverScreen(this);

      // ---- VIKTIG FOR TESTING ----
      // Bestem hvilken screen man begynner på 
      setScreen(titleScreen);
  }

  public void showTitleScreen() {
      setScreen(titleScreen);
  }

  public void showGameScreen() {
      setScreen(gameScreen);
  }

  public void showGameOverScreen() {
      setScreen(gameOverScreen);
  }
}

// Inspirasjon fra https://happycoding.io/tutorials/libgdx/game-screens#the-libgdx-approach
