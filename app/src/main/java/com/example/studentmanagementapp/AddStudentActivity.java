
package com.example.studentmanagementapp;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone, etDepartment;
    Button btnSubmitStudent;
    StudentDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etDepartment = findViewById(R.id.etDepartment);
        btnSubmitStudent = findViewById(R.id.btnSubmitStudent);

        databaseHelper = new StudentDatabaseHelper(this);

        btnSubmitStudent.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String department = etDepartment.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()
                    || phone.isEmpty() || department.isEmpty()) {

                Toast.makeText(this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Enter a valid email address");
                etEmail.requestFocus();
                return;
            }

            if (!phone.matches("[0-9]{10}")) {
                etPhone.setError("Enter a valid 10-digit phone number");
                etPhone.requestFocus();
                return;
            }

            boolean inserted = databaseHelper.addStudent(
                    name, email, phone, department
            );

            if (inserted) {
                Toast.makeText(this,
                        "Student added successfully!",
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this,
                        "Failed to add student",
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
