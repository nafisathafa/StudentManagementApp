
package com.example.studentmanagementapp;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditStudentActivity extends AppCompatActivity {

    EditText etEditName, etEditEmail, etEditPhone, etEditDepartment;
    Button btnUpdateStudent;

    StudentDatabaseHelper databaseHelper;
    int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        etEditName = findViewById(R.id.etEditName);
        etEditEmail = findViewById(R.id.etEditEmail);
        etEditPhone = findViewById(R.id.etEditPhone);
        etEditDepartment = findViewById(R.id.etEditDepartment);
        btnUpdateStudent = findViewById(R.id.btnUpdateStudent);

        databaseHelper = new StudentDatabaseHelper(this);

        studentId = getIntent().getIntExtra("student_id", -1);

        etEditName.setText(getIntent().getStringExtra("student_name"));
        etEditEmail.setText(getIntent().getStringExtra("student_email"));
        etEditPhone.setText(getIntent().getStringExtra("student_phone"));
        etEditDepartment.setText(
                getIntent().getStringExtra("student_department")
        );

        btnUpdateStudent.setOnClickListener(v -> {

            String name = etEditName.getText().toString().trim();
            String email = etEditEmail.getText().toString().trim();
            String phone = etEditPhone.getText().toString().trim();
            String department = etEditDepartment.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()
                    || phone.isEmpty() || department.isEmpty()) {

                Toast.makeText(this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEditEmail.setError("Enter a valid email address");
                etEditEmail.requestFocus();
                return;
            }

            if (!phone.matches("[0-9]{10}")) {
                etEditPhone.setError("Enter a valid 10-digit phone number");
                etEditPhone.requestFocus();
                return;
            }

            boolean updated = databaseHelper.updateStudent(
                    studentId, name, email, phone, department
            );

            if (updated) {
                Toast.makeText(this,
                        "Student updated successfully!",
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this,
                        "Failed to update student",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );
    }
}
