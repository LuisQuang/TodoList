package com.lequanganh.todolist.view.dialog;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.anhql.todolist.databinding.EditDialogBottomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lequanganh.todolist.App;
import com.lequanganh.todolist.db.entities.Task;
import com.lequanganh.todolist.viewmodel.OnShowList;

public class EditDialog extends BottomSheetDialog {
    private final EditDialogBottomBinding binding;
    private final Context context;
    private final Task task;
    private final OnShowList callBack;

    public EditDialog(@NonNull Context context, Task task, OnShowList callBack) {
        super(context);
        this.context = context;
        this.callBack = callBack;
        this.task = task;
        binding = EditDialogBottomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.icDone.setOnClickListener(v -> {
            if (binding.edtEditTask.getText().toString().isEmpty() && !binding.btn1.isChecked() && !binding.btn2.isChecked()
                    && !binding.btn3.isChecked() && !binding.btn4.isChecked()) {
            } else {
                updateTask(binding.edtEditTask.getText().toString(), getPriority() + "");
                dismiss();
            }
        });
        binding.edtEditTask.setHint(task.getName());
    }

    private int getPriority() {
        int mPr = Integer.parseInt(task.getPriority());
        if (binding.btn1.isChecked()) {
            mPr = 1;
        } else if (binding.btn2.isChecked()) {
            mPr = 2;
        } else if (binding.btn3.isChecked()) {
            mPr = 3;
        } else if (binding.btn4.isChecked()) {
            mPr = 4;
        }
        return mPr;
    }

    private void updateTask(String name, String priority) {
        if (name.isEmpty()) {
            task.setPriority(priority);
        } else {
            task.setName(name);
            task.setPriority(priority);
        }
        callBack.sortDataAndShow();
        new Thread(() -> {
            App.getInstance().getDb().taskDAO().deleteAll();
            App.getInstance().getDb().taskDAO().insertAll(App.getInstance().getStorage().tasks);
        }).start();
    }
}
