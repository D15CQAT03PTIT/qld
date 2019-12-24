package com.taxi.managerstudent.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentResponse implements Serializable {
    @SerializedName("studentId")
    private int studentId;
    @SerializedName("studentCode")
    private String studentCode;
    @SerializedName("studentName")
    private String studentName;
    @SerializedName("classCode")
    private String classCode;
    @SerializedName("studentAdress")
    private String studentAdress;
    @SerializedName("studentBirhDay")
    private String studentBirhDay;
    @SerializedName("studentEmail")
    private String studentEmail;

    public int getStudentId() {
        return studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getClassCode() {
        return classCode;
    }

    public String getStudentAdress() {
        return studentAdress;
    }

    public String getStudentBirhDay() {
        return studentBirhDay;
    }

    public String getStudentEmail() {
        return studentEmail;
    }
}
