package com.example.valdir.appitarare.ui.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.News;
import com.google.android.gms.common.images.ImageRequest;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private ArrayList<News> mListNews;
    final private ListItemNewsClickListener mOnClickListener;
    private ProgressBar progressBar;
    private int mPosition;

    public NewsAdapter(ListItemNewsClickListener listner,    ArrayList<News> listNews) {
        mOnClickListener = listner;
        mListNews = listNews;
    }

    public interface ListItemNewsClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item_news2, parent, false);

         progressBar = view.findViewById(R.id.progressBar_list_item);

        NewsViewHolder newsViewHolder = new NewsViewHolder(view);

        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        mPosition = position;
        holder.bind(mListNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mListNews.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTitleNew;
        TextView mDescription;
        TextView mSourceAuthor;
        ImageView mImageView;
        LinearLayout mLinearLayoutImage;


        public NewsViewHolder(View itemView) {
            super(itemView);
            mTitleNew = (TextView) itemView.findViewById(R.id.tv_title_listitem_news);
            mLinearLayoutImage = itemView.findViewById(R.id.linear_image_item_news);
            mDescription = (TextView) itemView.findViewById(R.id.tv_descri_listitem_news);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_listitem_news);
            mSourceAuthor = (TextView) itemView.findViewById(R.id.tv_listitem__source);

            if (itemView != null) {
                progressBar = (ProgressBar)
                        itemView.findViewById(R.id.progressBar_list_item);
                progressBar.setVisibility(View.VISIBLE);
            }

            itemView.setOnClickListener(this);

        }

        void bind(final News news) {

            if (!news.getImage().equals("")){

                Picasso.get()
                        .load(news.getImage())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(mImageView, new ImageLoadedCallback(progressBar) {
                            @Override public void onSuccess() {
                                if (this.progressBar != null) {
                                    this.progressBar.setVisibility(View.GONE); }
                            }
                        });

            }else {

                mLinearLayoutImage.setVisibility(View.GONE);
            }


            mTitleNew.setText(news.getTitle());
            mDescription.setText(news.getDescription());
            mSourceAuthor.setText(news.getSourceAuthor());
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

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);


        }
    }


}
