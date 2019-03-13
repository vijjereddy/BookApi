package com.example.acer.jsn;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {

    Context context;
    ArrayList<BookModel> books=null;

    public BooksAdapter(MainActivity mainActivity, ArrayList<BookModel> arrayList) {
        this.context = mainActivity;
        this.books = arrayList;
    }

    @Override
    public BooksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rowdesign, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BooksAdapter.MyViewHolder holder, final int position) {
        BookModel model = books.get(position);

        holder.bookTitle.setText(model.title);
        Picasso.with(context).load(model.bookimage).into(holder.bookImage);
        holder.bookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] book=new String[3];
                book[0]=books.get(position).getTitle();
                book[1]=books.get(position).getBookimage();
                book[2]=books.get(position).getDescription();
                Intent i=new Intent(context,BookDetails.class);
                i.putExtra("data",book);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (books == null) ? 0 : books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookimage);
            bookTitle = itemView.findViewById(R.id.booktitle);

        }
    }
}