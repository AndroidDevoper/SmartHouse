package com.example.tabletapp.Tablet.Notes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib.Note;
import com.example.lib.ServerRequests;
import com.example.tabletapp.R;
import com.example.tabletapp.Server.Server;
import com.example.tabletapp.Server.ServerListener;
import com.example.tabletapp.Sounds.SoundManager;
import com.example.tabletapp.Sounds.SoundsRes;

import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter implements NotesBD.CallBack, NoteViewPresenter.CallBack{

    private ArrayList<Note> list;
    private NotesBD notesBD;
    private LayoutInflater inflater;
    private FragmentManager fragmentManager;
    private SoundManager soundManager;
    private NotesContract tabletPresenter;
    private Server server;
    private Activity activity;

    public NotesAdapter(Activity activity, NotesContract contract) {
        this.activity = activity;
        this.tabletPresenter = contract;
        Context context = activity.getBaseContext();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
        soundManager = new SoundManager(activity);
        notesBD = new NotesBD(context, this);
        initServer();
    }

    public void minutePassed() {
        notesBD.minutePassed();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_note, parent, false);
        final Note note = list.get(position);
        NoteViewPresenter presenter = new NoteViewPresenter(note, soundManager, notesBD, fragmentManager, this);
        initFindById(view, note);
        initOnClickListener(view, presenter);
        return view;
    }

    private void initOnClickListener(View view, final NoteViewPresenter presenter) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClick();
            }
        });
    }

    private void initFindById(View view, Note note) {
        ((TextView)view.findViewById(R.id.note_tv_recording)).setText(note.getRecording());
        ((TextView)view.findViewById(R.id.note_tv_title)).setText(note.getTitle());
        ((ImageView)view.findViewById(R.id.note_icon)).setImageResource(note.getResIcon());
        if (note.isAddTime) {
            (view.findViewById(R.id.note_divider)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.note_tv_time)).setText(note.getTime());
            ((TextView)view.findViewById(R.id.note_tv_date)).setText(note.getDate());
        }
        else  {
            (view.findViewById(R.id.note_divider)).setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoad(ArrayList<Note> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void upDateNote(Note note) {
        list.set(list.indexOf(note), note);
        notesBD.upData(list);
    }


    private void initServer() {

        @SuppressLint("HandlerLeak")
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int i = msg.what;
                if (i == 100) {
                    notesBD.upData(list);
                    soundManager.playSound(SoundsRes.ADD_NOTE);
                }
                else if (i == 110)
                    soundManager.stopBackgroundMusic();
                else if (i == 120)
                    soundManager.startBackgroundMusic();
                else if (i == 430) {
                    initServer();
                }
            }
        };

            server = new Server(activity.getBaseContext(),
                ((FragmentActivity) activity).getSupportLoaderManager(),
                new ServerListener() {
                    @Override
                    public void addNote(Note note) {
                        if (!note.isAddTime)
                            note.setResIcon(R.mipmap.questcomplete);
                        else
                            note.setResIcon(R.mipmap.questnotready);
                        list.add(note);
                        handler.sendEmptyMessage(100);
                    }

                    @Override
                    public void stopMusic() {
                        handler.sendEmptyMessage(110);
                    }

                    @Override
                    public void startMusic() {
                        handler.sendEmptyMessage(120);
                    }

                    @Override
                    public ServerRequests getStatusMusic() {
                        ServerRequests request;
                        int status = soundManager.getStatusMusic();
                        if (status == 0)
                            request = ServerRequests.STATUS_MUSIC_OFF;
                        else
                            request = ServerRequests.STATUS_MUSIC_ON;
                        return request;
                    }

                    @Override
                    public void onDisconnect() {
                        handler.sendEmptyMessage(430);
                    }
                });
    }
}
