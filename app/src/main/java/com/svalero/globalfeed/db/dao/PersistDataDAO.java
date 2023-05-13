package com.svalero.globalfeed.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.globalfeed.domain.PersistData;


@Dao
public interface PersistDataDAO {
    @Query("SELECT * FROM persistdata WHERE id=0")
    PersistData getPersistData();

    @Insert
    void insert(PersistData persistData);

    @Update
    void update(PersistData persistData);
}
