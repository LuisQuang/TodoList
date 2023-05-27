package com.anhql.todolist.view.act;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.anhql.todolist.App;
import com.anhql.todolist.R;
import com.anhql.todolist.databinding.MainScrBinding;
import com.anhql.todolist.db.entities.Task;
import com.anhql.todolist.view.adapter.TaskAdapter;
import com.anhql.todolist.view.dialog.EditDialog;
import com.anhql.todolist.view.dialog.TaskDialog;
import com.anhql.todolist.viewmodel.CommonVM;

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
        sweepDeleteTask();
        clickMenu();
    }

    private void clickMenu() {
        ImageView ivMenu = findViewById(R.id.iv_menu);
        ivMenu.setOnClickListener(v -> binding.drawerLayout.open());

    }

    private void sweepDeleteTask() {
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Task t = App.getInstance().getStorage().tasks.get(position);
                new Thread(() -> App.getInstance().getDb().taskDAO().deleteTask(t)).start();
                App.getInstance().getStorage().tasks.remove(position);
                binding.recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    Paint paint = new Paint();
                    paint.setColor(Color.parseColor("#F6F6C9"));

                    if (dX > 0) {
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, paint);
                    } else {
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, paint);
                    }

                    Drawable icon = ContextCompat.getDrawable(Main.this, R.drawable.ic_delete);
                    if (icon != null) {
                        int iconHeight = icon.getIntrinsicHeight();
                        int iconWidth = icon.getIntrinsicWidth();
                        int margin = (int) ((height - iconHeight) / 2);

                        if (dX > 0) {
                            icon.setBounds(itemView.getLeft() + margin, itemView.getTop() + margin, itemView.getLeft() + margin + iconWidth, itemView.getBottom() - margin);
                        } else {
                            icon.setBounds(itemView.getRight() - margin - iconWidth, itemView.getTop() + margin, itemView.getRight() - margin, itemView.getBottom() - margin);
                        }

                        icon.draw(c);
                    }
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
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

