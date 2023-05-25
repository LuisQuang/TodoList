package com.lequanganh.todolist;

import android.app.Application;

import androidx.room.Room;

import com.lequanganh.todolist.db.TaskDB;

public class App extends Application {
    private static final String DATABASE_NAME = "tasks.db";
    private static App instance;
    private Storage storage;
    private TaskDB db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new Storage();
        initDB();
    }

    private void initDB() {
        db = Room.databaseBuilder(getApplicationContext(), TaskDB.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
// .fallbackToDestructiveMigration() de khoi phuc dl neu mat, co 2 cach
    }

    public TaskDB getDb() {
        return db;
    }

    public Storage getStorage() {
        return storage;
    }
}
