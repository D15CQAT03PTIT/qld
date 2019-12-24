package com.taxi.managerstudent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class ManagerMarksActivity extends AppCompatActivity {
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
    }
}
