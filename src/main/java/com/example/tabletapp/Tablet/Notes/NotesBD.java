package com.example.tabletapp.Tablet.Notes;

import android.content.Context;

import com.example.lib.Note;
import com.example.tabletapp.FileManager;
import com.example.tabletapp.R;
import com.example.tabletapp.Tablet.DateTablet;

import java.util.ArrayList;

public class NotesBD implements FileManager.CallBack{

    interface CallBack {
        void onLoad(ArrayList<Note> list);
    }

    private ArrayList<Note> noteList;
    private FileManager fileManager;
    private CallBack callBack;
    private DateTablet dateTablet;
    final private String fileName = "Notes.out";


    public NotesBD(Context context, CallBack callBack) {
        this.callBack = callBack;
        fileManager = new FileManager(context, this);
        fileManager.readFile(fileName);
    }

    public void minutePassed() {
        if (noteList != null) {
            for (Note note : noteList) {
                if (note.isAddTime) {
                    dateTablet = new DateTablet();
                    if (note.getTime().equals(dateTablet.getTime()) &&
                            note.getDate().equals(dateTablet.getDayAndMonth()))
                        note.isTimeHasCome = true;
                }
            }
            upData(noteList);
        }
    }

    public void refresh(Note note) {
        NotesSorter.setNextDay(note);
        note.setResIcon(R.mipmap.questnotready);
        note.isTimeHasCome = false;
        note.isViewed = false;
        upDataFile();
    }

    public void upData(ArrayList<Note> noteList) {
        this.noteList = noteList;
        upDataFile();
    }


    public void delete(Note note) {
        noteList.remove(note);
        upDataFile();
    }

    @Override
    public void onRead(Object result) {
        if (result == null) {
            fileManager.writeFile(fileName, new ArrayList<>());
            noteList = new ArrayList<>();
        }
        else
            noteList = (ArrayList<Note>) result;
        noteList = NotesSorter.sort(noteList);
        callBack.onLoad(noteList);
    }

    private void upDataFile() {
        noteList = NotesSorter.sort(noteList);
        fileManager.writeFile(fileName, noteList);
        callBack.onLoad(noteList);
    }
}
