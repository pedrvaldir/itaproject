package com.example.valdir.appitarare.ui.adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.Advertisement;

import java.util.ArrayList;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AnunViewHolder> {

    private static final String TAG = AdvertAdapter.class.getSimpleName();

    private ArrayList<Advertisement> mListAnunc;
    final private ListItemAnunClickListener mOnClickListener;
    private int positionList;

    public AdvertAdapter(ListItemAnunClickListener listner, ArrayList<Advertisement> listAnunc){
        mOnClickListener = listner;
        mListAnunc = listAnunc;
    }

    public interface ListItemAnunClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public AnunViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item , parent, false);

        AnunViewHolder anunViewHolder = new AnunViewHolder(view);

        return anunViewHolder;
    }

    @Override
    public void onBindViewHolder(AnunViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        positionList = position;
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mListAnunc.size();
    }

    class AnunViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView descTextView;
        TextView avaliadoTextView;


        public AnunViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.tv_title_listitem);
            descTextView = itemView.findViewById(R.id.tv_descri_listitem);
            avaliadoTextView = itemView.findViewById(R.id.tv_rating);

            itemView.setOnClickListener(this);
        }

        void bind(){
            nameTextView.setText(mListAnunc.get(positionList).getTitulo());
            descTextView.setText(mListAnunc.get(positionList).getDescricao());
            avaliadoTextView.setText(String.valueOf(mListAnunc.get(positionList).getAvaliado()));
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}