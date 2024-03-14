package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreen implements Screen{
  private final ScreenManager screenManager;
  private final BitmapFont font;
  private final SpriteBatch batch; 
  
  public TitleScreen(ScreenManager screenManager){
    this.screenManager = screenManager;
    font = new BitmapFont();
    batch = new SpriteBatch();
  }
  
  @Override
  public void show() {
  }
  
  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    // Sjekker om Enter
    if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
      System.out.println("Byttet til GameScreen");
      screenManager.showGameScreen();
    }
    
    // Tegner tekst
    batch.begin();
    font.setColor(Color.WHITE);
    float x = Gdx.graphics.getWidth() / 2f - 100;
    float y = Gdx.graphics.getHeight() / 2f;
    font.draw(batch, "Press ENTER to play", x, y);
    batch.end();
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