package com.rismawan.jobhunter.DataModel.TalentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailJob {
    @SerializedName("id_talent")
    @Expose
    private Integer idTalent;
    @SerializedName("id_job")
    @Expose
    private String idJob;
    @SerializedName("pengalaman")
    @Expose
    private String pengalaman;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
