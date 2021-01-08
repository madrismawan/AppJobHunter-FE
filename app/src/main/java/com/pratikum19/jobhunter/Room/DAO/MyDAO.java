package com.pratikum19.jobhunter.Room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pratikum19.jobhunter.Room.Entity.EmployerJobList;

import java.util.List;

@Dao
public interface MyDAO {

    @Insert
    Long insertJobList(EmployerJobList employerJobList);

    @Query("SELECT * FROM EmployerJobList")
    List<EmployerJobList> getAllEmployerJobList();

    @Query("DELETE FROM EmployerJobList")
    void nukeTableEmployerJobList();

}
