package com.example.tabletapp;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {

    public interface CallBack {
        void onRead(Object result);
    }

    private Context context;
    private CallBack callBack;

    public FileManager(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public void writeFile(String fileName, Object object) {
        delete(fileName);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    new FileOutputStream(context.getFilesDir() + "/" + fileName)
            );
            outputStream.writeObject(object);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String fileName) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(
                    new FileInputStream(
                            context.getFilesDir() + "/" + fileName
                    )
            );
            Object object = inputStream.readObject();
            callBack.onRead(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            callBack.onRead(null);
        } catch (IOException e) {
            e.printStackTrace();
            callBack.onRead(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(String fileName) {
        File file = new File(context.getFilesDir() + "/" +
                fileName);
        file.delete();
    }
}
