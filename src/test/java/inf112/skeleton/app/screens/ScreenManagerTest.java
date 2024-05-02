package inf112.skeleton.app.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ScreenManagerTest {

 private ScreenManager screenManager;

    @BeforeEach
    public void setUp() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new Game() {
            @Override
            public void create() {
            }
        }, config);

        screenManager = new ScreenManager();

        // Mocking screens
        screenManager.titleScreen = mock(Screen.class);
        screenManager.gameScreen = mock(Screen.class);
        screenManager.gameOverScreen = mock(Screen.class);
        screenManager.winScreen = mock(Screen.class);
        screenManager.infoScreen = mock(Screen.class);
    }

    @Test
    public void testShowTitleScreen() {
        screenManager.showTitleScreen();
        assertEquals(screenManager.getScreen(), screenManager.titleScreen);
        assertNotEquals(screenManager.getScreen(), screenManager.infoScreen);
    }

    @Test
    public void testShowGameScreen() {
        screenManager.showGameScreen();
        assertEquals(screenManager.getScreen(), screenManager.gameScreen);
        assertNotEquals(screenManager.getScreen(), screenManager.titleScreen);
    }

    @Test
    public void testShowGameOverScreen() {
        screenManager.showGameOverScreen();
        assertEquals(screenManager.getScreen(), screenManager.gameOverScreen);
        assertNotEquals(screenManager.getScreen(), screenManager.titleScreen);
    }

    @Test
    public void testShowWinScreen() {
        screenManager.showWinScreen();
        assertEquals(screenManager.getScreen(), screenManager.winScreen);
        assertNotEquals(screenManager.getScreen(), screenManager.titleScreen);

    }

    @Test
    public void testShowInfoScreen() {
        screenManager.showInfoScreen();
        assertEquals(screenManager.getScreen(), screenManager.infoScreen);
        assertNotEquals(screenManager.getScreen(), screenManager.titleScreen);

    }
}
