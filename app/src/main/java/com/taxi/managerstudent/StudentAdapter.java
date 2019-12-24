package com.taxi.managerstudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.taxi.managerstudent.models.StudentResponse;
import com.taxi.managerstudent.models.SubjectResponse;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<StudentResponse> list;
    private List<SubjectResponse> listSubject;
    private AppCompatActivity context;
    private DialogEditStudent.OnMyClickListener onMyClickListener;
    private DialogAddMarks.OnMyClickListener clickListener;

    public StudentAdapter(List<StudentResponse> list, List<SubjectResponse> listSubject, AppCompatActivity context
            , DialogEditStudent.OnMyClickListener onMyClickListener, DialogAddMarks.OnMyClickListener clickListener) {
        this.list = list;
        this.context = context;
        this.listSubject = listSubject;
        this.clickListener = clickListener;
        this.onMyClickListener = onMyClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_student, null);
        return new StudentHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StudentHolder studentHolder = (StudentHolder) holder;
        final StudentResponse studentResponse = list.get(holder.getAdapterPosition());
        studentHolder.tvStName.setText(studentResponse.getStudentName());
        studentHolder.tvStCode.setText(studentResponse.getStudentCode());
        studentHolder.tvStBirthday.setText(studentResponse.getStudentBirhDay());
        studentHolder.tvStHomeTown.setText(studentResponse.getStudentAdress());

        studentHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditStudent dialogEditStudent = DialogEditStudent.newInstance(context, studentResponse);
                dialogEditStudent.setClickListener(new DialogEditStudent.OnMyClickListener() {
                    @Override
                    public void clickOk(StudentResponse studentResponse) {
                        onMyClickListener.clickOk(studentResponse);
                    }
                });
                dialogEditStudent.show(context.getSupportFragmentManager(), "StudentAdapter");
            }
        });

        studentHolder.btnAddMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddMarks dialogAddMarks = DialogAddMarks.newInstance(context, studentResponse, listSubject);
                dialogAddMarks.setClickListener(new DialogAddMarks.OnMyClickListener() {
                    @Override
                    public void clickOk(String cc, String btl, String ck, String subjectCode, String stCode) {
                        clickListener.clickOk(cc, btl, ck, subjectCode, stCode);
                    }
                });
                dialogAddMarks.show(context.getSupportFragmentManager(), "StudentAdapter");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class StudentHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvStName;
        AppCompatTextView tvStCode;
        AppCompatTextView tvStBirthday;
        AppCompatTextView tvStHomeTown;
        AppCompatTextView btnEdit;
        AppCompatTextView btnAddMarks;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            tvStName = itemView.findViewById(R.id.item_st_ed_student_name);
            tvStCode = itemView.findViewById(R.id.item_st_ed_student_code);
            tvStBirthday = itemView.findViewById(R.id.item_st_ed_student_birthday);
            tvStHomeTown = itemView.findViewById(R.id.item_st_ed_student_home_town);
            btnEdit = itemView.findViewById(R.id.item_st_btn_edit);
            btnAddMarks = itemView.findViewById(R.id.item_st_btn_add_marks);
        }
    }
}
