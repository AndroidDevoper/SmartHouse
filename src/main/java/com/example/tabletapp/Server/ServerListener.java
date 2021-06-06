package com.example.tabletapp.Server;

import com.example.lib.Note;
import com.example.lib.ServerRequests;

public interface ServerListener {
    void addNote(Note note);
    void stopMusic();
    void startMusic();
    ServerRequests getStatusMusic();
    void onDisconnect();
}
