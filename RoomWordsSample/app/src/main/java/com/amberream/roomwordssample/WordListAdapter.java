package com.amberream.roomwordssample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private LayoutInflater mLayoutInflator;
    private List<Word> mWords;

    public WordListAdapter(Context context)
    {
        mLayoutInflator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflator.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null)
        {
            holder.mTextView.setText(mWords.get(position).getWord());
        }
        else
        {
            holder.mTextView.setText("No Word");
        }
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
        {
            return mWords.size();
        }
        return 0;
    }

    public void setWords(List<Word> words)
    {
        mWords = words;
        /*
         Notify any registered observers that the data set has changed.
         LayoutManagers will be forced to fully rebind and relayout all visible views.
         */
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mTextView;
        /*
        The item view that's passed in corresponds to the layout that we created (R.layout.recyclerview_item).
        We can pull out the individual components and store them as fields - similar to Activity.onCreate.
         */
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
