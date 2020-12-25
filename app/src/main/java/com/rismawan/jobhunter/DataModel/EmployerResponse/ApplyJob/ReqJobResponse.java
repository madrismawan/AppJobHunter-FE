package com.rismawan.jobhunter.DataModel.EmployerResponse.ApplyJob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReqJobResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data_job")
    @Expose
    private List<DetailDataJob> dataJobs = null;

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

    public List<DetailDataJob> getDetailDataJob() {
        return dataJobs;
    }

    public void setDetailDataJob(List<DetailDataJob> detailDataJobs) {
        this.dataJobs = detailDataJobs;
    }
}
