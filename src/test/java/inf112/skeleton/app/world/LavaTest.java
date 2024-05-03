package inf112.skeleton.app.world;

import inf112.skeleton.app.screens.GameScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LavaTest {
  
  Lava lava;
  Player player;
  GameScreen game;
  
  @BeforeEach
  void setUp() {
    
    Gdx.gl = mock(GL20.class);
    HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
    ApplicationListener listener = mock(ApplicationListener.class);
    new HeadlessApplication(listener, config);
    
    player = mock(Player.class);
    game = mock(GameScreen.class);
    lava = new Lava(0, 5, player, game);
  }
  
  @Test
  void testLavaInitialization() {
    assertEquals(0, lava.getLavaStartValue());
    assertEquals(0, lava.getLavaHeight());
  }
  
  @Test
  void testLavaRisingPlayerDeath() {
    assertFalse(lava.playerLavaDeath());
    for (int i = 0; i < 100; i++){
      lava.updateLava(0.1f);
    }
    assertTrue(lava.playerLavaDeath()); // Lava stiger spiller dør.
  }
  
}
