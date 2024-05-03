package inf112.skeleton.app.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum SoundEffect {
    PlayerMoveSFX("footstep.wav"), // spilleren flytter seg
    LavaAmbience("lava ambience.wav"), // Lava vibes
    PlayerDeathSFX("Player Death.wav"), // spilleren dør av lava
    MoveEntitySFX("Boulder Push.wav"), // spilleren flytter en stein
    PowerUpSFX("Powerup.wav"), // spilleren plukker opp en power up
    DestroyEntitySFX("Explosion6.wav"), // spilleren ødelegger en entity (powerup)
    SelectSFX("Select.wav"), // spilleren endrer screen
    PlayerWinSFX("game win.wav"), //spilleren vinner spillet


    GameWinMusic("game win music.wav"),
    GameOverMusic("game over music.wav"),
    GameMusic("game music.wav");


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

    public void loop(float volume) {
        sound.loop(volume);
    }

    public void dispose() {
        sound.dispose();
    }
}
