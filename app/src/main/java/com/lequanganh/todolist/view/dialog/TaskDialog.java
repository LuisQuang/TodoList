package com.lequanganh.todolist.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.anhql.todolist.databinding.AddTaskBinding;
import com.lequanganh.todolist.App;
import com.lequanganh.todolist.db.entities.Task;
import com.lequanganh.todolist.model.Priority;
import com.lequanganh.todolist.viewmodel.OnShowList;

public class TaskDialog extends Dialog {
    private final AddTaskBinding binding;
    public OnShowList callBack;

    public TaskDialog(@NonNull Context context, OnShowList callBack) {
        super(context);
        binding = AddTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.callBack = callBack;
        initView();
    }

    private void initView() {

        binding.btnAdd.setOnClickListener(v -> addTask(binding.tvTitle.getText().toString(), getPriority().getCode()));
    }

    private Priority getPriority() {
        if (binding.btn1.isChecked()) {
            return Priority.LOW;
        } else if (binding.btn2.isChecked()) {
            return Priority.MEDIUM;
        } else if (binding.btn3.isChecked()) {
            return Priority.HIGH;
        } else {
            return Priority.VERY_HIGH;
        }
    }

    private void addTask(String task, String priority) {
        if (task.isEmpty() || priority.isEmpty()) {
            Toast.makeText(getContext(), "Add contents!!!", Toast.LENGTH_SHORT).show();
        } else {
            App.getInstance().getStorage().tasks.add(new Task(0, task, priority));
            new Thread(() -> {
                App.getInstance().getDb().taskDAO().insertTask(new Task(0, task, priority));
            }).start();
            callBack.sortDataAndShow();
            dismiss();
        }
    }
}
