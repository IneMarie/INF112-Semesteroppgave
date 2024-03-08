package inf112.skeleton.app.world;

import inf112.skeleton.app.geometry.Vector2i;
import inf112.skeleton.app.world.Tile;

public interface IEntity {
    Vector2i getPosition();

    Tile getTile();
}



