package com.amberream.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/*
A Repository is a class that abstracts access to multiple data sources.
The Repository is not part of the Architecture Components libraries,
but is a suggested best practice for code separation and architecture.
A Repository class handles data operations.
It provides a clean API to the rest of the app for app data.

A Repository manages query threads and allows you to use multiple backends.
In the most common example, the Repository implements the logic for deciding
whether to fetch data from a network or use results cached in the local database.

Room doesn't support database access on the main thread unless you've called
allowMainThreadQueries() on the builder because it might lock the UI
for a long period of time.
Asynchronous queries—queries that return instances of LiveData or Flowable—are
exempt from this rule because they asynchronously run the query on a background
thread when needed.

This class currently doesn't do much.  It's a wrapper, but it handles the threads.
Also, if there were more data sources it would handle making it into one access point.
 */
public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application)
    {
        WordRoomDatabase db = WordRoomDatabase.getInstance(application);
        mWordDao = db.wordDao();
        // this returns a LiveData object so the thread is automatically managed
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords()
    {
        return mAllWords;
    }

    public void deleteAllWords()
    {
        new DeleteAllAsyncTask(mWordDao).execute();
    }

    public void insert(Word word)
    {
        // This has to be done on a separate thread or it will crash
        new InsertAsyncTask(mWordDao).execute(word);
    }

    public void delete(Word word)
    {
        new DeleteAsyncTask(mWordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void>
    {
        WordDao mAsyncTaskWordDao;

        public InsertAsyncTask(WordDao wordDao)
        {
            mAsyncTaskWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskWordDao.insert(words[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>
    {
        WordDao mAsyncTaskWordDao;

        public DeleteAllAsyncTask(WordDao wordDao)
        {
            mAsyncTaskWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskWordDao.deleteAll();
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Word, Void, Void>
    {
        WordDao mAsyncTaskWordDao;

        public DeleteAsyncTask(WordDao wordDao)
        {
            mAsyncTaskWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskWordDao.deleteWord(words[0]);
            return null;
        }
    }
}
