package inf112.skeleton.app.world;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.AmateurInput;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.screens.GameScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class PlayerTest {

    World world;
    Player player;

    @BeforeEach
    void setup() {
        Gdx.gl = mock(GL20.class);
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = mock(ApplicationListener.class);
        new HeadlessApplication(listener, config);
        world = new World(mock(GameScreen.class), null);
        player = getPlayer();
    }

    @Test
    void powerUp() {
        {
            for (int xOff = 0; xOff < 2; xOff++) {
                final var pos = player.getPosition().add(new Vector2i(1 + xOff, 0));
                world.spawnEntity(new Entity(Tile.Wall, pos));
            }

            // Simulate right movement
            AmateurInput.press(Input.Keys.D);

            // should not move without power-up
            final var originalPosition = player.getPosition();
            player.update(1f);
            assert player.getPosition().equals(originalPosition);

            // should break through walls with power-up
            player.powerUp(1f);
            player.update(1.1f);
            assert !player.getPosition().equals(originalPosition);

            // power up is over. should be stuck again.
            final var otherPosition = player.getPosition();
            player.update(1f);
            assert player.getPosition().equals(otherPosition);
        }

        {
            // the same for the y-axis
            AmateurInput.release(Input.Keys.D);
            AmateurInput.press(Input.Keys.W);

            for (int yOff = 0; yOff < 2; yOff++) {
                final var pos = player.getPosition().add(new Vector2i(0, 1 + yOff));
                world.spawnEntity(new Entity(Tile.Wall, pos));
            }

            // should not move without power-up
            final var originalPosition = player.getPosition();
            player.update(1f);
            assert player.getPosition().equals(originalPosition);

            // should break through walls with power-up
            player.powerUp(1f);
            player.update(1.1f);
            assert !player.getPosition().equals(originalPosition);

            // power up is over. should be stuck again.
            final var otherPosition = player.getPosition();
            player.update(1f);
            assert player.getPosition().equals(otherPosition);
        }
    }

    @Test
    void getScreenPosition() {
    }

    @Test
    void setPosition() {
    }

    @Test
    void getPosition() {
    }

    @Test
    void getRow() {
    }

    @Test
    void update() {
    }

    @Test
    void draw() {
    }

    @Test
    void texture() {
    }

    @Test
    void flags() {
    }

    private Player getPlayer() {
        final var playerScreenPosition = world.getPlayerScreenPosition(); // Find player spawn.
        final var playerPos = new Vector2i((int) playerScreenPosition.x, (int) playerScreenPosition.y);
        return (Player) world.getEntityAt(playerPos);
    }
}