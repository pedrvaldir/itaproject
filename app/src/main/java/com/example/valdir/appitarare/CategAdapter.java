package com.example.valdir.appitarare;

import android.content.Context;
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

public class CategAdapter extends RecyclerView.Adapter<CategAdapter.ViewHolder>{


    private static final String TAG = CategAdapter.class.getSimpleName();


    private static int viewHolderCount;
    final private ListItemClickListener mOnClickListener;
    private int mNumberItems;
    private ArrayList<String> listCateg;

    public CategAdapter(ListItemClickListener listner, ArrayList categorias) {
        mNumberItems = categorias.size();
        mOnClickListener = listner;
        viewHolderCount = 0;
        listCateg = categorias;

    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public CategAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_categ;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolderCount++;

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView TvItemCategView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         *                 {@link CategAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public ViewHolder(View itemView) {
            super(itemView);

            TvItemCategView = (TextView) itemView.findViewById(R.id.tx_item_categ);
            //listItemNumberView
            // COMPLETED (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
            itemView.setOnClickListener(this);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex) {
            Log.v("asdfas", listCateg.get(0));
            TvItemCategView.setText(listCateg.get(listIndex).toString());
            Log.v("asdfas", TvItemCategView.toString());
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
