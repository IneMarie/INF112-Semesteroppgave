package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import inf112.skeleton.app.GameLogic;


public class GameScreen implements Screen{
  private final ScreenManager screenManager; 
  private final GameLogic gameLogic;


  public GameScreen(ScreenManager screenManager, GameLogic gameLogic){
    this.screenManager = screenManager;
    this.gameLogic = gameLogic;

  }

  @Override
  public void show() {
    gameLogic.create();
  }

  @Override
  public void render(float delta) {
    gameLogic.render();
  }

  @Override
  public void resize(int width, int height) {
    gameLogic.resize(width, height);
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
