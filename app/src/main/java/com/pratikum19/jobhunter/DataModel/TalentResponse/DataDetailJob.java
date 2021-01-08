package com.pratikum19.jobhunter.DataModel.TalentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDetailJob {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_employer")
    @Expose
    private Integer idEmployer;
    @SerializedName("nama_instansi")
    @Expose
    private String namaInstansi;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("bagian")
    @Expose
    private String bagian;
    @SerializedName("syarat")
    @Expose
    private String syarat;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("user")
    @Expose
    private User user;

//    public DataDetailJob(Integer id, Integer idEmployer, String namaInstansi, String alamat, String bagian, String syarat, String created_at, String updated_at,String deleted_at, User user) {
//        this.id = id;
//        this.idEmployer = idEmployer;
//        this.namaInstansi = namaInstansi;
//        this.alamat = alamat;
//        this.bagian = bagian;
//        this.syarat = syarat;
//        this.createdAt = created_at;
//        this.updatedAt = updated_at;
//        this.deletedAt = deleted_at;
//        this.user = user;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getSyarat() {
        return syarat;
    }

    public void setSyarat(String syarat) {
        this.syarat = syarat;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
