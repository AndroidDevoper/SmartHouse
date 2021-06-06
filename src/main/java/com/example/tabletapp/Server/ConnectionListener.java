package com.example.tabletapp.Server;

import com.example.lib.Note;
import com.example.lib.ServerRequests;

public interface ConnectionListener {
    void onСall(ServerRequests request);
    ServerRequests onWrite();
    void onAddNote(Note note);
}
