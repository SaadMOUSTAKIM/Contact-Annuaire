package com.example.moustakim_annauire_pro;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserContactDAO {

    @Insert(onConflict =REPLACE)
    void insert(UserContact userContact);

    @Query("SELECT * FROM UserContact")
    List<UserContact> getAll();

    @Update
    void update(UserContact userContact);

    @Delete
    void delete(UserContact userContact);

    @Query("SELECT * FROM UserContact where lastName LIKE '%' || :motCle || '%' ")
    List<UserContact> getAllByname(String motCle);
}
