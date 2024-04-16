package inf112.skeleton.app.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum SoundEffect {
    Player_move("blipp.ogg"),
    LavaSFX("blipp.ogg");

    private final Sound sound;

    SoundEffect(String path) {
        this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }
    
    /**
     * Plays a soundeffect
     * @param volume - Must be between 0 - 1, where 1 is full volume and 0 is no volume
     */
    public void play(float volume) {
        sound.play(volume);
    }

    public void stop() {
        sound.stop();
    }

    public void dispose() {
        sound.dispose();
    }
}
