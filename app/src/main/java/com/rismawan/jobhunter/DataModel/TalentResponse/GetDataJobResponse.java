package com.rismawan.jobhunter.DataModel.TalentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDataJobResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data_job")
    @Expose
    private List<DataJob> dataJob = null;

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

    public List<DataJob> getDataJob() {
        return dataJob;
    }

    public void setDataJob(List<DataJob> dataJob) {
        this.dataJob = dataJob;
    }

}
