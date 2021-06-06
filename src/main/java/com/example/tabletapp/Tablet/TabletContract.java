package com.example.tabletapp.Tablet;

import com.example.tabletapp.Tablet.Notes.NotesAdapter;

public interface TabletContract {
    void setDate(String time, String date);
    void showToast(String text);
    int getIdFragmentContainers();
    void setNoteAdapter(NotesAdapter adapter);
}
