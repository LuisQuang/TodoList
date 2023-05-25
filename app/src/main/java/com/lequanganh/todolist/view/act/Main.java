package com.lequanganh.todolist.view.act;

import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.ItemTouchHelper;

import com.anhql.todolist.databinding.MainScrBinding;
import com.lequanganh.todolist.App;
import com.lequanganh.todolist.db.entities.Task;
import com.lequanganh.todolist.view.SwipeItemTouchHelperCallback;
import com.lequanganh.todolist.view.adapter.TaskAdapter;
import com.lequanganh.todolist.view.dialog.EditDialog;
import com.lequanganh.todolist.view.dialog.TaskDialog;
import com.lequanganh.todolist.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.Comparator;

public class Main extends BaseAct<MainScrBinding, CommonVM> {


    @Override
    protected void initViews() {
        getDataFromDB();
        binding.fab.setOnClickListener(v -> {
            v.setAnimation(AnimationUtils.loadAnimation(Main.this, androidx.appcompat.R.anim.abc_fade_in));
            addTask();
        });
        deleteTask();
    }

    private void deleteTask() {
        SwipeItemTouchHelperCallback swipeCallback = new SwipeItemTouchHelperCallback(position -> {
            Task t = App.getInstance().getStorage().tasks.get(position);
            new Thread(() -> App.getInstance().getDb().taskDAO().deleteTask(t)).start();
            App.getInstance().getStorage().tasks.remove(position);
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeCallback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
    }

    private void getDataFromDB() {
        new Thread(() -> {
            App.getInstance().getStorage().tasks = (ArrayList<Task>) App.getInstance().getDb().taskDAO().getAllTasks();
            sortDataAndShow();
        }).start();
    }


    private void addTask() {
        TaskDialog dialog = new TaskDialog(this, this::sortDataAndShow);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.show();
        }
    }

    private void sortDataAndShow() {
        if (App.getInstance().getStorage().tasks == null || App.getInstance().getStorage().tasks.isEmpty())
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            App.getInstance().getStorage().tasks.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getPriority())));
            TaskAdapter adapter = new TaskAdapter(this, App.getInstance().getStorage().tasks, v -> showEditTask((Task) v.getTag()));
            binding.recyclerView.setAdapter(adapter);

        }
    }

    private void showEditTask(Task task) {
        EditDialog dialog = new EditDialog(this, task, this::sortDataAndShow);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.show();
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected MainScrBinding initViewBinding() {
        return MainScrBinding.inflate(getLayoutInflater());
    }
}

