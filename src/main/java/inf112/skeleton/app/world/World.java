package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assets.SoundEffect;
import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final Lava lava;
    private final Player player;

    private final List<IEntity> entities;
    private final Map map;
    private final GameScreen game;
    private static final Texture fog = Textures.Fog.texture;


    public World(GameScreen game, String mapPath) {


        this.game = game;
        this.player = new Player(new Vector2i(1, 1), this);
        this.entities = new ArrayList<>();
        this.map = new Map(this);
        if (mapPath != null) {
            this.map.parseMapFile(mapPath);
        }
        this.lava = new Lava(-7, 2, this.player, game);

    }

    public Vector2 getPlayerScreenPosition() {
        return player.getScreenPosition();
    }

    public void spawnEntity(IEntity e) {
        entities.add(e);
    }

    public IEntity getEntityAt(Vector2i pos) {
        for (var ent : entities) {
            if (ent.getPosition().equals(pos)) {
                return ent;
            }
        }
        if (player.getPosition().equals(pos)) {
            return player;
        }

        IEntity.dummy.setPosition(pos);
        return IEntity.dummy;
    }

    public boolean removeEntity(IEntity entity) {
        if (entity == IEntity.dummy) return false;
        return entities.remove(entity);
    }

    /**
     * Called by entity as a request to the world to move to a different tile.
     * This is the logic that decides how tiles interact with each-other.
     * The semantic meaning of the Flag values are expressed in this code.
     *
     * @param entity   the entity trying to move
     * @param movement the velocity of the entity
     * @return true if entity moved, false otherwise
     */
    public boolean moveEntity(IEntity entity, Vector2i movement) {
        // How many blocks an entity can push at once.
        int initialStrength = 2;
        return moveEntity(entity, movement, initialStrength);
    }

    /**
     * Called by entity as a request to the world to move to a different tile.
     * This is the logic that decides how tiles interact with each-other.
     * The semantic meaning of the Flag values are expressed in this code.
     *
     * @param entity   the entity trying to move
     * @param movement the velocity of the entity
     * @param strength how many blocks the entity can push, achieved via recursive calls to moveEntity
     * @return true if entity moved, false otherwise
     */
    public boolean moveEntity(IEntity entity, Vector2i movement, int strength) {

        if (movement.x() == 0 && movement.y() == 0) {
            return false;
        }

        Vector2i newPos = entity.getPosition().add(movement);
        IEntity entityAt = getEntityAt(newPos);
        Tile mapTile = map.getBlock(newPos.x(), newPos.y());

        Flag.Group jointFlags = mapTile.flags.union(entityAt.flags());

        if (newPos.x() > 15 || newPos.x() < 0 || newPos.y() < 0 || newPos.y() > 47) {
            return false;
        }

        if (entity.flags().is(Flag.Player) && jointFlags.is(Flag.Goal)) {
            game.setGameWon();
        }

        if (entity.flags().is(Flag.Player) && jointFlags.is(Flag.PowerUp)) {
            map.setBlock(newPos.x(), newPos.y(), Tile.None);
            removeEntity(entityAt);

            Player player = (Player) entity;
            player.powerUp(3f);
            entity.setPosition(newPos);
            SoundEffect.PlayerMoveSFX.play(0.10f);
            SoundEffect.PowerUpSFX.play(0.10f);

            return true;
        }

        if (entity.flags().is(Flag.Breaking) && jointFlags.is(Flag.Breakable)) {
            map.setBlock(newPos.x(), newPos.y(), Tile.None);
            removeEntity(entityAt);

            SoundEffect.PlayerMoveSFX.play(0.10f);
            SoundEffect.DestroyEntitySFX.play(0.10f);
            entity.setPosition(newPos);
            return true;
        }

        if (mapTile.is(Flag.Solid)) {
            return false;
        }

        if (entityAt.flags().is(Flag.Movable) && strength > 0 && moveEntity(entityAt, movement, strength - 1)) {
            entity.setPosition(newPos);
            SoundEffect.PlayerMoveSFX.play(0.10f);
            SoundEffect.MoveEntitySFX.play(0.15f);
            return true;
        }

        if (!entityAt.flags().is(Flag.Solid)) {
            entity.setPosition(newPos);
            SoundEffect.PlayerMoveSFX.play(0.10f);
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
