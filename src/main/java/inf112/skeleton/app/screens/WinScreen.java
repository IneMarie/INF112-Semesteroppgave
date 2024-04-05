package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.CameraController2D;

public class WinScreen implements Screen {
  private final ScreenManager screenManager;
  private final BitmapFont font;
  private final SpriteBatch batch; 
  private CameraController2D uiCamera;
  
  
  public WinScreen(ScreenManager screenManager){
    this.screenManager = screenManager;
    font = new BitmapFont();
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
    ScreenUtils.clear(Color.BLACK);
    
    
    // Tegner tekst
    batch.begin();
    font.setColor(Color.WHITE);
    float x = Gdx.graphics.getWidth() / 2f - 100;
    float y = Gdx.graphics.getHeight() / 2f;
    font.draw(batch, "YOU WON!!!!", x, y); // Fikset skaleringen
    float secondLineY = y - font.getLineHeight();
    font.draw(batch, "Press ENTER to try again!", x, secondLineY);
    batch.end();
    
    uiCamera.begin(batch);
    
    // Sjekker om Enter
    if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
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
    font.dispose();
    batch.dispose();
  }
}

