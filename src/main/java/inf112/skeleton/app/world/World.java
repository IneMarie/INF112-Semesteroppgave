package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.screens.GameScreen;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;

public class World implements IWorld {

    private Lava lava;
    private Player player;
    private List<IEntity> entities;
    private Map map;

    public World(Player player, List<IEntity> entities, Map map) {
        this.player = player;
        this.entities = entities;
        this.map = map;
    }

    public World(GameScreen game) {
        // TODO remove this. In the future, a world is created by a factory interpreting some map data.
        this.player = new Player(new Vector2i(0, 0), this);
        this.entities = new ArrayList<>();
        this.entities.add(new Boulder(new Vector2i(5, 5), this));
        this.entities.add(new Boulder(new Vector2i(6, 5), this));
        this.entities.add(new Boulder(new Vector2i(7, 5), this));
        this.entities.add(new Wall(new Vector2i(8, 5)));
        this.map = new Map();
        this.lava = new Lava(0, 5, this.player, game);
    }

    private IEntity getEntityAt(Vector2i pos) {
        for (var ent : entities) {
            if (ent.getPosition().equals(pos)) {
                return ent;
            }
        }
        return null;
    }

    @Override
    public boolean moveEntity(IEntity entity, Vector2i movement) {
        if (movement.x() == 0 && movement.y() == 0) {
            return false;
        }
        Vector2i new_pos = entity.getPosition().add(movement);
        IEntity b = getEntityAt(new_pos);
        Tile map_tile = Tile.Floor;
        try { // TODO THIS SHOULD BE REMOVED WHEN MAP IS IMPLEMENTED
            map_tile = map.getBlock(new_pos.x(), new_pos.y());
        } catch (ExecutionControl.NotImplementedException ignored) {

        }

        if (map_tile.is(Tile.Flag.Solid)) {
            return false;
        }

        if (b == null) {
            entity.setPosition(new_pos);
            return true;
        }

        if (b.getTile().is(Tile.Flag.Movable) && moveEntity(b, movement)) {
            entity.setPosition(new_pos);
            return true;
        }

        if (!b.getTile().is(Tile.Flag.Solid)) {
            entity.setPosition(new_pos);
            return true;
        }

        return false;
    }

    @Override
    public void update(float deltaSeconds) {
        for (var entity : entities) {
            entity.update(deltaSeconds);
        }
        player.update(deltaSeconds);
        lava.updateLava(deltaSeconds);
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (var entity : entities) {
            entity.draw(batch);
        }
        player.draw(batch);
        lava.draw(batch);
    }
}
