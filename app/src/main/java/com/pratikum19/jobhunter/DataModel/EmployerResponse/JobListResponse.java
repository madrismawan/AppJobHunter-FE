package com.pratikum19.jobhunter.DataModel.EmployerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobListResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("job_list")
    @Expose
    private List<JobList> jobList = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JobList> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobList> jobList) {
        this.jobList = jobList;
    }


}
