package com.pratikum19.jobhunter.DataModel.TalentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailJob {
    @SerializedName("id_talent")
    @Expose
    private Integer idTalent;
    @SerializedName("id_job")
    @Expose
    private String idJob;
    @SerializedName("id_employer")
    @Expose
    private Integer idEmployer;
    @SerializedName("pengalaman")
    @Expose
    private String pengalaman;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("joblist")
    @Expose
    private DataJob joblist;

    public Integer getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public DataJob getJoblist() {
        return joblist;
    }

    public void setJoblist(DataJob joblist) {
        this.joblist = joblist;
    }

    public Integer getIdTalent() {
        return idTalent;
    }

    public void setIdTalent(Integer idTalent) {
        this.idTalent = idTalent;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getPengalaman() {
        return pengalaman;
    }

    public void setPengalaman(String pengalaman) {
        this.pengalaman = pengalaman;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
