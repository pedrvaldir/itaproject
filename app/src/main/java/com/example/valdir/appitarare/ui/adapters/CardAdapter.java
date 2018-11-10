package com.example.valdir.appitarare.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.News;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardAdapter extends  RecyclerView.Adapter<CardAdapter.NewsCardViewHolder> {

private ArrayList<News> mListNews;

public CardAdapter(  ArrayList<News> listNews) {
        mListNews = listNews;
        }

    @NonNull
    @Override
    public NewsCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item_news_card, parent, false);

        NewsCardViewHolder newsCardViewHolder = new NewsCardViewHolder(view);

        return newsCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCardViewHolder holder, int position) {
        holder.bind(mListNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mListNews.size();
    }

class NewsCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView mTitleNew;
    ImageView mImageView;
    LinearLayout mLinearLayoutImage;


    public NewsCardViewHolder(View itemView) {
        super(itemView);
        mTitleNew = (TextView) itemView.findViewById(R.id.tv_title_listitem_news);
        mLinearLayoutImage = itemView.findViewById(R.id.linear_image_item_news);
        mImageView = (ImageView) itemView.findViewById(R.id.iv_listitem_news);

        itemView.setOnClickListener(this);

    }

    void bind(final News news) {

        if (!news.getImage().equals("")){

            Picasso.get()
                    .load(news.getImage())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(mImageView);
        }else {

            mLinearLayoutImage.setVisibility(View.GONE);
        }

        mTitleNew.setText(news.getTitle());
    }

    @Override
    public void onClick(View v) {

        int clickedPosition = getAdapterPosition();


    }
}


}
