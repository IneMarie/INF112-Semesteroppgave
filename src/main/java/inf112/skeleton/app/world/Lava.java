package inf112.skeleton.app.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.assets.Textures;


public class Lava {
  private int lavaStartValue; 
  private int lavaHeight; 
  // private float lavaRiseSpeed; 
  
  private float timeElapsed = 0; // Siden sist lavaen steg
  private final float lavaRiseTimer;
  
  private static final Texture texture = Textures.Lava.texture;

  private boolean reachedPlayerOrTop;
    
  
  /**
  * Oppretter lavaen
  * @param lavaStartValue - start posisjon til lava
  * @param lavaRiseSpeed - hvor raskt lavaen stiger
  */
  public Lava(int lavaStartValue, float lavaRiseTimer) {
    this.lavaStartValue = lavaStartValue;
    this.lavaHeight = 0;
    this.lavaRiseTimer = lavaRiseTimer;
    // this.lavaRiseSpeed = lavaRiseSpeed; 1 rad om gangen TODO
  } 
  
  /**
  * Oppdaterer lavaen
  */
  public void updateLava(float deltaSeconds) {
    // System.out.println("TEST lava update");
    timeElapsed += deltaSeconds;
    riseLava();
  }
  
  /**
  * Tegner lavaen
  * @param spriteBatch 
  */
  public void draw(SpriteBatch batch) {
    for (int y = 0; y < lavaHeight; y++) {
      for (int x = 0; x < 16; x++) { // TODO foreløpig 16x16 grid endre til variabler
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
  
  /**
  * Gjør at lavaen stiger 
  */  
  public void riseLava() {
    if (!reachedPlayerOrTop && timeElapsed >= lavaRiseTimer) {
      timeElapsed -= lavaRiseTimer;
      if (lavaStartValue + lavaHeight < 16) { // Ikke hardkode TODO
        lavaHeight++;
        System.out.println("Lava høyde: " + lavaHeight);
      } else {
        reachedPlayerOrTop = true;
        System.out.println("Nådd toppen!");

      }
    }
  }
}
