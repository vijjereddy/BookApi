package com.example.acer.jsn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookDetails extends AppCompatActivity {
    ImageView iv;
    TextView tvtitle,tvdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        iv=findViewById(R.id.image);
        tvtitle=findViewById(R.id.text1);
        tvdescription=findViewById(R.id.text2);
        String[] s=getIntent().getStringArrayExtra("data");
        tvtitle.setText(s[0]);
        Picasso.with(this).load(s[1]).into(iv);
        tvdescription.setText(s[2]);
    }
}
