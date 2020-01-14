package com.eltiempo.mobiletest;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eltiempo.mobiletest.model.Apollo11;
import com.eltiempo.mobiletest.model.Item;
import com.eltiempo.mobiletest.util.Vars;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class VisorActivity extends AppCompatActivity {

    private ImageView spacecraftImageView;
    private TextView nameTextView;
    private ImageView favoriteOffImageView;
    private ImageView favoriteOnImageView;
    private TextView locationTitleTextView;
    private TextView locationTextView;
    private TextView keywordsTitleTextView;
    private TextView keywordsTextView;
    private TextView photographerTitleTextView;
    private TextView photographerTextView;
    private TextView dateCreatedTitleTextView;
    private TextView dateCreatedTextView;
    private TextView descriptionTitleTextView;
    private TextView descriptionTextView;

    private Apollo11 apollo11;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");


//        String spacecraftStr;
//        String nameStr;
//        final boolean favoriteBoo;
//        String locationStr;
//        String keywordsStr;
//        String photographerStr;
//        String dateCreatedStr;
//        String descriptionStr;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                position = 0;
//                spacecraftStr = null;
//                nameStr = null;
//                favoriteBoo = false;
//                locationStr = null;
//                keywordsStr = null;
//                photographerStr = null;
//                dateCreatedStr = null;
//                descriptionStr = null;
            } else {
                position = extras.getInt("position");
//                spacecraftStr = extras.getString("spacecraft");
//                nameStr = extras.getString("name");
//                favoriteBoo = extras.getBoolean("favorite");
//                locationStr = extras.getString("location");
//                keywordsStr = extras.getString("keywords");
//                photographerStr = extras.getString("photographer");
//                dateCreatedStr = extras.getString("dateCreated");
//                descriptionStr = extras.getString("description");
            }
        } else {
            position = (int) savedInstanceState.getSerializable("position");
//            spacecraftStr = (String) savedInstanceState.getSerializable("spacecraft");
//            nameStr = (String) savedInstanceState.getSerializable("name");
//            favoriteBoo = (boolean) savedInstanceState.getSerializable("favorite");
//            locationStr = (String) savedInstanceState.getSerializable("location");
//            keywordsStr = (String) savedInstanceState.getSerializable("keywords");
//            photographerStr = (String) savedInstanceState.getSerializable("photographer");
//            dateCreatedStr = (String) savedInstanceState.getSerializable("dateCreated");
//            descriptionStr = (String) savedInstanceState.getSerializable("description");
        }

        spacecraftImageView = findViewById(R.id.spacecraftImageView);
        nameTextView = findViewById(R.id.nameTextView);
        favoriteOffImageView = findViewById(R.id.favoriteOffImageView);
        favoriteOnImageView = findViewById(R.id.favoriteOnImageView);

        locationTitleTextView = findViewById(R.id.locationTitleTextView);
        locationTextView = findViewById(R.id.locationTextView);
        keywordsTitleTextView = findViewById(R.id.keywordsTitleTextView);
        keywordsTextView = findViewById(R.id.keywordsTextView);
        photographerTitleTextView = findViewById(R.id.photographerTitleTextView);
        photographerTextView = findViewById(R.id.photographerTextView);
        dateCreatedTitleTextView = findViewById(R.id.dateCreatedTitleTextView);
        dateCreatedTextView = findViewById(R.id.dateCreatedTextView);
        descriptionTitleTextView = findViewById(R.id.descriptionTitleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);


        String apollo11Str = Vars.getSharedPreferences(this, "apollo11");
        Gson gson = new Gson();
        apollo11 = gson.fromJson(apollo11Str, Apollo11.class);

        Item item = apollo11.getCollection().getItems().get(position);

        if (item.getLinks() != null && item.getLinks().get(0).getHref() != null && item.getLinks().get(0).getHref().length() > 0) {
            Picasso.get().load(item.getLinks().get(0).getHref()).placeholder(R.drawable.placeholder).into(spacecraftImageView);
        } else {
//            Toast.makeText(this, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.placeholder).into(spacecraftImageView);
        }

        nameTextView.setText(item.getData().get(0).getTitle());

        if (item.getData().get(0).isFavorite()) {
            favoriteOnImageView.setVisibility(View.VISIBLE);
            favoriteOffImageView.setVisibility(View.INVISIBLE);
        } else {
            favoriteOffImageView.setVisibility(View.VISIBLE);
            favoriteOnImageView.setVisibility(View.INVISIBLE);
        }

        if (item.getData().get(0).getLocation() == null || item.getData().get(0).getLocation().equals("")) {
            locationTitleTextView.setVisibility(View.GONE);
            locationTextView.setVisibility(View.GONE);
        } else {
            locationTextView.setText(item.getData().get(0).getLocation());
        }

        if(item.getData().get(0).getKeywordsStr() == null || item.getData().get(0).getKeywordsStr().equals("")){
            keywordsTitleTextView.setVisibility(View.GONE);
            keywordsTextView.setVisibility(View.GONE);
        }else{
            keywordsTextView.setText(item.getData().get(0).getKeywordsStr());
        }

        if(item.getData().get(0).getPhotographer() == null || item.getData().get(0).getPhotographer().equals("")){
            photographerTitleTextView.setVisibility(View.GONE);
            photographerTextView.setVisibility(View.GONE);
        }else{
            photographerTextView.setText(item.getData().get(0).getPhotographer());
        }

        if(item.getData().get(0).getDate_created() == null || item.getData().get(0).getDate_created().equals("")){
            dateCreatedTitleTextView.setVisibility(View.GONE);
            dateCreatedTextView.setVisibility(View.GONE);
        }else{
            dateCreatedTextView.setText(item.getData().get(0).getDate_created());
        }

        if(item.getData().get(0).getDescription() == null || item.getData().get(0).getDescription().equals("")){
            descriptionTitleTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        }else{
            descriptionTextView.setText(item.getData().get(0).getDescription());
        }

        favoriteOnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValueFavorite(false);
                favoriteOffImageView.setVisibility(View.VISIBLE);
                favoriteOnImageView.setVisibility(View.INVISIBLE);
            }
        });

        favoriteOffImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValueFavorite(true);
                favoriteOnImageView.setVisibility(View.VISIBLE);
                favoriteOffImageView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setValueFavorite(boolean flag){
        apollo11.getCollection().getItems().get(position).getData().get(0).setFavorite(flag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Vars.setSharedPreferences(this, "apollo11", new Gson().toJson(apollo11));
        finish();
        return true;
    }
}
