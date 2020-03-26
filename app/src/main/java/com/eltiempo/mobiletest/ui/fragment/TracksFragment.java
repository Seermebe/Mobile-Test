package com.eltiempo.mobiletest.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eltiempo.mobiletest.R;
import com.eltiempo.mobiletest.RecyclerViewAdapter;
import com.eltiempo.mobiletest.model.TopTracks;
import com.eltiempo.mobiletest.model.Track;
import com.eltiempo.mobiletest.util.TopArtistsClient;
import com.eltiempo.mobiletest.util.TopTracksClient;
import com.eltiempo.mobiletest.util.Vars;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class TracksFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView.Adapter adapter;
    private RecyclerView mListView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar myProgressBar;
    //    private FloatingActionButton fab;
    private Button deleteAllButton;
    private Button refreshButton;

    public static TracksFragment newInstance(int index) {
        TracksFragment fragment = new TracksFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void populateListView(TopTracks topArtist) {

        mListView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(getActivity(), topArtist);
        mListView.setAdapter(adapter);

        Vars.setSharedPreferences(getActivity(), "TopTracks", new Gson().toJson(topArtist));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_all, container, false);

        myProgressBar = root.findViewById(R.id.myProgressBar);
        mListView = root.findViewById(R.id.mListView);
//        fab = root.findViewById(R.id.fab);
        deleteAllButton = root.findViewById(R.id.deleteAllButton);
        refreshButton = root.findViewById(R.id.refreshButton);

        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        String topArtistStr = Vars.getSharedPreferences(getActivity(), "TopTracks");

        if (topArtistStr.equals("")) {
            service();
        } else {
            myProgressBar.setVisibility(View.GONE);
        }

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Desarrollo pendiente", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//            }
//        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topArtistStr = Vars.getSharedPreferences(getActivity(), "TopTracks");
                Gson gson = new Gson();
                TopTracks topArtist = gson.fromJson(topArtistStr, TopTracks.class);
                topArtist.getTracks().setTrack(new ArrayList<Track>());
                populateListView(topArtist);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        String topArtistStr = Vars.getSharedPreferences(getActivity(), "TopTracks");

        if (!topArtistStr.equals("")) {
            Gson gson = new Gson();
            TopTracks topArtist = gson.fromJson(topArtistStr, TopTracks.class);
            populateListView(topArtist);
        }
    }

    private void service() {
        /*Create handle for the RetrofitInstance interface*/
        TopTracksClient topArtistClient = Vars.getRetrofitInstance.create(TopTracksClient.class);

        Call<TopTracks> callApollo = topArtistClient.getTopTracks("geo.gettoptracks", "colombia", "829751643419a7128b7ada50de590067", "json", "1");
        callApollo.enqueue(new Callback<TopTracks>() {
            @Override
            public void onResponse(Call<TopTracks> call, Response<TopTracks> response) {
                myProgressBar.setVisibility(View.GONE);
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<TopTracks> call, Throwable t) {
                myProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}