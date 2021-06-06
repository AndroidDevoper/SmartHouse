package com.example.tabletapp.Server;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.lib.Note;
import com.example.lib.ServerRequests;
import com.example.tabletapp.R;
import com.example.tabletapp.RestApi.InternetAccess;


public class Server implements InternetAccess.OnListener, LoaderManager.LoaderCallbacks, ConnectionListener {


    private InternetAccess internetAccess;
    private LoaderManager loaderManager;
    private ServerConnectionLoader connectionLoader;
    private ServerListener listener;

    public Server(Context context, LoaderManager loaderManager, ServerListener listener) {
        this.loaderManager = loaderManager;
        this.listener = listener;
        internetAccess = new InternetAccess(context, this);
        internetAccess.execute();
        connectionLoader = new ServerConnectionLoader(context);
        connectionLoader.setConnectionListener(this);
    }

    @Override
    public void available() {
        initLoader(R.integer.server_connection_loader);
    }

    @Override
    public void notAvailable() {
        internetAccess.execute();
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
        return connectionLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object object) {
        if (object == ServerRequests.DISCONNECT || object == ServerRequests.ERROR) {
            loaderManager.destroyLoader(R.integer.server_connection_loader);
            listener.onDisconnect();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    private void readRequest(ServerRequests request) {
        if (request == ServerRequests.PLAY_MUSIC)
            listener.startMusic();
        else if (request == ServerRequests.STOP_MUSIC)
            listener.stopMusic();
        else if
            (request == ServerRequests.CONNECT);
    }

    private void initLoader(int id) {
        loaderManager.initLoader(id, Bundle.EMPTY, this);
    }

    @Override
    public void onСall(ServerRequests request) {
        readRequest(request);
    }

    @Override
    public ServerRequests onWrite() {
        return listener.getStatusMusic();
    }

    @Override
    public void onAddNote(Note note) {
            listener.addNote(note);
    }


}
