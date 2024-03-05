package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assets.Textures;

public enum Tile {
    Floor(Textures.Floor.texture, Flag.None),
    Wall(Textures.Wall.texture, Flag.Solid),
    Lava(Textures.Lava.texture, Flag.Damaging),
    Snake(Textures.Snake.texture, Flag.Damaging, Flag.Solid);

    public enum Flag {
        None, // None must be the first flag.
        Solid, Movable, Damaging;

        private long val() {
            if (ordinal() == 0) {
                return 0L;
            }
            return 1L << (ordinal() - 1);
        }

        static private long join(Flag ... flags) {
            long v = 0;
            for (var flag : flags) {
                v |= flag.val();
            }
            return v;
        }
    }

    public final Texture texture;
    private final long flags;

    Tile(Texture texture, Flag ... flags) {
        this.texture = texture;
        this.flags = Flag.join(flags);
    }

    public boolean is(Flag flag) {
        return (this.flags & flag.val()) != 0;
    }

    public boolean isAll(Flag ... flags) {
        for (var f : flags) {
            if (!is(f)) {
                return false;
            }
        }
        return true;
    }

    public boolean isOnly(Flag ... flags) {
        long v = this.flags;
        for (var flag : flags) {
            v ^= flag.val();
        }
        return v == 0L;
    }

    public boolean isAny(Flag ... flags) {
        for (var f : flags) {
            if (is(f)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNone(Flag ... flags) {
        return !isAny();
    }
    public void draw(SpriteBatch sb, Vector2 position, Vector2 origin) {
        sb.draw(texture, position.x - origin.x, position.y - origin.y, 1, 1);
    }

    public void draw(SpriteBatch sb, Vector2 position) {
        draw(sb, position, Vector2.Zero);
    }
}
