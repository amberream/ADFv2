package com.amberream.whowroteit;

import android.net.Network;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    public static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    public static final String QUERY_PARAM = "q";
    public static final String MAX_RESULTS = "maxResults";
    public static final String PRINT_TYPE = "printType";

    public static String getBookInfo(String query)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJson = null;

        try {
            Uri uri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, query)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books").build();
            URL url = new URL(uri.toString());

            // open the url connection and make the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while(line != null)
            {
                buffer.append(line + "\n");
                line = reader.readLine();
            }

            // if no content, go ahead and return
            if (buffer.length() == 0)
            {
                return null;
            }

            bookJson = buffer.toString();
        }
        catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                }
            }
        }
        if (bookJson != null) {
            // this could be null if we caught an exception above before it got set (i.e. search on space char)
            Log.d(LOG_TAG, bookJson);
        }
        return bookJson;
    }
}
