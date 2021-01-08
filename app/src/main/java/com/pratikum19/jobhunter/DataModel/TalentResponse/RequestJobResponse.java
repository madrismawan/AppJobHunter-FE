package com.pratikum19.jobhunter.DataModel.TalentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pratikum19.jobhunter.DataModel.EmployerResponse.ApplyJob.DetailDataJob;

import java.util.List;

public class RequestJobResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data_job")
    @Expose
    private List<DetailJob> dataJob;

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

    public List<DetailJob>  getDetailJob() {
        return dataJob;
    }

    public void setDetailJob(List<DetailJob>  dataJob) {
        this.dataJob = dataJob;
    }
}
