package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.assets.Textures;


public class Lava {
  private int lavaStartValue; 
  private int lavaHeight; 

  private float timeElapsed = 0; // Siden sist lavaen steg
  private final float lavaRiseTimer;

  private Player player; 
  private GameLogic game;
  private int gridHeight = 16; // IKKE HARDKOD FIKS SENERE TODO!
  private boolean reachedPlayerOrTop;

  private static final Texture texture = Textures.Lava.texture;
  
  /**
  * Lager et lava objekt som gradvis stiger oppover
  * @param lavaStartValue - start posisjon til lava
  * @param lavaRiseTimer - hvor mange sekunder det tar før lavaen stiger
  * @param player - for å sjekke om lava og player er på samme rad
  * @param game - gjør det mulig å endre screen når man dør
  */
  public Lava(int lavaStartValue, float lavaRiseTimer, Player player, GameLogic game) {
    this.lavaStartValue = lavaStartValue;
    this.lavaHeight = 0;
    this.lavaRiseTimer = lavaRiseTimer;
    this.player = player;
    this.game = game;

  } 
  
  /**
  * Oppdaterer lavaen, så lenge spilleren lever
  */
  public void updateLava(float deltaSeconds) {
    timeElapsed += deltaSeconds;
    if (playerLavaDeath() && !reachedPlayerOrTop){
      reachedPlayerOrTop = true;
      game.setGameOver();
    } else {
      checkAndRiseLava();
    }
  }
  
  /**
  * Tegner lavaen
  * @param spriteBatch 
  */
  public void draw(SpriteBatch batch) {
    for (int y = 0; y < lavaHeight; y++) {
      for (int x = 0; x < gridHeight; x++) {
        batch.draw(texture, x, lavaStartValue + y, 1, 1);
      }
    }
  }
  
  /**
  * Henter ut y verdien til lavaens startposisjon
  */
  public int getLavaStartValue() {
    return lavaStartValue;
  }
  
  /**
  * Henter ut høyden til lavaen
  */
  public int getLavaHeight() {
    return lavaHeight;
  }

  private boolean playerLavaDeath(){
    return lavaStartValue <= player.getRow() && player.getRow() < lavaStartValue + lavaHeight;
  }
  
  /**
  * Gjør at lavaen stiger dersom den er under kart høyden, og spilleren ikke er død
  */  
  public void checkAndRiseLava() {
    if (!reachedPlayerOrTop && timeElapsed >= lavaRiseTimer) {
      timeElapsed -= lavaRiseTimer;
      if (lavaStartValue + lavaHeight < gridHeight){
        lavaHeight++;
        System.out.println("Lava høyde: " + lavaHeight);
      } else {
        reachedPlayerOrTop = true;
        System.out.println("Nådd toppen!");
      } 
    }
  }
}
