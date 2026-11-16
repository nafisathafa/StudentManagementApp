
package com.example.studentmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    RecyclerView recyclerViewStudents;
    TextView tvStudentCount, tvEmptyMessage;
    EditText etSearchStudent;
    Button btnAddStudentFromList;

    StudentDatabaseHelper databaseHelper;
    StudentAdapter studentAdapter;

    ArrayList<Student> studentList;
    ArrayList<Student> filteredStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        recyclerViewStudents = findViewById(R.id.recyclerViewStudents);
        tvStudentCount = findViewById(R.id.tvStudentCount);
        tvEmptyMessage = findViewById(R.id.tvEmptyMessage);
        etSearchStudent = findViewById(R.id.etSearchStudent);
        btnAddStudentFromList = findViewById(R.id.btnAddStudentFromList);

        databaseHelper = new StudentDatabaseHelper(this);

        recyclerViewStudents.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Navigate to Add Student screen
        btnAddStudentFromList.setOnClickListener(v -> {

            Intent intent = new Intent(
                    StudentListActivity.this,
                    AddStudentActivity.class
            );

            startActivity(intent);

            // Slide transition animation
            overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
            );
        });

        // Search students by name
        etSearchStudent.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {

                filterStudents(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        studentList = databaseHelper.getAllStudents();

        filterStudents(etSearchStudent.getText().toString());
    }

    // Filter students and update RecyclerView
    private void filterStudents(String query) {

        filteredStudentList = new ArrayList<>();

        for (Student student : studentList) {

            if (student.getName().toLowerCase()
                    .contains(query.toLowerCase().trim())) {

                filteredStudentList.add(student);
            }
        }

        studentAdapter = new StudentAdapter(
                filteredStudentList,
                databaseHelper,
                tvStudentCount
        );

        recyclerViewStudents.setAdapter(studentAdapter);

        tvStudentCount.setText(
                "Showing " + filteredStudentList.size()
                        + " of " + studentList.size() + " Students"
        );

        // Empty-state message
        if (filteredStudentList.isEmpty()) {

            tvEmptyMessage.setVisibility(View.VISIBLE);

            if (studentList.isEmpty()) {
                tvEmptyMessage.setText("No students added yet");
            } else {
                tvEmptyMessage.setText("No students found");
            }

        } else {
            tvEmptyMessage.setVisibility(View.GONE);
        }
    }
}
