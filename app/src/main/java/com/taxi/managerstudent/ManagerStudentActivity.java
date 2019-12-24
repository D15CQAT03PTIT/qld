package com.taxi.managerstudent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.taxi.managerstudent.models.StudentResponse;
import com.taxi.managerstudent.models.SubjectResponse;
import com.taxi.managerstudent.network.ServiceAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerStudentActivity extends AppCompatActivity implements DialogEditStudent.OnMyClickListener, DialogAddMarks.OnMyClickListener {
    AppCompatTextView btnSearch;
    AppCompatTextView btnAdd;
    AppCompatTextView btnClear;
    AppCompatEditText edStudentName;
    AppCompatEditText edStudentCode;
    AppCompatEditText edStudentBirthday;
    AppCompatEditText edStudentHometown;
    RecyclerView recyclerStudent;
    private List<SubjectResponse> subjectResponses;

    private SpotsDialog spotsDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_student);
        btnSearch = findViewById(R.id.manager_st_btn_search);
        btnAdd = findViewById(R.id.manager_st_btn_add);
        btnClear = findViewById(R.id.manager_st_btn_clear);
        edStudentName = findViewById(R.id.manager_st_ed_student_name);
        edStudentCode = findViewById(R.id.manager_st_ed_student_code);
        edStudentBirthday = findViewById(R.id.manager_st_ed_student_birthday);
        edStudentHometown = findViewById(R.id.manager_st_ed_student_home_town);
        recyclerStudent = findViewById(R.id.manager_st_recycler);
        recyclerStudent.setLayoutManager(new LinearLayoutManager(this));
        recyclerStudent.setNestedScrollingEnabled(false);

        spotsDialog = new SpotsDialog(this, R.style.Custom);
        getListStudent();
        getSubject();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stName = edStudentName.getText().toString().trim();
                String stCode = edStudentCode.getText().toString().trim();
                String stBirthday = edStudentBirthday.getText().toString().trim();
                String stHomeTown = edStudentHometown.getText().toString().trim();
                if (TextUtils.isEmpty(stName) || TextUtils.isEmpty(stCode) || TextUtils.isEmpty(stBirthday) || TextUtils.isEmpty(stHomeTown)) {
                    Toast.makeText(ManagerStudentActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                }
                addStudent(stHomeTown, stBirthday, stCode, stName);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edStudentName.setText("");
                edStudentCode.setText("");
                edStudentBirthday.setText("");
                edStudentHometown.setText("");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stName = edStudentName.getText().toString().trim();
                String stCode = edStudentCode.getText().toString().trim();
                String stBirthday = edStudentBirthday.getText().toString().trim();
                String stHomeTown = edStudentHometown.getText().toString().trim();
                if (TextUtils.isEmpty(stName) && TextUtils.isEmpty(stCode) && TextUtils.isEmpty(stBirthday) && TextUtils.isEmpty(stHomeTown)) {
                    Toast.makeText(ManagerStudentActivity.this, "Vui lòng nhập ít nhất một thông tin", Toast.LENGTH_LONG).show();
                    return;
                }
                searchStudent(stHomeTown, stBirthday, stCode, stName);
            }
        });
    }

    private void getListStudent() {
        spotsDialog.show();
        JSONObject jsonRequest = new JSONObject();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest.toString());
        ServiceAPI apiCalling = MSApplication.self().getRetrofit().create(ServiceAPI.class);
        Call<List<StudentResponse>> responseCall = apiCalling.getStudent(body);
        responseCall.enqueue(new Callback<List<StudentResponse>>() {
            @Override
            public void onResponse(Call<List<StudentResponse>> call, Response<List<StudentResponse>> response) {
                spotsDialog.hide();
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<StudentResponse>> call, Throwable t) {
                spotsDialog.hide();
            }
        });
    }

    private void getSubject() {
        JSONObject jsonRequest = new JSONObject();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest.toString());
        ServiceAPI apiCalling = MSApplication.self().getRetrofit().create(ServiceAPI.class);
        Call<List<SubjectResponse>> responseCall = apiCalling.getSubject(body);
        responseCall.enqueue(new Callback<List<SubjectResponse>>() {
            @Override
            public void onResponse(Call<List<SubjectResponse>> call, Response<List<SubjectResponse>> response) {
                subjectResponses = response.body();
            }

            @Override
            public void onFailure(Call<List<SubjectResponse>> call, Throwable t) {
            }
        });
    }

    private void searchStudent(String studentAdress, String studentBirhDay, String studentCode, String studentName) {
        spotsDialog.show();
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("studentAdress", studentAdress);
            jsonRequest.put("studentBirhDay", studentBirhDay);
            jsonRequest.put("studentCode", studentCode);
            jsonRequest.put("studentName", studentName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest.toString());
        ServiceAPI apiCalling = MSApplication.self().getRetrofit().create(ServiceAPI.class);
        Call<List<StudentResponse>> responseCall = apiCalling.getStudent(body);
        responseCall.enqueue(new Callback<List<StudentResponse>>() {
            @Override
            public void onResponse(Call<List<StudentResponse>> call, Response<List<StudentResponse>> response) {
                spotsDialog.hide();
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<StudentResponse>> call, Throwable t) {
                spotsDialog.hide();
            }
        });
    }

    private void addStudent(String studentAdress, String studentBirhDay, String studentCode, String studentName) {
        spotsDialog.show();
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("studentAdress", studentAdress);
            jsonRequest.put("studentBirhDay", studentBirhDay);
            jsonRequest.put("studentCode", studentCode);
            jsonRequest.put("studentName", studentName);
            jsonRequest.put("classCode", "CLASSCODE");
            jsonRequest.put("studentEmail", "EMAIL");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest.toString());
        ServiceAPI apiCalling = MSApplication.self().getRetrofit().create(ServiceAPI.class);
        Call<List<StudentResponse>> responseCall = apiCalling.addStudent(body);
        responseCall.enqueue(new Callback<List<StudentResponse>>() {
            @Override
            public void onResponse(Call<List<StudentResponse>> call, Response<List<StudentResponse>> response) {
                spotsDialog.hide();
                getListStudent();
            }

            @Override
            public void onFailure(Call<List<StudentResponse>> call, Throwable t) {
                Toast.makeText(ManagerStudentActivity.this, "Kiểm tra kết nối internet", Toast.LENGTH_LONG).show();
                spotsDialog.hide();
            }
        });
    }

    private void updateStudent(String studentAdress, String studentBirhDay, String studentCode, String studentName, int studentId) {
        spotsDialog.show();
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("studentAdress", studentAdress);
            jsonRequest.put("studentBirhDay", studentBirhDay);
            jsonRequest.put("studentCode", studentCode);
            jsonRequest.put("studentName", studentName);
            jsonRequest.put("classCode", "CLASSCODE");
            jsonRequest.put("studentEmail", "EMAIL");
            jsonRequest.put("studentId", studentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest.toString());
        ServiceAPI apiCalling = MSApplication.self().getRetrofit().create(ServiceAPI.class);
        Call<List<StudentResponse>> responseCall = apiCalling.updateStudent(body);
        responseCall.enqueue(new Callback<List<StudentResponse>>() {
            @Override
            public void onResponse(Call<List<StudentResponse>> call, Response<List<StudentResponse>> response) {
                spotsDialog.hide();
                getListStudent();
            }

            @Override
            public void onFailure(Call<List<StudentResponse>> call, Throwable t) {
                Toast.makeText(ManagerStudentActivity.this, "Kiểm tra kết nối internet", Toast.LENGTH_LONG).show();
                spotsDialog.hide();
            }
        });
    }

    private void addSubjectMarks(String cc, String btl, String ck, String subCode, String stCode) {
        spotsDialog.show();
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("markBtl", btl);
            jsonRequest.put("markCc", cc);
            jsonRequest.put("markCk", ck);
            jsonRequest.put("studentCode", stCode);
            jsonRequest.put("classCode", "CLASSCODE");
            jsonRequest.put("subjectCode", subCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest.toString());
        ServiceAPI apiCalling = MSApplication.self().getRetrofit().create(ServiceAPI.class);
        Call<RequestBody> responseCall = apiCalling.addSubject(body);
        responseCall.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                spotsDialog.hide();
                Toast.makeText(ManagerStudentActivity.this, "Thêm điểm thành công!", Toast.LENGTH_LONG).show();
                getListStudent();
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Toast.makeText(ManagerStudentActivity.this, "Kiểm tra kết nối internet", Toast.LENGTH_LONG).show();
                spotsDialog.hide();
            }
        });
    }


    private void setAdapter(List<StudentResponse> list) {
        StudentAdapter studentAdapter = new StudentAdapter(list, subjectResponses, this, this, this);
        recyclerStudent.setAdapter(studentAdapter);
    }

    @Override
    public void clickOk(StudentResponse studentResponse) {
        updateStudent(studentResponse.getStudentAdress(), studentResponse.getStudentBirhDay(), studentResponse.getStudentCode(), studentResponse.getStudentName(), studentResponse.getStudentId());
    }

    @Override
    public void clickOk(String cc, String btl, String ck, String subjectCode, String stCode) {
        addSubjectMarks(cc,btl,ck,subjectCode,stCode);
    }
}
