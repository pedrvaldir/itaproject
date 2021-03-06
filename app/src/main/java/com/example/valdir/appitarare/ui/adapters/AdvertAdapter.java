package com.example.valdir.appitarare.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.Advertisement;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by VALDIR on 13/07/2018.
 */
public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AnunViewHolder> {

    private static final String TAG = AdvertAdapter.class.getSimpleName();

    private ArrayList<Advertisement> mListAnunc;
    final private ListItemAnunClickListener mOnClickListener;
    private ProgressBar progressBar;
    private ImageView imageView;
    private int mPosition;

    public AdvertAdapter(ListItemAnunClickListener listner, ArrayList<Advertisement> listAnunc) {
        mOnClickListener = listner;
        mListAnunc = listAnunc;
    }

    public AdvertAdapter(ListItemAnunClickListener listner, ArrayList<Advertisement> listAnunc, String categ) {
        mOnClickListener = listner;
        mListAnunc = listAnunc;

    }

    public interface ListItemAnunClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public AnunViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item, parent, false);

        progressBar = view.findViewById(R.id.progressBar_list_item);


        AnunViewHolder anunViewHolder = new AnunViewHolder(view);

        return anunViewHolder;
    }

    @Override
    public void onBindViewHolder(AnunViewHolder holder, int position) {
        mPosition = position;
        holder.bind(mListAnunc.get(position));
    }

    @Override
    public int getItemCount() {

        return mListAnunc.size();
    }

    class AnunViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView descTextView;
        TextView phoneTextView;

        LinearLayout mLinearLayoutItemImg;


        public AnunViewHolder(View itemView) {
            super(itemView);

            mLinearLayoutItemImg = itemView.findViewById(R.id.linear_image_item);

            nameTextView = itemView.findViewById(R.id.tv_title_listitem);
            descTextView = itemView.findViewById(R.id.tv_descri_listitem);
            phoneTextView = itemView.findViewById(R.id.tv_phone_contact_categ);
            imageView = itemView.findViewById(R.id.iv_listitem);



            if (itemView != null) {
                progressBar = (ProgressBar)
                        itemView.findViewById(R.id.progressBar_list_item);
                progressBar.setVisibility(View.VISIBLE);
            }
            //veja sua imagem
            ImageView myImage = (ImageView)
                    itemView.findViewById(R.id.iv_listitem);

            itemView.setOnClickListener(this);
        }


            void bind(final Advertisement advertisement) {


            if (!advertisement.getImagem().equals("")){

                 Picasso.get()
                    .load(advertisement.getImagem())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(imageView, new ImageLoadedCallback(progressBar) {
                        @Override public void onSuccess() {
                            if (this.progressBar != null) {
                                this.progressBar.setVisibility(View.GONE); }
                        }
                    });

            }else {

                mLinearLayoutItemImg.setVisibility(View.INVISIBLE);
            }

            nameTextView.setText(advertisement.getTitulo());

            descTextView.setText(advertisement.getDescricao());

            phoneTextView.setText(advertisement.getTelContato());


        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        private class ImageLoadedCallback implements Callback {
            ProgressBar progressBar;

            public ImageLoadedCallback(ProgressBar progBar){
                progressBar = progBar;
            } @Override public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        }
    }

}


