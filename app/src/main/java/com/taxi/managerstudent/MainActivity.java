package com.taxi.managerstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    AppCompatTextView btnManagerStudent;
    AppCompatTextView btnManagerSubject;
    AppCompatTextView btnManagerMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnManagerStudent = findViewById(R.id.main_btn_manager_student);
        btnManagerSubject = findViewById(R.id.main_btn_manager_subject);
        btnManagerMarks = findViewById(R.id.main_btn_manager_marks);

        btnManagerStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,ManagerStudentActivity.class);
                startActivity(intent);
            }
        });
        btnManagerSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,ManagerSubjectActivity.class);
                startActivity(intent);
            }
        });
        btnManagerMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,ManagerMarksActivity.class);
                startActivity(intent);
            }
        });
    }
}
