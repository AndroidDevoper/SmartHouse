package com.example.tabletapp.Sounds;

import com.example.tabletapp.R;

public enum MusicRes {
    BACKGROUND_MUSIC(R.raw.backgroundmusic),
    TIME_TO_PLAY(R.raw.timetoplay);


    private int id;

    MusicRes(int id)  {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
