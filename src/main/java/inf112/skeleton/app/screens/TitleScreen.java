package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import inf112.skeleton.app.GameLogic;

public class TitleScreen implements Screen{
  private final ScreenManager screenManager; 
  
  public TitleScreen(ScreenManager screenManager){
    this.screenManager = screenManager;
  }
  
  @Override
  public void show() {
  }
  
  @Override
  public void render(float delta) {
    // RØd farge
    Gdx.gl.glClearColor(0, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    // Sjekker om Enter
    if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
      System.out.println("Byttet til GameScreen");
      screenManager.showGameScreen();
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
  }
}