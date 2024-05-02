package inf112.skeleton.app.world;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.screens.GameScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class WorldTest {

    Map map;
    World world;
    final String path = "maps/map1.txt";

    @BeforeEach
    void setUp() {
        Gdx.gl = mock(GL20.class);
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = mock(ApplicationListener.class);
        new HeadlessApplication(listener, config);
        world = new World(mock(GameScreen.class), "maps/map1.txt");
        map = new Map(world);
    }

    private Player getPlayer() {
        final var playerScreenPosition = world.getPlayerScreenPosition(); // Find player spawn.
        final var playerPos = new Vector2i((int) playerScreenPosition.x, (int) playerScreenPosition.y);
        return (Player) world.getEntityAt(playerPos);
    }

    @Test
    void getPlayerScreenPosition() {
        final var playerScreenPosition = world.getPlayerScreenPosition(); // Find player spawn.
        final var player = getPlayer();
        world.moveEntity(player, new Vector2i(1, 0));
        world.update(1f); // Arbitrary time to assure lerp is finished.
        playerScreenPosition.x += 1f; // Should move by one block to the right.
        assert world.getPlayerScreenPosition().equals(playerScreenPosition);
        assert player.getScreenPosition().equals(playerScreenPosition);


        // Also check that the integer position is behaving correctly.

        final var velocity = new Vector2i(-1, 0);
        final var expectation = player.getPosition().add(velocity);
        world.moveEntity(player, velocity);

        assert player.getPosition().equals(expectation);
    }

    @Test
    void spawnEntity() {
        final var player = getPlayer();
        final var originalPos = player.getPosition();
        final var dir = new Vector2i(1, 0);
        final var right = player.getPosition().add(dir);

        world.removeEntity(world.getEntityAt(right)); // Remove boulder messing with movement. The game does not account
        // for overlapping entities.

        world.spawnEntity(new Entity(Tile.Wall, right)); // spawn a wall
        world.moveEntity(player, dir);
        assert originalPos.equals(player.getPosition()); // player should not move
    }

    @Test
    void getEntityAt() {
        final var playersRight = getPlayer().getPosition().add(new Vector2i(1, 0));
        world.removeEntity(world.getEntityAt(playersRight));

        final var entity = new Entity(Tile.Snake, playersRight);
        world.spawnEntity(entity);
        assert world.getEntityAt(playersRight).equals(entity);
        assert world.getEntityAt(new Vector2i(-1, -1)).equals(IEntity.dummy);
    }
}