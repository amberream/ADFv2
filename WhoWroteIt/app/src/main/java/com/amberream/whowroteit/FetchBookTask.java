package com.amberream.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class FetchBookTask extends AsyncTask<String, Void, String> {

    /*
    use WeakReference objects for these text views
    (rather than actual TextView objects) to avoid leaking context
    from the Activity. The weak references prevent memory leaks
    by allowing the object held by that reference to be
    garbage-collected if necessary.
     */
    private WeakReference<TextView> textViewAuthor;
    private WeakReference<TextView> textViewTitle;

    public FetchBookTask(TextView textViewAuthor, TextView textViewTitle)
    {
        this.textViewAuthor = new WeakReference<TextView>(textViewAuthor);
        this.textViewTitle = new WeakReference<TextView>(textViewTitle);
    }


    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
