package com.anhql.todolist.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.anhql.todolist.db.entities.Task;
import com.anhql.todolist.db.entities.TaskDAO;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDB extends RoomDatabase {
    private static TaskDB instance;
    public abstract TaskDAO taskDAO();
}
