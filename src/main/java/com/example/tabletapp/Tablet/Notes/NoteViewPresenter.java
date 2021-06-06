package com.example.tabletapp.Tablet.Notes;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.example.lib.Note;
import com.example.tabletapp.R;
import com.example.tabletapp.Sounds.MusicRes;
import com.example.tabletapp.Sounds.SoundManager;
import com.example.tabletapp.Sounds.SoundsRes;

public class NoteViewPresenter {

    public interface CallBack {
        void upDateNote(Note note);
    }

    private final Note note;
    private final SoundManager soundManager;
    private final NotesBD notesBD;
    private final FragmentManager fragmentManager;
    private CallBack callBack;

    public NoteViewPresenter(Note note, SoundManager soundManager, NotesBD notesBD, FragmentManager fragmentManager, CallBack callBack) {
        this.note = note;
        this.soundManager = soundManager;
        this.notesBD = notesBD;
        this.fragmentManager = fragmentManager;
        this.callBack = callBack;
        checkTime();
    }

    private void checkTime() {
        if (note.isTimeHasCome && !note.isViewed) {
            soundManager.playMusic(MusicRes.TIME_TO_PLAY);
            note.setResIcon(R.mipmap.questready);
        }
    }

    public void onClick() {
        if (!note.isAddTime || note.isTimeHasCome)
            showCompleteDialog(note);
        else
            showCancelDialog(note);
    }

    private void showCompleteDialog(final Note note) {
        if (note.isTimeHasCome && !note.isViewed) {
            soundManager.stopMusic(MusicRes.TIME_TO_PLAY);
            note.isViewed = true;
        }
        DialogFragment dialog = NoteDialog.newInstance(true, new NoteDialog.CallBack() {
            @Override
            public void onClick(int idBtn) {
                if (idBtn == R.string.text_btn_positive) {
                    soundManager.playSound(SoundsRes.COMPLETE_NOTE);
                    if (note.isEveryday)
                        notesBD.refresh(note);
                    else
                        notesBD.delete(note);
                }
                else
                    callBack.upDateNote(note);
            }
        });
        dialog.show(fragmentManager.beginTransaction(), "CompleteNote");
    }

    private void showCancelDialog(final Note note) {
        DialogFragment dialog = NoteDialog.newInstance(false, new NoteDialog.CallBack() {
            @Override
            public void onClick(int idBtn) {
                if (idBtn == R.string.text_btn_positive) {
                    notesBD.delete(note);
                    soundManager.playSound(SoundsRes.CANCEL_NOTE);
                }
            }
        });
        dialog.show(fragmentManager.beginTransaction(), "CancelNote");
    }
}
