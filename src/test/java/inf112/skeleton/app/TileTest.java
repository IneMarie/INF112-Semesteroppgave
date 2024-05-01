package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.world.Flag;
import inf112.skeleton.app.world.Tile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;


public class TileTest {

    @BeforeAll
    static void first() {
        //Main.main(new String[]{});

        Gdx.gl = mock(GL20.class);
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = mock(ApplicationListener.class);
        new HeadlessApplication(listener, config);
    }

    @Test
    void is() {
        assert Tile.Snake.is(Flag.Damaging);
        assert Tile.Snake.is(Flag.Solid);
        assert !Tile.Snake.is(Flag.Movable);
    }

    @Test
    void allOf() {
        assert Tile.Snake.isAllOf(Flag.Damaging);
        assert Tile.Snake.isAllOf(Flag.Solid);
        assert Tile.Snake.isAllOf(Flag.Damaging, Flag.Solid);
        assert Tile.Snake.isAllOf();
        assert !Tile.Snake.isAllOf(Flag.Movable);
    }

    @Test
    void only() {
        assert Tile.Snake.isOnly(Flag.Solid, Flag.Damaging);
        assert !Tile.Snake.isOnly(Flag.Damaging);
        assert !Tile.Snake.isOnly(Flag.Solid);
        assert !Tile.Snake.isOnly(Flag.Movable);
        assert Tile.Floor.isOnly();
    }

    @Test
    void any() {
        assert Tile.Snake.isAnyOf(Flag.Solid, Flag.Movable, Flag.Damaging);
        assert Tile.Snake.isAnyOf(Flag.Solid, Flag.Damaging);
        assert Tile.Snake.isAnyOf(Flag.Damaging);
        assert !Tile.Snake.isAnyOf(Flag.Movable);
    }

    @Test
    void none() {
        assert Tile.Snake.isNoneOf(Flag.Movable);
        assert Tile.Wall.isNoneOf(Flag.Movable, Flag.Damaging);
        assert Tile.Lava.isNoneOf(Flag.Movable, Flag.Solid);
        assert Tile.Floor.isNoneOf(Flag.Movable, Flag.Solid, Flag.Damaging);
    }
}
