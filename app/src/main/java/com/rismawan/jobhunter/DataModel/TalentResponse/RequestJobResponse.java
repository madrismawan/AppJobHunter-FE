package com.rismawan.jobhunter.DataModel.TalentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestJobResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data_job")
    @Expose
    private DetailJob dataJob;

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

    public DetailJob getDetailJob() {
        return dataJob;
    }

    public void setDetailJob(DetailJob dataJob) {
        this.dataJob = dataJob;
    }
}
