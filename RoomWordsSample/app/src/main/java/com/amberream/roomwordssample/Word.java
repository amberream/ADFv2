package com.amberream.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// if you dont specify the name it will match the name of the class
@Entity (tableName = "word_table")
public class Word {

    // To auto-generate a unique key for each entity, you would add and annotate a primary integer key with autoGenerate=true
    @PrimaryKey
    @NonNull
    // if you don't specify the name it will match the name of the field
    @ColumnInfo (name = "word")
    private String mWord;

    public Word(@NonNull String word)
    {
        mWord = word;
    }

    public String getWord()
    {
        return mWord;
    }


}
