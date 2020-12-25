package com.rismawan.jobhunter.DataModel.EmployerResponse.KonfirmasiJob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rismawan.jobhunter.DataModel.EmployerResponse.ApplyJob.DetailDataJob;

import java.util.List;

public class KonfirmasiResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("detail_data")
    @Expose
    private List<DetailDataJob> detailData = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DetailDataJob> getDetailData() {
        return detailData;
    }

    public void setDetailData(List<DetailDataJob> detailData) {
        this.detailData = detailData;
    }
}
