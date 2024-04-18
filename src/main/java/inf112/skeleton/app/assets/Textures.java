package inf112.skeleton.app.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum Textures {
    Player("Dungeon Crawl Stone Soup Full/player/base/centaur_darkbrown_male.png"),
    Floor("Dungeon Crawl Stone Soup Full/dungeon/floor/grass/grass0-dirt-mix_1.png"),
    Wall("Dungeon Crawl Stone Soup Full/dungeon/wall/brick_brown_2.png"),
    Lava("lava.png"),
    LavaTop("lavatop.png"),
    Snake("Dungeon Crawl Stone Soup Full/monster/animals/snake.png"),
    Boulder("rock placeholder.png"),
    Fog("fog of war png.png"),
    Win("you win.png"),
    Dead("dead.png"),
    Title("titleImage.png"),
    Orb("Dungeon Crawl Stone Soup Full/dungeon/vaults/disco_ball.png"),
    HammerPowerUp("Dungeon Crawl Stone Soup Full/item/weapon/war_hammer.png"),
    HammerPlayerImage("Dungeon Crawl Stone Soup Full/player/hand_right/hammer_3.png"),
    ;


    public final Texture texture;

    Textures(String path) {
        this.texture = new Texture(Gdx.files.internal(path));
    }

    public static void dispose() {
        for (var texture : values()) {
            texture.texture.dispose();
        }
    }
}
