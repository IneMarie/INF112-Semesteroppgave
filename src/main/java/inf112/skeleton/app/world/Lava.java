package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.screens.GameScreen;


public class Lava {
  private int lavaStartValue;
  private int lavaHeight;
  
  private float timeElapsed = 0;
  private final float lavaRiseTimer;
  
  private Player player;
  private GameScreen game;
  private int gridHeight = 16; // IKKE HARDKOD FIKS SENERE TODO!
  private boolean reachedPlayerOrTop;
  
  private static final Texture texture = Textures.Lava.texture;
  private static final Texture topTexture = Textures.LavaTop.texture;
  
  /**
  * Creates lava that slowly rises
  *
  * @param lavaStartValue - start pos for lava
  * @param lavaRiseTimer  - how many seconds before lava rises
  * @param player         - needed to check for player collision
  * @param game           - makes it possible to set a game over condition
  */
  public Lava(int lavaStartValue, float lavaRiseTimer, Player player, GameScreen game) {
    this.lavaStartValue = lavaStartValue - 1;
    this.lavaHeight = 0;
    this.lavaRiseTimer = lavaRiseTimer;
    this.player = player;
    this.game = game;
  }
  
  /**
  * Updates the lava, as long as the player is still alive
  */
  public void updateLava(float deltaSeconds) {
    timeElapsed += deltaSeconds;
    if (playerLavaDeath() && !reachedPlayerOrTop) {
      reachedPlayerOrTop = true;
      game.setGameOver();
    } else {
      checkAndRiseLava();
    }
  }
  
  public void draw(SpriteBatch batch) {
    for (int y = 0; y < lavaHeight; y++) {
      // Smooth movement
      float smoothY = lavaStartValue + y + (float) 1 * timeElapsed / lavaRiseTimer;

      // First row is its own texture
      if (y == lavaHeight - 1) {
        for (int x = 0; x < gridHeight; x++) {
          batch.draw(topTexture, x, smoothY, 1, 1);
        }
      } else { // Rest of lava
        for (int x = 0; x < gridHeight; x++) {
          batch.draw(texture, x, smoothY, 1, 1);
        }
      }
    }
  }

  /**
  * Gets the y value for the lavas start position
  */
  public int getLavaStartValue() {
    return lavaStartValue;
  }
  
  /**
  * Gets the height of the lava
  */
  public int getLavaHeight() {
    return lavaHeight;
  }
  
  // Checks if the player is dying of lava
  private boolean playerLavaDeath() {
    return (lavaStartValue <= player.getRow() && player.getRow() < lavaStartValue + lavaHeight) || lavaStartValue > player.getRow();
  }
  
  /**
  * The lava rises as long as it is lower than the map, and the player is still alive
  */
  public void checkAndRiseLava() {
    if (!reachedPlayerOrTop && timeElapsed >= lavaRiseTimer) {
      timeElapsed -= lavaRiseTimer;
      if (lavaStartValue + lavaHeight < gridHeight) {
        lavaHeight++;
        //System.out.println("Lava høyde: " + lavaHeight);
      } else {
        reachedPlayerOrTop = true;
        // System.out.println("Nådd toppen!");
      }
    }
  }
}
