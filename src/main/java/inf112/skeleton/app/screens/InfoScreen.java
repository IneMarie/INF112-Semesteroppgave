package inf112.skeleton.app.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.skeleton.app.AmateurInput;
import inf112.skeleton.app.CameraController2D;
import inf112.skeleton.app.assets.SoundEffect;
import inf112.skeleton.app.assets.Textures;

public class InfoScreen extends ScreenAdapter{
  private final ScreenManager screenManager;
  private CameraController2D uiCamera;
  private final SpriteBatch batch;
  private static final Texture info = Textures.Info.texture;


  public InfoScreen(ScreenManager screenManager) {
      this.screenManager = screenManager;
      batch = new SpriteBatch();

      uiCamera = new CameraController2D(720);
      uiCamera.screenAnchor.x = 0f;
      uiCamera.screenAnchor.y = 0f;
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(new Color(25 / 255f, 14 / 255f, 22 / 255f, 255 / 255f));

      batch.begin();

      batch.draw(info, 0, 0, 1419 / 2, 1515 / 2);

      batch.end();

      uiCamera.begin(batch);


      // Sjekker om Enter
      if (AmateurInput.isKeyJustPressed(Input.Keys.ENTER)) {
          screenManager.restartGame();
          screenManager.showGameScreen();
          SoundEffect.SelectSFX.play(0.25f);
          SoundEffect.LavaAmbience.loop(0.20f);

      }
      if (AmateurInput.isKeyJustPressed(Input.Keys.ESCAPE)) {
        screenManager.showTitleScreen();
        SoundEffect.SelectSFX.play(0.25f);

    }
  }

  @Override
  public void resize(int width, int height) {
    uiCamera.onResize(width, height);

  }

  @Override
  public void dispose() {
      batch.dispose();
  }
}
