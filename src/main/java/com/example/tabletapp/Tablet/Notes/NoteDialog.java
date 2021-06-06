package com.example.tabletapp.Tablet.Notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.tabletapp.R;

public class NoteDialog extends DialogFragment implements Dialog.OnClickListener {

    public interface CallBack {
        void onClick(int idBtn);
    }

    private CallBack callBack;
    private boolean isComplete;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (isComplete) return complete();
        else return cancel();
    }

    private Dialog complete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).
                setPositiveButton(R.string.text_btn_positive, this).
                setNeutralButton(R.string.text_btn_neutral, this).
                setMessage("Задание выполнено?");
        return builder.create();
    }

    private Dialog cancel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).
                setPositiveButton(R.string.text_btn_positive, this).
                setNeutralButton(R.string.text_btn_neutral, this).
                setMessage("Отменить задание?");
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int result = 0;
        if (which == -1)
            result = R.string.text_btn_positive;
        if (which == -3)
            result = R.string.text_btn_neutral;
        callBack.onClick(result);
    }

    public static NoteDialog newInstance(boolean ch, CallBack callback) {
        Bundle args = new Bundle();
        NoteDialog fragment = new NoteDialog();
        fragment.attach(callback, ch);
        fragment.setArguments(args);
        return fragment;
    }
    private void attach(CallBack callBack, boolean ch) {
        this.callBack = callBack;
        this.isComplete = ch;
    }

    @Override
    public void onDetach() {
        callBack = null;
        super.onDetach();
    }
}
