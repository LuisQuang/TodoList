package com.anhql.todolist.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.anhql.todolist.R;
import com.anhql.todolist.db.entities.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private final Context context;
    private final List<Task> tasks;
    private final View.OnClickListener event;

    public TaskAdapter(Context context, List<Task> tasks, View.OnClickListener event) {
        this.context = context;
        this.tasks = tasks;
        this.event = event;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = tasks.get(position);
        holder.tvTitle.setText(task.getName());
        holder.tvTitle.setTag(task);
        holder.chbState.setChecked(false);
        setViewBackground(holder, task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    private void setViewBackground(TaskHolder holder, Task task) {
        int priority = Integer.parseInt(task.getPriority());
        int backgroundColor = 0;
        switch (priority) {
            case 1:
                backgroundColor = ContextCompat.getColor(context, R.color.my_red);
                break;
            case 2:
                backgroundColor = ContextCompat.getColor(context, R.color.my_orange);
                break;
            case 3:
                backgroundColor = ContextCompat.getColor(context, R.color.my_blue);
                break;
            case 4:
                backgroundColor = ContextCompat.getColor(context, R.color.my_green);
                break;
        }
        GradientDrawable backgroundDrawable = new GradientDrawable();
        float cornerRadius = context.getResources().getDimension(R.dimen.corner_radius);
        backgroundDrawable.setCornerRadius(cornerRadius);
        backgroundDrawable.setColor(backgroundColor);
        backgroundDrawable.setGradientRadius(cornerRadius);
        ViewCompat.setBackground(holder.itemView, backgroundDrawable);
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CheckBox chbState;

        public TaskHolder(@NonNull View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_title);
            chbState = v.findViewById(R.id.chb_state);
            tvTitle.setOnClickListener(event);
            chbState.setOnCheckedChangeListener((buttonView, isChecked) -> removeViewClicked(isChecked));
        }

        @SuppressLint("NotifyDataSetChanged")
        private void removeViewClicked(boolean isChecked) {
            int pos = getAdapterPosition();
            Task task = tasks.get(pos);
            if (isChecked) {
//                new Thread(() -> App.getInstance().getDb().taskDAO()
//                        .deleteTask(task)).start();
//                tasks.remove(pos);
//                notifyItemRemoved(pos);
//                Xây dựng tính năng lưu task vào mục đã hoàn thành
            }
        }
    }

}
