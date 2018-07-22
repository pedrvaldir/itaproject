package com.example.valdir.appitarare.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valdir.appitarare.R;

import java.util.ArrayList;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class CategAdapter extends RecyclerView.Adapter<CategAdapter.ViewHolder> {

    private static final String TAG = CategAdapter.class.getSimpleName();
    final private ListItemClickListener mOnClickListener;
    private ArrayList<String> mListCateg;

    public CategAdapter(ListItemClickListener listner, ArrayList categorias) {
        mOnClickListener = listner;
        mListCateg = categorias;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public CategAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item_categ, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mListCateg.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TvItemCategView;

        ViewHolder(View itemView) {
            super(itemView);

            TvItemCategView = itemView.findViewById(R.id.tv_item_categ);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {

            TvItemCategView.setText(mListCateg.get(listIndex));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
