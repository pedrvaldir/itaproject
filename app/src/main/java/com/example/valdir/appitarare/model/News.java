package com.example.valdir.appitarare.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    private String title;
    private String description;
    private String datePublication;
    private String sourceAuthor;
    private String image;

    public News( ) {
    }


    public News(String title, String description, String datePublication, String sourceAuthor, String image) {
        this.title = title;
        this.description = description;
        this.datePublication = datePublication;
        this.sourceAuthor = sourceAuthor;
        this.image = image;
    }

    protected News(Parcel in) {
        title = in.readString();
        description = in.readString();
        datePublication = in.readString();
        sourceAuthor = in.readString();
        image = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public String getSourceAuthor() {
        return sourceAuthor;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(datePublication);
        dest.writeString(sourceAuthor);
        dest.writeString(image);
    }
}
