package com.pratikum19.jobhunter.DataModel.EmployerResponse.ApplyJob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailDataJob {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_talent")
    @Expose
    private Integer idTalent;
    @SerializedName("id_job")
    @Expose
    private Integer idJob;
    @SerializedName("id_employer")
    @Expose
    private Integer idEmployer;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("pengalaman")
    @Expose
    private String pengalaman;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("joblist")
    @Expose
    private DataJobList joblist;
    @SerializedName("user")
    @Expose
    private UserDataJob user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTalent() {
        return idTalent;
    }

    public void setIdTalent(Integer idTalent) {
        this.idTalent = idTalent;
    }

    public Integer getIdJob() {
        return idJob;
    }

    public void setIdJob(Integer idJob) {
        this.idJob = idJob;
    }

    public Integer getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getPengalaman() {
        return pengalaman;
    }

    public void setPengalaman(String pengalaman) {
        this.pengalaman = pengalaman;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public DataJobList getDataJoblist() {
        return joblist;
    }

    public void setDataJoblist(DataJobList joblist) {
        this.joblist = joblist;
    }

    public UserDataJob getUserDataJob() {
        return user;
    }

    public void setUserDataJob(UserDataJob user) {
        this.user = user;
    }
}
