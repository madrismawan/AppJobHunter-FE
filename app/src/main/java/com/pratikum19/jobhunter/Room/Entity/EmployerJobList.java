package com.pratikum19.jobhunter.Room.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EmployerJobList {
    @PrimaryKey(autoGenerate = true)
    public int id_employer;
    public int id;
    public String nama_instansi;
    public String alamat;
    public String bagian;
    public String syarat;
    public String created_at;
    public String updated_at;

    public int getId_employer() {
        return id_employer;
    }

    public void setId_employer(int id_employer) {
        this.id_employer = id_employer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_instansi() {
        return nama_instansi;
    }

    public void setNama_instansi(String nama_instansi) {
        this.nama_instansi = nama_instansi;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
