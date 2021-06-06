package com.example.tabletapp.Server;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.lib.Note;
import com.example.lib.ServerRequests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionLoader extends AsyncTaskLoader<ServerRequests> {

    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ConnectionListener listener;

    public ServerConnectionLoader(@NonNull Context context) {
        super(context);
        try {
            serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConnectionListener(ConnectionListener listener) {
        this.listener = listener;
    }

    private void close() {
        try {
            objectInputStream.close();
            objectInputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }


    @Nullable
    @Override
    public ServerRequests loadInBackground() {
        try {
            socket = serverSocket.accept();
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ServerRequests request = listener.onWrite();
            objectOutputStream.writeObject(request);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                request = (ServerRequests)objectInputStream.readObject();
                if (request == ServerRequests.ADD_NOTE) {
                    Note note = (Note) objectInputStream.readObject();
                    listener.onAddNote(note);
                }
                else if (request == ServerRequests.DISCONNECT) {
                    close();
                    return ServerRequests.DISCONNECT;
                }
                else
                    listener.onСall(request);
            }

        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerRequests.ERROR;
    }
}
