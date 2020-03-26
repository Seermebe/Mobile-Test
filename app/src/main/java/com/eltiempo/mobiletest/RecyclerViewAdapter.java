package com.eltiempo.mobiletest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.eltiempo.mobiletest.model.Artist;
import com.eltiempo.mobiletest.model.TopArtists;
import com.eltiempo.mobiletest.model.TopTracks;
import com.eltiempo.mobiletest.model.Track;
import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private TopArtists topArtists;
    private TopTracks topTracks;
    private Context context;
    private Artist artist;
    private Track track;

    // Provide a reference to the views for each data track
    // Complex data items may need more than one view per track, and
    // you provide access to all the views for a data track in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data track is just a string in this case
        CardView cv;
        TextView nameTxt;
        TextView descriptionTxt;
        ImageView spacecraftImageView;

        MyViewHolder(View view) {
            super(view);

            cv = view.findViewById(R.id.cv);
            nameTxt = view.findViewById(R.id.nameTextView);
            descriptionTxt = view.findViewById(R.id.descriptionTextView);
            spacecraftImageView = view.findViewById(R.id.spacecraftImageView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Context context, TopArtists topArtists) {
        this.context = context;
        this.topArtists = topArtists;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Context context, TopTracks topArtist) {
        this.context = context;
        this.topTracks = topArtist;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (topArtists != null) {
            artist = topArtists.getTopartists().getArtist().get(position);

            holder.nameTxt.setText(artist.getName());
            holder.descriptionTxt.setText(artist.getDescription());

            if (artist.getImage() != null && artist.getImage().size() > 0) {
                Picasso.get().load(artist.getImage().get(3).getText()).placeholder(R.drawable.placeholder).into(holder.spacecraftImageView);
            } else {
                Picasso.get().load(R.drawable.placeholder).into(holder.spacecraftImageView);
            }

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    artist = topArtists.getTopartists().getArtist().get(position);

                    Intent intent = new Intent(context, VisorActivity.class);
                    intent.putExtra("type", 1);
                    intent.putExtra("position", position);

                    context.startActivity(intent);

                }
            });
        } else if (topTracks != null){
            track = topTracks.getTracks().getTrack().get(position);

            holder.nameTxt.setText(track.getName());
            holder.descriptionTxt.setText(track.getDescription());

            if (track.getImage() != null && track.getImage().size() > 0) {
                Picasso.get().load(track.getImage().get(3).getText()).placeholder(R.drawable.placeholder).into(holder.spacecraftImageView);
            } else {
                Picasso.get().load(R.drawable.placeholder).into(holder.spacecraftImageView);
            }

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    track = topTracks.getTracks().getTrack().get(position);

                    Intent intent = new Intent(context, VisorActivity.class);
                    intent.putExtra("type", 2);
                    intent.putExtra("position", position);

                    context.startActivity(intent);

                }
            });
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        int count;

        if (topArtists != null) {
            count = topArtists.getTopartists().getArtist().size();
        } else {
            count = topTracks.getTracks().getTrack().size();
        }

        return count;
    }
}
