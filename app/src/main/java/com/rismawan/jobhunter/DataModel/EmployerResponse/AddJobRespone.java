package com.rismawan.jobhunter.DataModel.EmployerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddJobRespone {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("job_list")
    @Expose
    private JobList jobList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JobList getJobList() {
        return jobList;
    }

    public void setJobList(JobList jobList) {
        this.jobList = jobList;
    }
}
