package com.example.faculty_of_science;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeWork extends AppCompatActivity {
    EditText editText;
    Button add, delete, show, watch;
    TextView note, showdata;
    DataBase DB;

    //String Id=String.valueOf(id);

    // Declare a RecyclerView and its adapter
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);
        DB =new DataBase(this);
        // Initialize the RecyclerView and its adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);
        watch = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTask);
        add = findViewById(R.id.buttonAddTask);
        delete = findViewById(R.id.buttonClearTasks);
        show = findViewById(R.id.buttonShowTasks);
        note = findViewById(R.id.textView2);
        // showdata = findViewById(R.id.textView3);
        Intent incomingId=getIntent();
        id=incomingId.getExtras().getInt("id");
       String Id=String.valueOf(id);

    }



    public void addTask(View view) {
        String error = "";
        String taskname = editText.getText().toString();

        // Check if the task name is empty
        if (taskname.length() == 0) {
            error += "Task cannot be empty";
            note.setText(error);
            return;
        }

        // Create a To_Do object with the task name
        To_Do task = new To_Do(taskname);

        // Insert the task into the database using the 'student' object
        if (!DB.insertTask(task,id)) {
            note.setText(DB.getError());
        } else {
            note.setText("Task added Successfully");
            editText.setText(""); // Clear the input field
            taskAdapter.addTask(task); // Add the task to the adapter

        }
    }

    public void deleteTask(View view) {
        List<To_Do> checkedTasks = new ArrayList<>();

        // Find the checked tasks
        for (To_Do task : taskAdapter.taskList) {
            if (task.isChecked()) {
                checkedTasks.add(task);
            }
        }

        // Delete the checked tasks from the database
        for (To_Do task : checkedTasks) {
            if (DB.delete(task.getName())) {
                taskAdapter.taskList.remove(task);
            }
        }

        taskAdapter.notifyDataSetChanged();
    }

    public void showTask(View v) {

        Cursor cursor = DB.show(id);
        if (cursor == null) {
            note.setText(DB.getError());
            return;
        } else {

            taskAdapter.clearTasks();


            while (cursor.moveToNext()) {
                @SuppressLint("Range") String showname = cursor.getString(cursor.getColumnIndex("name"));
                To_Do t = new To_Do(showname);
                taskAdapter.addTask(t);
            }


            taskAdapter.notifyDataSetChanged();
        }
    }

    public void stopWatch(View view) {
        Intent intent = new Intent(this, StopWatch.class);
        startActivity(intent);
    }


    private class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
        private List<To_Do> taskList;

        public TaskAdapter() {
            taskList = new ArrayList<>();
        }


        public void clearTasks() {
            taskList.clear();
        }

        public void addTask(To_Do task) {
            taskList.add(task);
            notifyItemInserted(taskList.size() - 1);
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            To_Do task = taskList.get(position);
            holder.checkBoxTask.setChecked(false); // Set the initial checkbox state
            holder.textViewTaskName.setText(task.getName());
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }


        public class TaskViewHolder extends RecyclerView.ViewHolder {
            CheckBox checkBoxTask;
            TextView textViewTaskName;

            public TaskViewHolder(@NonNull View itemView) {
                super(itemView);
                checkBoxTask = itemView.findViewById(R.id.checkBoxTask);
                textViewTaskName = itemView.findViewById(R.id.textViewTaskName);

                checkBoxTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            To_Do task = taskList.get(position);
                            task.setChecked(isChecked);
                        }
                    }
                });
            }
        }
    }
}