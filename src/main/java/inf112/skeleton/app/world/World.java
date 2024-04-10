package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class World {

    private Lava lava;
    private Player player;

    // TODO Entities can spawn, but are never removed. Remove entities that touch lava or something.
    private List<IEntity> entities;
    private Map map;
    private static final Texture fog = Textures.Fog.texture;


    public World(Player player, List<IEntity> entities, Map map) {
        this.player = player;
        this.entities = entities;
        this.map = map;

    }

    public World(GameScreen game) {

        // TODO Maybe the world should just be a long-lived object that handles loading of several Map objects.
        // TODO Load random map chunk from the maps folder. Keep loading and unloading maps as the player travels up.

        this.player = new Player(new Vector2i(1, 1), this, game);
        this.entities = new ArrayList<>();
        this.map = new Map(this, "maps/map1.txt");
        this.lava = new Lava(0, 5, this.player, game);
    }

    public Vector2 getPlayerScreenPosition() {
        return player.getScreenPosition();
    }

    public void spawnEntity(IEntity e) {
        entities.add(e);
    }

    private IEntity getEntityAt(Vector2i pos) {
        for (var ent : entities) {
            if (ent.getPosition().equals(pos)) {
                return ent;
            }
        }
        return null;
    }

    public boolean moveEntity(IEntity entity, Vector2i movement) {
        // How many blocks an entity can push at once.
        int initialStrength = 2;
        return moveEntity(entity, movement, initialStrength);
    }

    public boolean moveEntity(IEntity entity, Vector2i movement, int strength) {
        if (movement.x() == 0 && movement.y() == 0) {
            return false;
        }
        Vector2i new_pos = entity.getPosition().add(movement);
        IEntity b = getEntityAt(new_pos);

        Tile map_tile = map.getBlock(new_pos.x(), new_pos.y());

        if (new_pos.x() > 16 || new_pos.x() < 0 || new_pos.y() < 0) {
            return false;
        }


        if (map_tile.is(Tile.Flag.Solid)) {
            return false;
        }

        if (b == null) {
            entity.setPosition(new_pos);
            return true;
        }

        if (b.getTile().is(Tile.Flag.Movable) && strength > 0 && moveEntity(b, movement, strength - 1)) {
            entity.setPosition(new_pos);
            return true;
        }

        if (!b.getTile().is(Tile.Flag.Solid)) {
            entity.setPosition(new_pos);
            return true;
        }

        return false;
    }

    public void update(float deltaSeconds) {
        player.update(deltaSeconds);
        for (var entity : entities) {
            entity.update(deltaSeconds);
        }
        lava.updateLava(deltaSeconds);
    }

    public void draw(SpriteBatch batch) {
        map.draw(batch);
        for (var entity : entities) {
            entity.draw(batch);
        }
        player.draw(batch);
        lava.draw(batch);
        batch.draw(fog, player.getScreenPosition().x - 21.5f, player.getScreenPosition().y - 21.5f, 44, 44);
    }
}
