package com.amberream.whowroteit;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        if (s == null)
        {
            textViewTitle.get().setText(R.string.no_results);
            textViewAuthor.get().setText("");
            return;
        }

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            // return the first item that contains both a title and an author
            String title = null;
            String authors = null;
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject item = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                }
                catch(JSONException e)
                {
                    continue;
                }
                // if we get here, both title and authors exist
                break;
            }
            if (title != null && authors != null)
            {
                textViewTitle.get().setText(title);
                textViewAuthor.get().setText(authors);
            }
            else
            {
                textViewTitle.get().setText(R.string.no_results);
                textViewAuthor.get().setText("");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            textViewTitle.get().setText(R.string.no_results);
            textViewAuthor.get().setText("");
        }

    }
}
