/**
 * Author: Bao Trinh
 * Course: TCSS 305
 * Assignment: 6 - Game of Craps
 */
package model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for handling the sound in the application.
 */
public class Sound {
    private Clip audioClip;

    /**
     * Constructor for the Sound class.
     * @param soundFile the file containing the sound to be played
     */
    public Sound(File soundFile) {
        try {
            // Get the audio stream from the sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            // Get a clip that can be used for playing back the audio
            audioClip = AudioSystem.getClip();
            // Open the audio clip
            audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Play the sound from the beginning.
     */
    public void play() {
        // Set the frame position to the start
        audioClip.setFramePosition(0);
        // Start playing the audio
        audioClip.start();
    }
}
