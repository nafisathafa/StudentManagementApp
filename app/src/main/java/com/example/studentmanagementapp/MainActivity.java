
package com.example.studentmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnViewStudents, btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewStudents = findViewById(R.id.btnViewStudents);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        // Navigate to Student List
        btnViewStudents.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    StudentListActivity.class
            );

            startActivity(intent);

            // Slide transition animation
            overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
            );
        });

        // Navigate to Add Student
        btnAddStudent.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    AddStudentActivity.class
            );

            startActivity(intent);

            // Slide transition animation
            overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
            );
        });
    }
}
