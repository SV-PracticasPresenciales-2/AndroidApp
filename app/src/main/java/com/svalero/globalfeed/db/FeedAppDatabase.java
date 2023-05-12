package com.svalero.globalfeed.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.globalfeed.db.dao.PersistDataDAO;
import com.svalero.globalfeed.domain.PersistData;


@Database(entities = {PersistData.class}, version = 1)
public abstract class FeedAppDatabase extends RoomDatabase {
    public abstract PersistDataDAO getPersistDataDAO();
}
