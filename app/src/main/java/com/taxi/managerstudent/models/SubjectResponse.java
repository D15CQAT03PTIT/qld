package com.taxi.managerstudent.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubjectResponse implements Serializable {
    @SerializedName("subjectId")
    private int subjectId;
    @SerializedName("subjectCode")
    private String subjectCode;
    @SerializedName("subjectName")
    private String subjectName;
    @SerializedName("numberSession")
    private String numberSession;

    public int getSubjectId() {
        return subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getNumberSession() {
        return numberSession;
    }
}
