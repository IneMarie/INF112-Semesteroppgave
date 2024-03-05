package inf112.skeleton.app.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.world.Tile;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.world.Player;


public class Lava {
  private int lavaStartValue; 
  private int lavaHeight; 
  private float lavaRiseSpeed; 
  private static final Texture texture = Textures.Lava.texture;

  
  /**
  * Oppretter lavaen
  * @param lavaStartValue - start posisjon til lava
  * @param lavaRiseSpeed - hvor raskt lavaen stiger
  */
  public Lava(int lavaStartValue, float lavaRiseSpeed) {
    this.lavaStartValue = lavaStartValue;
    this.lavaHeight = 1;
    this.lavaRiseSpeed = lavaRiseSpeed; // 1 rad om gangen TODO
  } 
  
  /**
  * Oppdaterer lavaen
  */
  public void updateLava() {
    // System.out.println("TEST lava update");
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
  public int getLavaStartY() {
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
  private void riseLava() {
    //Sjekker at den ikker er større en vinduet
    if (lavaStartValue + lavaHeight < Gdx.graphics.getHeight()) {
      lavaHeight += lavaRiseSpeed;
    }
  }
}
