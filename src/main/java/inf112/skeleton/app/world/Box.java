package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.assets.Textures;
import inf112.skeleton.app.geometry.Vector2i;


// TODO: move-method

public class Box implements IEntity{


    private Vector2 screenPosition;
    private Vector2i position;
    private IWorld world;
    private static final Texture texture = Textures.Rock.texture;


    public Box(Vector2i position, IWorld world) {
        this.position = position;
        this.world = world;
        screenPosition = new Vector2(position.x(), position.y());
    }


    public void setPosition(Vector2i position) {
        this.position = position;
    }

    @Override
    public Vector2i getPosition() {
        return position;
    }



    public void draw(SpriteBatch sb) {
        sb.draw(texture, screenPosition.x, screenPosition.y, 1, 1);
    }



    
    
}
