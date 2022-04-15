package com.example.moustakim_annauire_pro;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add database entities
@Database(entities={UserContact.class},version=1,exportSchema = false)

public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;

        private static String DATABASE_NAME="Contacts";

    public synchronized static RoomDB getInstance(Context context)
    {
        // check condition
        if(database==null)
        {
             // when database is null
            // Initialize database
            database= Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME)
            .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        // Return database
        return database;
    }

    //Create DAO
    public abstract UserContactDAO userContactDao();
}
