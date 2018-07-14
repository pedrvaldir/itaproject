package com.example.valdir.appitarare;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valdir.appitarare.data.AdvertiseContract;

import java.util.ArrayList;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AnunViewHolder> {

    private static final String TAG = AdvertAdapter.class.getSimpleName();

    private ArrayList<Advertisement> listAnunc;
    final private ListItemAnunClickListener mOnClickListener;
    private Cursor mCursor;


    public AdvertAdapter(ListItemAnunClickListener listner, Cursor cursor) {
        mOnClickListener = listner;
        mCursor = cursor;
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

        if (!mCursor.moveToPosition(position))
            return ;

        //final long id = mCursor.getLong(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada._ID));

        Log.d(TAG, "#" + position);
        holder.bind();

    //    holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }




    class AnunViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView nameTextView;
        TextView descTextView;
        ImageView imageView;
        TextView avaliadoTextView;


        public AnunViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.tv_title_listitem);
            descTextView = (TextView) itemView.findViewById(R.id.tv_descri_listitem);
            imageView = itemView.findViewById(R.id.iv_listitem);
            avaliadoTextView = itemView.findViewById(R.id.tv_rating);

            itemView.setOnClickListener(this);
        }

        void bind(){
            nameTextView.setText(mCursor.getString(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada.COLUNA_TITULO)));
            descTextView.setText(mCursor.getString(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada.COLUNA_DESCRICAO)));
            imageView.setImageResource(mCursor.getInt(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada.COLUNA_IMG)));
            avaliadoTextView.setText("Avaliação:  "+ String.valueOf(mCursor.getInt(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada.COLUNA_AVALIADO))));
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
