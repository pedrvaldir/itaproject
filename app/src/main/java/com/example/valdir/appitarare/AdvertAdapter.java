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
    final private ListItemAnunClickListener mOnClickListener;
    private int viewHolderCount;
    private int positionList;

    public AdvertAdapter(ListItemAnunClickListener listner, ArrayList<Advertisement> listAnunc){
        mOnClickListener = listner;
        mListAnunc = listAnunc;
        viewHolderCount = 0;
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


        viewHolderCount++;

        return anunViewHolder;
    }

    @Override
    public void onBindViewHolder(AnunViewHolder holder, int position) {

        //final long id = mCursor.getLong(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada._ID));

        Log.d(TAG, "#" + position);
        positionList = position;
        holder.bind();

    //    holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mListAnunc.size();
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
            nameTextView.setText(mListAnunc.get(positionList).getmTitulo());
            descTextView.setText(mListAnunc.get(positionList).getmDescricao());
            avaliadoTextView.setText(Integer.toString(mListAnunc.get(positionList).getmAvaliado()));
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
