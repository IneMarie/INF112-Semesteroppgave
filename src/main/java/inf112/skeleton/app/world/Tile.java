package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.assets.Textures;

public enum Tile {
    Floor(Textures.Floor.texture, false, false),
    Wall(Textures.Wall.texture, true, false),
    Lava(Textures.Lava.texture, true, false);

    public final Texture texture;
    public final boolean solid;
    public final boolean movable;

    private Tile(Texture texture, boolean solid, boolean movable) {
        this.texture = texture;
        this.solid = solid;
        this.movable = movable;
    }

}
