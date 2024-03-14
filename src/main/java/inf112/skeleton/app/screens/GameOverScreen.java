package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.GameLogic;


public class GameOverScreen implements Screen{
  private final ScreenManager screenManager; 
  private final SpriteBatch batch;  
  
  public GameOverScreen(ScreenManager screenManager){
    this.screenManager = screenManager;
    batch = new SpriteBatch();
  }
  
  @Override
  public void show() {
  }
  
  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Sjekker om Enter
    if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
      System.out.println("Current screen: GameOverScreen");
    }
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
    batch.dispose();
  }
}
