package com.example.acer.jsn;

import android.os.Parcel;
import android.os.Parcelable;

public class BookModel  implements Parcelable {
    String title,bookimage,description;

    public BookModel(String title, String bookimage, String description) {
        this.title = title;
        this.bookimage = bookimage;
        this.description = description;
    }

    protected BookModel(Parcel in) {
        title = in.readString();
        bookimage = in.readString();
        description = in.readString();
    }

    public static final Creator<BookModel> CREATOR = new Creator<BookModel>() {
        @Override
        public BookModel createFromParcel(Parcel in) {
            return new BookModel(in);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getBookimage() {
        return bookimage;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBookimage(String bookimage) {
        this.bookimage = bookimage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(bookimage);
        dest.writeString(description);
    }
}
