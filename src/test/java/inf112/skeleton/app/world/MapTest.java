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

class MapTest {

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

    @Test
    void parseMapFile() {
        var repeat = 3; // Make sure stacking feature works.

        for (int i = 0; i < repeat; i++) {
            map.parseMapFile(path);
        }

        var f = Gdx.files.internal(path);
        var lines = f.readString().lines().toList();
        for (int y = 0; y < lines.size() * repeat; y++) {
            var linesInd = lines.size() - 1 - (y % lines.size());
            var line = lines.get(linesInd).toCharArray();

            for (int x = 0; x < line.length; x++) {
                if (line[x] == '|') {
                    continue;
                }
                var expected = switch (line[x]) {
                    case ' ' -> Tile.None;
                    case 'W' -> Tile.Wall;
                    case 'B' -> Tile.Boulder;
                    case 'G' -> Tile.Goal;
                    case 'H' -> Tile.HammerPowerUp;
                    default -> throw new IllegalStateException("Unexpected value: " + line[x]);
                };
                if (!expected.is(Flag.Movable)) {
                    assert expected.equals(map.getBlock(x, y));
                } else {
                    assert expected.texture.equals(world.getEntityAt(new Vector2i(x, y)).texture());
                    assert expected.flags.equals(world.getEntityAt(new Vector2i(x, y)).flags());
                }
            }

        }
    }

    @Test
    void removeBottomRows() {
        int height;
        var f = Gdx.files.internal(path);
        var lines = f.readString().lines().toList();
        height = lines.size();

        map.parseMapFile(path);
        assert map.removeBottomRows(height * 4) == height;

        map.parseMapFile(path);
        assert map.removeBottomRows(1) == 1; // Assuming the map is at least one row tall
    }


    @Test
    void setBlock() {
        map.parseMapFile(path);

        int x = 3; // arbitrary
        int y = 3;

        map.setBlock(x, y, Tile.Snake);
        assert map.getBlock(x, y).equals(Tile.Snake);

        x = -1; // outside the map
        y = -1;
        map.setBlock(x, y, Tile.Snake);
        assert map.getBlock(x, y).equals(Tile.None);
    }
}