package com.taxi.managerstudent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.taxi.managerstudent.models.StudentResponse;
import com.taxi.managerstudent.models.SubjectResponse;

import java.util.List;

public class DialogAddMarks extends BaseDialogFragment {
    private OnMyClickListener onMyClickListener = null;
    AppCompatEditText edMarksCC, edMarksBTL, edMarksCK;
    AppCompatSpinner spinnerSubject;
    AppCompatTextView btnAdd;
    private StudentResponse studentResponse;
    private List<SubjectResponse> list;
    private SubjectResponse subjectResponse;

    public static DialogAddMarks newInstance(Context context, StudentResponse studentResponse, List<SubjectResponse> list) {
        DialogAddMarks textDialog = new DialogAddMarks();
        textDialog.context = context;
        textDialog.list = list;
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
        View view = inflater.inflate(R.layout.dialog_add_marks, container);
        setContentCustom(view);
        return view;
    }

    private void setContentCustom(View v) {
        spinnerSubject = v.findViewById(R.id.dialog_marks_spinner);
        edMarksCC = v.findViewById(R.id.dialog_marks_ed_cc);
        edMarksBTL = v.findViewById(R.id.dialog_marks_ed_btl);
        edMarksCK = v.findViewById(R.id.dialog_marks_ed_ck);
        btnAdd = v.findViewById(R.id.dialog_marks_btn_add);
        bindData(list);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cc = edMarksCC.getText().toString().trim();
                String btl = edMarksBTL.getText().toString().trim();
                String ck = edMarksCK.getText().toString().trim();
                onMyClickListener.clickOk(cc, btl, ck, subjectResponse.getSubjectCode(), studentResponse.getStudentCode());
                dismiss();
            }
        });
    }

    private void bindData(List<SubjectResponse> list) {
        final SpinnerSubjectAdapter spinnerSubjectAdapter = new SpinnerSubjectAdapter(context, R.layout.item_spinner, list);
        spinnerSubject.setAdapter(spinnerSubjectAdapter);
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectResponse = spinnerSubjectAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setClickListener(OnMyClickListener clickListener) {
        this.onMyClickListener = clickListener;
    }

    public interface OnMyClickListener {
        void clickOk(String cc, String btl, String ck, String subjectCode, String stCode);
    }
}
