package com.example.android.sunshine.activities;

import android.os.StrictMode;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sunshine.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_1 = "key1";
    public static final String EXTRA_KEY_2 = "key2";
    public static final String EXTRA_KEY_3 = "key3";
    public static final String base_url = "http://image.tmdb.org/t/p/w185";

    TextView description;
    TextView original;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String origin_title = "";
        String desc = "";
        String post = "";
        if (getIntent().getExtras()!=null){
             desc = getIntent().getExtras().getString(EXTRA_KEY_1);
        }

        description = (TextView)findViewById(R.id.desc);
        description.setText(desc);
 // for orign title
        if (getIntent().getExtras()!=null){
            origin_title = getIntent().getExtras().getString(EXTRA_KEY_2);
        }

        original = (TextView)findViewById(R.id.origin_title);
        original.setText(origin_title);


        //for image
        if (getIntent().getExtras()!=null){
            post = getIntent().getExtras().getString(EXTRA_KEY_3);
            Log.d("G",post);
        }

        poster = (ImageView)findViewById(R.id.poster_image_view);
        Picasso.with(getApplicationContext()).load(base_url+post).into(poster);

    }
}
