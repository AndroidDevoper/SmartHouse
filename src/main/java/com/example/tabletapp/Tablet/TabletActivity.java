package com.example.tabletapp.Tablet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabletapp.R;
import com.example.tabletapp.Tablet.Notes.NotesAdapter;


public class TabletActivity extends AppCompatActivity implements TabletContract {

    private TabletPresenter presenter;
    private FrameLayout frameLayout;
    private TextView tv_time;
    private TextView tv_date;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        init();
    }

    private void init() {
        frameLayout = findViewById(R.id.tablet_fragment_containers);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickWeather();
            }
        });
        tv_time = findViewById(R.id.tablet_tv_time);
        tv_date = findViewById(R.id.tablet_tv_date);
        listView = findViewById(R.id.tablet_listView);
        presenter = new TabletPresenter(this, this);
    }

    @Override
    public void setDate(String time, String date) {
        tv_time.setText(time);
        tv_date.setText(date);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getIdFragmentContainers() {
        return frameLayout.getId();
    }

    @Override
    public void setNoteAdapter(NotesAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }


}
