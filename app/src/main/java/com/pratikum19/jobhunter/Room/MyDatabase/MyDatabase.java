package com.pratikum19.jobhunter.Room.MyDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.pratikum19.jobhunter.Room.DAO.MyDAO;

import com.pratikum19.jobhunter.Room.Entity.EmployerJobList;

@Database(entities = {EmployerJobList.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDAO myDao();

    private static MyDatabase INSTANCE;

    public static MyDatabase createDatabase(Context context){
        synchronized(MyDatabase.class){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,MyDatabase.class,"db_class")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}
