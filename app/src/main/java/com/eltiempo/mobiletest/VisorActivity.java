package com.eltiempo.mobiletest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eltiempo.mobiletest.model.Artist;
import com.eltiempo.mobiletest.model.TopArtists;
import com.eltiempo.mobiletest.model.TopTracks;
import com.eltiempo.mobiletest.model.Track;
import com.eltiempo.mobiletest.util.Vars;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class VisorActivity extends AppCompatActivity {

    private ImageView spacecraftImageView;
    private TextView nameTextView;

    private TextView durationTitleTextView;
    private TextView durationTextView;
    private TextView listenersTextView;
    private TextView urlSongTextView;
    private TextView artistTitleTextView;
    private TextView artistTextView;
    private TextView urlArtistTitleTextView;
    private TextView urlArtistTextView;
    private TextView rankTitleTextView;
    private TextView rankTextView;

    private TopArtists topArtists;
    private TopTracks topTracks;
    private int type;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                type = 1;
                position = 0;
            } else {
                type = extras.getInt("type");
                position = extras.getInt("position");
            }
        } else {
            type = (int) savedInstanceState.getSerializable("type");
            position = (int) savedInstanceState.getSerializable("position");
        }

        spacecraftImageView = findViewById(R.id.spacecraftImageView);
        nameTextView = findViewById(R.id.nameTextView);

        durationTitleTextView = findViewById(R.id.durationTitleTextView);
        durationTextView = findViewById(R.id.durationTextView);
        listenersTextView = findViewById(R.id.listenersTextView);
        urlSongTextView = findViewById(R.id.urlSongTextView);
        artistTitleTextView = findViewById(R.id.artistTitleTextView);
        artistTextView = findViewById(R.id.artistTextView);
        urlArtistTitleTextView = findViewById(R.id.urlArtistTitleTextView);
        urlArtistTextView = findViewById(R.id.urlArtistTextView);
        rankTitleTextView = findViewById(R.id.rankTitleTextView);
        rankTextView = findViewById(R.id.rankTextView);

        String top = "";

        switch (type){
            case 1:
                top = Vars.getSharedPreferences(this, "TopArtists");
                topArtists = new Gson().fromJson(top, TopArtists.class);
                Artist artist = topArtists.getTopartists().getArtist().get(position);

                if (artist.getImage() != null && artist.getImage().size() > 0) {
                    Picasso.get().load(artist.getImage().get(3).getText()).placeholder(R.drawable.placeholder).into(spacecraftImageView);
                } else {
                    Picasso.get().load(R.drawable.placeholder).into(spacecraftImageView);
                }

                nameTextView.setText(artist.getName());

                durationTitleTextView.setVisibility(View.GONE);
                durationTextView.setVisibility(View.GONE);
                listenersTextView.setText(artist.getListeners());
                urlSongTextView.setText(artist.getUrl());
                artistTitleTextView.setVisibility(View.GONE);
                artistTextView.setVisibility(View.GONE);
                urlArtistTitleTextView.setVisibility(View.GONE);
                urlArtistTextView.setVisibility(View.GONE);
                rankTitleTextView.setVisibility(View.GONE);
                rankTextView.setVisibility(View.GONE);

                urlSongTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToUrl(urlSongTextView.getText().toString());
                    }
                });
                break;
            case 2:
                top = Vars.getSharedPreferences(this, "TopTracks");
                topTracks = new Gson().fromJson(top, TopTracks.class);

                Track track = topTracks.getTracks().getTrack().get(position);

                if (track.getImage() != null && track.getImage().size() > 0) {
                    Picasso.get().load(track.getImage().get(3).getText()).placeholder(R.drawable.placeholder).into(spacecraftImageView);
                } else {
                    Picasso.get().load(R.drawable.placeholder).into(spacecraftImageView);
                }

                nameTextView.setText(track.getName());

                durationTextView.setText(track.getDuration());
                listenersTextView.setText(track.getListeners());
                urlSongTextView.setText(track.getUrl());
                artistTextView.setText(track.getArtist().getName());
                urlArtistTextView.setText(track.getArtist().getUrl());
                rankTextView.setText(track.getAttr().getRank());

                urlSongTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToUrl(urlSongTextView.getText().toString());
                    }
                });

                urlArtistTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToUrl(urlArtistTextView.getText().toString());
                    }
                });
                break;
        }




    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
