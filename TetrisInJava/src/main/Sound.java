package main;


import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    Clip musicClip;
    URL url[] = new URL[10];

    public Sound() {
        url[0] = getClass().getResource("/Density & Time - MAZE.wav");
        url[1] = getClass().getResource("/deleteLine.wav");
        url[2] = getClass().getResource("/gameOver.wav");
        url[3] = getClass().getResource("/rotation.wav");
        url[4] = getClass().getResource("/touchFloor.wav");
    }
    public void play(int i, boolean music){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
            Clip clip = AudioSystem.getClip();

            if (music){
                musicClip = clip;
            }

            clip.open(ais);

            //FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            //gainControl.setValue(-30.0f);

            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if(event.getType() == LineEvent.Type.STOP){
                        clip.close();
                    }
                }
            });
            ais.close();
            clip.start();

        } catch (Exception e){

        }

    }

    public void playMusic() {
        play(0, true); // Play the music and initialize musicClip
        musicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
    }

    public void loop(){
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        musicClip.stop();
        musicClip.close();
    }


}
