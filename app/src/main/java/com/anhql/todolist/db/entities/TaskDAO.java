package com.anhql.todolist.db.entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {

    @Query("SELECT * FROM tasks")
    List<Task> getAllTasks();

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Insert
    void insertAll(List<Task> tasks);

    @Query("DELETE FROM tasks")
    void deleteAll();
}
