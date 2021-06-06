package com.example.tabletapp.Sounds;

import com.example.tabletapp.R;

public enum SoundsRes {
    COMPLETE_NOTE(R.raw.complitenote),
    ADD_NOTE(R.raw.addnote),
    CANCEL_NOTE(R.raw.cancelnote);

    int id;
    int soundId;

    SoundsRes(int id) {
        this.id = id;
    }

    int getId(){
        return id;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }
}
