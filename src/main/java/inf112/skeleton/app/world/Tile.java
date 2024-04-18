package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assets.Textures;

public enum Tile {
    None(null),
    Floor(Textures.Floor.texture),
    Wall(Textures.Wall.texture, Flag.Solid, Flag.Breakable),
    Lava(Textures.Lava.texture, Flag.Damaging),
    Snake(Textures.Snake.texture, Flag.Damaging, Flag.Solid),
    Boulder(Textures.Boulder.texture, Flag.Solid, Flag.Movable),
    Goal(Textures.Orb.texture, Flag.Goal, Flag.Solid, Flag.Movable),
    HammerPowerUp(Textures.HammerPowerUp.texture, Flag.PowerUp)
    ;

    public final Texture texture;
    public final Flag.Group flags;

    Tile(Texture texture, Flag... flags) {
        this.texture = texture;
        this.flags = Flag.join(flags);
    }

    public boolean is(Flag flag) {
        return flags.is(flag);
    }

    public boolean isAllOf(Flag... flags) {
        return this.flags.isAllOf(flags);
    }

    public boolean isOnly(Flag... flags) {
        return this.flags.isOnly(flags);
    }

    public boolean isAnyOf(Flag... flags) {
        return this.flags.isAnyOf(flags);
    }

    public boolean isNoneOf(Flag... flags) {
        return this.flags.isNoneOf(flags);
    }

    public void draw(SpriteBatch sb, Vector2 position, Vector2 origin) {
        if (texture != null) sb.draw(texture, position.x - origin.x, position.y - origin.y, 1, 1);
    }

    public void draw(SpriteBatch sb, Vector2 position) {
        draw(sb, position, Vector2.Zero);
    }
}
