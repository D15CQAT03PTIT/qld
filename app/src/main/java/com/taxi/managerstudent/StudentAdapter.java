package com.taxi.managerstudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.taxi.managerstudent.models.StudentResponse;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<StudentResponse> list;
    private Context context;

    public StudentAdapter(List<StudentResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(context).inflate(R.layout.item_student,null);
        return new StudentHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StudentHolder studentHolder= (StudentHolder) holder;
        StudentResponse studentResponse=list.get(holder.getAdapterPosition());
        studentHolder.tvStName.setText(studentResponse.getStudentName());
        studentHolder.tvStCode.setText(studentResponse.getStudentCode());
        studentHolder.tvStBirthday.setText(studentResponse.getStudentBirhDay());
        studentHolder.tvStHomeTown.setText(studentResponse.getStudentAdress());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class StudentHolder extends RecyclerView.ViewHolder{
        AppCompatTextView tvStName;
        AppCompatTextView tvStCode;
        AppCompatTextView tvStBirthday;
        AppCompatTextView tvStHomeTown;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            tvStName=itemView.findViewById(R.id.item_st_ed_student_name);
            tvStCode=itemView.findViewById(R.id.item_st_ed_student_code);
            tvStBirthday=itemView.findViewById(R.id.item_st_ed_student_birthday);
            tvStHomeTown=itemView.findViewById(R.id.item_st_ed_student_home_town);
        }
    }
}
