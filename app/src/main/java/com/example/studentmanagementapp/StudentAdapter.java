
package com.example.studentmanagementapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> studentList;
    private StudentDatabaseHelper databaseHelper;
    private TextView tvStudentCount;

    public StudentAdapter(ArrayList<Student> studentList,
                          StudentDatabaseHelper databaseHelper,
                          TextView tvStudentCount) {

        this.studentList = studentList;
        this.databaseHelper = databaseHelper;
        this.tvStudentCount = tvStudentCount;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull StudentViewHolder holder, int position) {

        Student student = studentList.get(position);

        holder.tvStudentName.setText(student.getName());
        holder.tvStudentEmail.setText(student.getEmail());
        holder.tvStudentPhone.setText("Phone: " + student.getPhone());
        holder.tvStudentDepartment.setText(
                "Department: " + student.getDepartment()
        );

        // Edit Student
        holder.btnEditStudent.setOnClickListener(v -> {

            Intent intent = new Intent(
                    holder.itemView.getContext(),
                    EditStudentActivity.class
            );

            intent.putExtra("student_id", student.getId());
            intent.putExtra("student_name", student.getName());
            intent.putExtra("student_email", student.getEmail());
            intent.putExtra("student_phone", student.getPhone());
            intent.putExtra("student_department", student.getDepartment());

            Context context = holder.itemView.getContext();

            context.startActivity(intent);

            // Slide transition animation
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                );
            }
        });

        // Delete Student
        holder.btnDeleteStudent.setOnClickListener(v -> {

            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Delete Student")
                    .setMessage("Are you sure you want to delete "
                            + student.getName() + "?")

                    .setPositiveButton("Delete", (dialog, which) -> {

                        boolean deleted = databaseHelper.deleteStudent(
                                student.getId()
                        );

                        if (deleted) {

                            int currentPosition =
                                    holder.getAdapterPosition();

                            if (currentPosition != RecyclerView.NO_POSITION) {
                                studentList.remove(currentPosition);
                                notifyItemRemoved(currentPosition);
                            }

                            tvStudentCount.setText(
                                    "Showing " + studentList.size()
                                            + " Students"
                            );

                            Toast.makeText(
                                    holder.itemView.getContext(),
                                    "Student deleted successfully",
                                    Toast.LENGTH_SHORT
                            ).show();

                        } else {

                            Toast.makeText(
                                    holder.itemView.getContext(),
                                    "Unable to delete student",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    })

                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentName, tvStudentEmail;
        TextView tvStudentPhone, tvStudentDepartment;

        Button btnEditStudent, btnDeleteStudent;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvStudentEmail = itemView.findViewById(R.id.tvStudentEmail);
            tvStudentPhone = itemView.findViewById(R.id.tvStudentPhone);
            tvStudentDepartment = itemView.findViewById(R.id.tvStudentDepartment);

            btnEditStudent = itemView.findViewById(R.id.btnEditStudent);
            btnDeleteStudent = itemView.findViewById(R.id.btnDeleteStudent);
        }
    }
}
