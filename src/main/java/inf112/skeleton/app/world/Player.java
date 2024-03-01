package inf112.skeleton.app.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.Input;
import inf112.skeleton.app.geometry.Vector2i;

public class Player {

    private Vector2i position;
    //private static final Texture = new Texture(Gdx.files.internal(texture_path));
    private static final Texture texture = new Texture(Gdx.files.internal("Dungeon Crawl Stone Soup Full/player/base/centaur_darkbrown_male.png"));


    Player(Vector2i position) {
        this.position = position;
    }


    public void setPosition(Vector2i position) {
        position = position.copy();
    }

    public Vector2i getPosition() {
        return position.copy();
    }

    void update(float deltaSeconds) {
        Vector2i input = Input.playerMove();
    }

    void draw(SpriteBatch sb) {
        sb.draw(texture, position.x(), position.y(), 1, 1);
    }

}
