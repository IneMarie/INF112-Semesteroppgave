package inf112.skeleton.app.screens;

import static org.mockito.Mockito.timeout;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenManager extends Game {
  
  private Screen titleScreen;
  private Screen gameScreen;
  private Screen gameOverScreen;
  private Screen winScreen;
  
  @Override
  public void create() {
    
    // Oppretter screens
    restartGame();
    titleScreen = new TitleScreen(this);
    gameOverScreen = new GameOverScreen(this);
    winScreen = new WinScreen(this);
    
    // ---- VIKTIG FOR TESTING ----
    // Bestem hvilken screen man begynner på
    setScreen(titleScreen);
  }
  
  /**
  * Bytter til titleScreen
  */
  public void showTitleScreen() {
    setScreen(titleScreen);
  }
  
  /**
  * Bytter til gameScreen
  */
  public void showGameScreen() {
    setScreen(gameScreen);
  }
  
  /**
  * Bytter til GameOverScreen
  */
  public void showGameOverScreen() {
    setScreen(gameOverScreen);
  }

  /**
  * Bytter til winScreen
  */
  public void showWinScreen() {
    setScreen(winScreen);
  }
  
  
  /**
  * Starter et nytt spill
  */
  public void restartGame() {
    gameScreen = new GameScreen(this);
  }
}

// Inspirasjon fra https://happycoding.io/tutorials/libgdx/game-screens#the-libgdx-approach
