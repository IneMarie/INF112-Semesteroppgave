package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.world.Tile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


public class TileTest {

    @BeforeAll
    static void first() {
        //Main.main(new String[]{});

        Gdx.gl = mock(GL20.class);
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationListener() {

            @Override
            public void create() {
                // TODO Auto-generated method stub

            }

            @Override
            public void resize(int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void render() {
                // TODO Auto-generated method stub

            }

            @Override
            public void pause() {
                // TODO Auto-generated method stub

            }

            @Override
            public void resume() {
                // TODO Auto-generated method stub

            }

            @Override
            public void dispose() {
                // TODO Auto-generated method stub

            }};
        new HeadlessApplication(listener, config);
    }

    @Test
    void is() {
        assert(Tile.Snake.is(Tile.Flag.Damaging));
        assert(Tile.Snake.is(Tile.Flag.Solid));
        assert(!Tile.Snake.is(Tile.Flag.Movable));
    }

    @Test
    void allOf() {
        assert(Tile.Snake.isAllOf(Tile.Flag.Damaging));
        assert(Tile.Snake.isAllOf(Tile.Flag.Solid));
        assert(Tile.Snake.isAllOf(Tile.Flag.Damaging, Tile.Flag.Solid));
        assert(Tile.Snake.isAllOf());
        assert(!Tile.Snake.isAllOf(Tile.Flag.Movable));
    }

    @Test
    void only() {
        assert(Tile.Snake.isOnly(Tile.Flag.Solid, Tile.Flag.Damaging));
        assert(!Tile.Snake.isOnly(Tile.Flag.Damaging));
        assert(!Tile.Snake.isOnly(Tile.Flag.Solid));
        assert(!Tile.Snake.isOnly(Tile.Flag.Movable));
        assert(Tile.Floor.isOnly());
    }

    @Test
    void any() {
        assert(Tile.Snake.isAnyOf(Tile.Flag.Solid, Tile.Flag.Movable, Tile.Flag.Damaging));
        assert(Tile.Snake.isAnyOf(Tile.Flag.Solid, Tile.Flag.Damaging));
        assert(Tile.Snake.isAnyOf(Tile.Flag.Damaging));
        assert(!Tile.Snake.isAnyOf(Tile.Flag.Movable));
    }

    @Test
    void none(){
        assert(Tile.Snake.isNoneOf(Tile.Flag.Movable));
        assert(Tile.Wall.isNoneOf(Tile.Flag.Movable, Tile.Flag.Damaging));
        assert(Tile.Lava.isNoneOf(Tile.Flag.Movable, Tile.Flag.Solid));
        assert(Tile.Floor.isNoneOf(Tile.Flag.Movable, Tile.Flag.Solid, Tile.Flag.Damaging));
    }
}
