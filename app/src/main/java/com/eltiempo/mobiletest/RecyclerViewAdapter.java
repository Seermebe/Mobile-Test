package com.eltiempo.mobiletest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.eltiempo.mobiletest.model.Apollo11;
import com.eltiempo.mobiletest.model.Item;
import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Apollo11 apollo11;
    private Context context;
    private Item item;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView nameTxt;
        TextView descriptionTxt;
        ImageView favoriteOffImageView;
        ImageView favoriteOnImageView;
        ImageView spacecraftImageView;

        MyViewHolder(View view) {
            super(view);

            cv = view.findViewById(R.id.cv);
            nameTxt = view.findViewById(R.id.nameTextView);
            descriptionTxt = view.findViewById(R.id.descriptionTextView);
            favoriteOffImageView = view.findViewById(R.id.favoriteOffImageView);
            favoriteOnImageView = view.findViewById(R.id.favoriteOnImageView);
            spacecraftImageView = view.findViewById(R.id.spacecraftImageView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Context context, Apollo11 spacecrafts) {
        this.context = context;
        this.apollo11 = spacecrafts;
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
        item = apollo11.getCollection().getItems().get(position);

        holder.nameTxt.setText(item.getData().get(0).getTitle());
        holder.descriptionTxt.setText(item.getData().get(0).getDescription());

        if (item.getLinks() != null && item.getLinks().get(0).getHref() != null && item.getLinks().get(0).getHref().length() > 0) {
            Picasso.get().load(item.getLinks().get(0).getHref()).placeholder(R.drawable.placeholder).into(holder.spacecraftImageView);
        } else {
//            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.placeholder).into(holder.spacecraftImageView);
        }

        if (item.getData().get(0).isFavorite()) {
            holder.favoriteOnImageView.setVisibility(View.VISIBLE);
            holder.favoriteOffImageView.setVisibility(View.INVISIBLE);
        } else {
            holder.favoriteOffImageView.setVisibility(View.VISIBLE);
            holder.favoriteOnImageView.setVisibility(View.INVISIBLE);
        }

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                item = apollo11.getCollection().getItems().get(position);

//                Toast.makeText(context, item.getData().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, VisorActivity.class);

                if (item.getLinks() != null && item.getLinks().get(0).getHref() != null && item.getLinks().get(0).getHref().length() > 0) {
                    intent.putExtra("spacecraft", item.getLinks().get(0).getHref());
                } else {
                    intent.putExtra("spacecraft", "");
                }

                intent.putExtra("position", position);
//                intent.putExtra("name", item.getData().get(0).getTitle());
//                intent.putExtra("favorite", item.getData().get(0).isFavorite());
//                intent.putExtra("location", item.getData().get(0).getLocation());
//                intent.putExtra("keywords", item.getData().get(0).getKeywordsStr());
//                intent.putExtra("photographer", item.getData().get(0).getPhotographer());
//                intent.putExtra("dateCreated", item.getData().get(0).getDate_created());
//                intent.putExtra("description", item.getData().get(0).getDescription());

                context.startActivity(intent);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return apollo11.getCollection().getItems().size();
    }
}
