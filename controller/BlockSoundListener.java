package controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BlockSoundListener implements SoundListener {

    private final boolean MUTED = false;

    public BlockSoundListener() {

    }

    @Override
    public void playSound() {

        if (!MUTED) {
            try {
                String path = "";
                path = new File("assets/sounds/block.wav").getAbsolutePath();
                AudioInputStream aIS = AudioSystem.getAudioInputStream(new File(path));
                Clip clip = AudioSystem.getClip();
                clip.open(aIS);
                clip.start();
            } catch (Exception e) {System.out.println(e);}
        }

    }
    
}