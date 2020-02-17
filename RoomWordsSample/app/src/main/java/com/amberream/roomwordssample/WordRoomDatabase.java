package com.amberream.roomwordssample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
Listing the entities class or classes creates corresponding tables in the database.
Set the version number.
exportSchema keeps a history of schema versions which is needed only if you are migrating the database.
 */
@Database(version = 1, entities = {Word.class}, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    /*
    The database needs to be a singleton so we only have one connection at a time
     */
    private static WordRoomDatabase mInstance;

    // This actually caused a build error!!
//    private WordRoomDatabase(){}

    public static WordRoomDatabase getInstance(final Context context)
    {
        if (mInstance == null)
        {
            synchronized (WordRoomDatabase.class)
            {
                if (mInstance == null)
                {
                    /*
                    However, if you modify the database schema,
                    you need to update the version number and define how to handle migrations.
                    For a sample app such as the one you're creating,
                    destroying and re-creating the database is a fine migration strategy.
                    For a real app, you must implement a non-destructive migration strategy.
                     */
                    // create the db
                    mInstance = Room.databaseBuilder(context.getApplicationContext(),WordRoomDatabase.class, "word_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return mInstance;
    }


    /*
    Provide an abstract getter for each Dao
     */
    public abstract WordDao wordDao();
}
