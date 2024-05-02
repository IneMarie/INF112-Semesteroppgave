package inf112.skeleton.app.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * An enum of all textures used in the game.
 * The number of textures is quite small, so we just load them all at startup and reuse them.
 */
public enum Textures {
    Missing("Dungeon Crawl Stone Soup Full/gui/prompt_no.png"),
    Player("sandergfx/player.png"),
    Floor("sandergfx/floor.png"),
    Wall("sandergfx/wall.png"),
    Lava("lava.png"),
    LavaTop("lavatop.png"),
    Snake("Dungeon Crawl Stone Soup Full/monster/animals/snake.png"),
    Boulder("rock placeholder.png"),
    Fog("fog of war png.png"),
    Win("you win.png"),
    Dead("dead.png"),
    Title("titleImage.png"),
    Info("infoscreen.png"),
    Orb("sandergfx/point.png"),
    HammerPowerUp("sandergfx/war_hammer.png"),
    HammerPlayerImage("Dungeon Crawl Stone Soup Full/player/hand_right/hammer_3.png"),
    ;


    public final Texture texture;

    Textures(String path) {
        this.texture = new Texture(Gdx.files.internal(path));
    }

    /**
     * Disposes all textures of this enum.
     */
    public static void dispose() {
        for (var texture : values()) {
            texture.texture.dispose();
        }
    }
}
