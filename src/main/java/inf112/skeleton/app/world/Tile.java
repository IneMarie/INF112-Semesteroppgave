package inf112.skeleton.app.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum Tile {
    Floor("Dungeon Crawl Stone Soup Full/dungeon/floor/grass/grass0-dirt-mix_1.png", false, false),
    Wall("Dungeon Crawl Stone Soup Full/dungeon/wall/brick_brown_2.png", true, false);

    public final Texture texture;
    public final boolean solid;
    public final boolean movable;

    private Tile(String texture_path, boolean solid, boolean movable) {
        this.texture = new Texture(Gdx.files.internal(texture_path));
        this.solid = solid;
        this.movable = movable;
    }

}
