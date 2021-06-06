package com.example.tabletapp.Sounds;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.concurrent.TimeUnit;


public class SoundManager implements SoundPool.OnLoadCompleteListener, Runnable, MediaPlayer.OnCompletionListener{

    private Context context;
    private SoundPool soundPool;
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer newMediaPlayer;

    public SoundManager(Activity activity) {
        context = activity.getBaseContext();
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        soundPool.setOnLoadCompleteListener(this);
        initBackgroundMusicPlayer();
        load();
    }

    private void initBackgroundMusicPlayer() {
        backgroundMusicPlayer = MediaPlayer.create(context, MusicRes.BACKGROUND_MUSIC.getId());
        backgroundMusicPlayer.setOnCompletionListener(this);
        backgroundMusicPlayer.setVolume(0.5f,0.5f);
        backgroundMusicPlayer.start();
    }

    public void startBackgroundMusic() {
        backgroundMusicPlayer.start();
    }

    public void stopBackgroundMusic() {
        backgroundMusicPlayer.pause();
    }

    public int getStatusMusic() {
        if (backgroundMusicPlayer.isPlaying()) return 1;
        else return 0;
    }

    public void playSound(SoundsRes sound) {
        if (backgroundMusicPlayer.isPlaying()) {
            backgroundMusicPlayer.pause();
            Thread thread = new Thread(this);
            thread.start();
        }
        soundPool.play(sound.getSoundId(), 1, 1, 1, 0, 1);
    }

    public void stopMusic(MusicRes music) {
        newMediaPlayer.stop();
        backgroundMusicPlayer.start();
    }

    public void playMusic(MusicRes music) {
        backgroundMusicPlayer.pause();
        newMediaPlayer = MediaPlayer.create(context, music.getId());
        newMediaPlayer.setOnCompletionListener(this);
        newMediaPlayer.setVolume(1,1);
        newMediaPlayer.start();
    }

    private void load() {
        int soundId_completeNote = soundPool.load(context, SoundsRes.COMPLETE_NOTE.getId(), 1);
        int soundId_addNote = soundPool.load(context, SoundsRes.ADD_NOTE.getId(), 1);
        int soundId_cancelNote = soundPool.load(context, SoundsRes.CANCEL_NOTE.getId(), 1);
        SoundsRes.COMPLETE_NOTE.setSoundId(soundId_completeNote);
        SoundsRes.ADD_NOTE.setSoundId(soundId_addNote);
        SoundsRes.CANCEL_NOTE.setSoundId(soundId_cancelNote);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            backgroundMusicPlayer.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        backgroundMusicPlayer.start();
    }
}
