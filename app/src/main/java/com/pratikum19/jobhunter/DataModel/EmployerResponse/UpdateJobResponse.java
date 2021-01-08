package com.pratikum19.jobhunter.DataModel.EmployerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateJobResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data_update")
    @Expose
    private JobList dataUpdate;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JobList getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(JobList dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

}


