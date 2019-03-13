package com.example.acer.jsn;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearLayoutManager.SavedState;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText searchBook;
    Button getBookData;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    String datas = null;

    String bookurl="https://www.googleapis.com/books/v1/volumes?q=";
    ArrayList<BookModel> arrayList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            arrayList=savedInstanceState.getParcelableArrayList("key");
        }
        searchBook=findViewById(R.id.bookname);
        getBookData=findViewById(R.id.getData);
        //datas=searchBook.getText().toString();
        arrayList=new ArrayList<>();
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BooksAdapter(this,arrayList));


    }

    public void btnclick(View view) {
       new  BookTask().execute();
    }

    class BookTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url= new URL(bookurl+searchBook.getText().toString());
                Log.i("bookurl",url.toString());
                HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream=urlConnection.getInputStream();
                Scanner scanner=new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext())
                {
                    return  scanner.next();

                }
                else
                {
                    return null;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s!=null){
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    Log.i("bookurl", String.valueOf(jsonObject));
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject volume = jsonArray.getJSONObject(i);
                        Log.i("bookurl", String.valueOf(volume));
                        JSONObject volumeInfo = volume.getJSONObject("volumeInfo");
                        String bookTitle = volumeInfo.getString("title");
                        Log.i("bookurl", bookTitle);
                        String bookDescription = volumeInfo.getString("description");
                        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                        String bookImage = imageLinks.getString("thumbnail");
                        Log.i("bookimageurl", bookImage);
                        arrayList.add(new BookModel(bookTitle, bookImage, bookDescription));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }

    }

}

