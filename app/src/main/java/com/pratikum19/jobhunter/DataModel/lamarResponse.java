package com.pratikum19.jobhunter.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pratikum19.jobhunter.DataModel.TalentResponse.DetailJob;

import java.util.List;

public class lamarResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
