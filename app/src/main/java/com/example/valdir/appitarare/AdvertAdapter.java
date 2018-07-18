package com.example.valdir.appitarare;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AnunViewHolder> {

    private static final String TAG = AdvertAdapter.class.getSimpleName();

    private ArrayList<Advertisement> mListAnunc;
    private Context context;
    final private ListItemAnunClickListener mOnClickListener;

    public AdvertAdapter(ListItemAnunClickListener listner, ArrayList<Advertisement> listAnunc){
        mOnClickListener = listner;
        mListAnunc = listAnunc;
    }

    public interface ListItemAnunClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public AnunViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        AnunViewHolder anunViewHolder = new AnunViewHolder(view);

        return anunViewHolder;
    }

    @Override
    public void onBindViewHolder(AnunViewHolder holder, int position) {

        Log.d(TAG, "#" + position);
        holder.bind();

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class AnunViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView nameTextView;
        TextView descTextView;
        TextView avaliadoTextView;


        public AnunViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.tv_title_listitem);
            descTextView = (TextView) itemView.findViewById(R.id.tv_descri_listitem);
            avaliadoTextView = itemView.findViewById(R.id.tv_rating);

            itemView.setOnClickListener(this);
        }

        void bind(){
            nameTextView.setText("TITULO");
            descTextView.setText("DESCRICAO");
            avaliadoTextView.setText("AVALIDADO");
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
