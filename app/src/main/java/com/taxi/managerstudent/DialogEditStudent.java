package com.taxi.managerstudent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.taxi.managerstudent.models.StudentResponse;

public class DialogEditStudent extends BaseDialogFragment {
    private OnMyClickListener onMyClickListener = null;
    AppCompatEditText edStName, edStCode, edBirthday, edHomeTown;
    AppCompatTextView btnAdd;
    private StudentResponse studentResponse;

    public static DialogEditStudent newInstance(Context context, StudentResponse studentResponse) {
        DialogEditStudent textDialog = new DialogEditStudent();
        textDialog.context = context;
        textDialog.studentResponse = studentResponse;
        return textDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.FragmentDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_student, container);
        setContentCustom(view);
        return view;
    }

    private void setContentCustom(View v) {
        edStName = v.findViewById(R.id.dialog_st_ed_student_name);
        edStCode = v.findViewById(R.id.dialog_st_ed_student_code);
        edBirthday = v.findViewById(R.id.dialog_st_ed_student_birthday);
        edHomeTown = v.findViewById(R.id.dialog_st_ed_student_home_town);
        btnAdd = v.findViewById(R.id.dialog_btn_add);
        bindData(studentResponse);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edStName.getText().toString().trim();
                String code = edStCode.getText().toString().trim();
                String birthday = edBirthday.getText().toString().trim();
                String hometown = edHomeTown.getText().toString().trim();
                studentResponse.setStudentName(name);
                studentResponse.setClassCode(code);
                studentResponse.setStudentBirhDay(birthday);
                studentResponse.setStudentAdress(hometown);
                onMyClickListener.clickOk(studentResponse);
                dismiss();
            }
        });
    }

    private void bindData(StudentResponse studentResponse) {
        edStName.setText(studentResponse.getStudentName());
        edStCode.setText(studentResponse.getStudentCode());
        edBirthday.setText(studentResponse.getStudentBirhDay());
        edHomeTown.setText(studentResponse.getStudentAdress());
    }

    public void setClickListener(OnMyClickListener clickListener) {
        this.onMyClickListener = clickListener;
    }

    public interface OnMyClickListener {
        void clickOk(StudentResponse studentResponse);
    }
}
