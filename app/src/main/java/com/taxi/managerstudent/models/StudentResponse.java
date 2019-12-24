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

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public void setStudentAdress(String studentAdress) {
        this.studentAdress = studentAdress;
    }

    public void setStudentBirhDay(String studentBirhDay) {
        this.studentBirhDay = studentBirhDay;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
