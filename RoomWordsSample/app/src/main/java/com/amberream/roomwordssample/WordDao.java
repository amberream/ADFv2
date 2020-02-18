package com.amberream.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/*
The DAO must be an interface or abstract class.
Room uses the DAO to create a clean API for your code.
By default, all queries (@Query) must be executed on a thread other than the main thread.
For operations such as inserting or deleting, if you use the provided convenience annotations, Room takes care of thread management for you.
 */
@Dao
public interface WordDao {

    /*
    There are also @Delete and @Update annotations for deleting and updating a row
     */
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert (Word word);

    /*
    There are no conveniences methods for deleting all objects, so you have to create a query.
     */
    @Query("DELETE FROM word_table")
    public abstract void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    public abstract LiveData<List<Word>> getAllWords();

    // No need to return LiveData since this method will be called explicitly when needed
    // This exists just to see if the db is populated
    @Query("SELECT * FROM word_table LIMIT 1")
    public abstract Word [] getAnyWord();
}
