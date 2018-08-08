package com.example.valdir.appitarare.ui.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.Advertisement;
import com.example.valdir.appitarare.util.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by VALDIR on 13/07/2018.
 */
public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AnunViewHolder> {

    private static final String TAG = AdvertAdapter.class.getSimpleName();

    private ArrayList<Advertisement> mListAnunc;
    final private ListItemAnunClickListener mOnClickListener;
    private int positionList;
    private String mCateg;

    public AdvertAdapter(ListItemAnunClickListener listner, ArrayList<Advertisement> listAnunc) {
        mOnClickListener = listner;
        mListAnunc = listAnunc;
    }

    public AdvertAdapter(ListItemAnunClickListener listner, ArrayList<Advertisement> listAnunc, String categ) {
        mOnClickListener = listner;
        mListAnunc = listAnunc;
        mCateg = categ;
    }

    public interface ListItemAnunClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public AnunViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item, parent, false);

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
        ImageView imageView;


        public AnunViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.tv_title_listitem);
            descTextView = itemView.findViewById(R.id.tv_descri_listitem);
            avaliadoTextView = itemView.findViewById(R.id.tv_rating);
            imageView = itemView.findViewById(R.id.iv_listitem);

            itemView.setOnClickListener(this);
        }

        void bind() {

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReferenceFromUrl(Constants.URL_IMAGE_STORAGE_FIREBASE).child(mListAnunc.get(positionList).getImagem());
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    imageView = itemView.findViewById(R.id.iv_listitem);

                    Picasso.get().load(uri.toString()).into(imageView);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

            nameTextView.setText(mListAnunc.get(positionList).getTitulo());

            Picasso.get().load(storageReference.toString()).into(imageView);
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
