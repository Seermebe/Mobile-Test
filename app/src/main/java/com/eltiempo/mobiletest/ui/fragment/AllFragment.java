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
import com.eltiempo.mobiletest.model.Apollo11;
import com.eltiempo.mobiletest.model.Item;
import com.eltiempo.mobiletest.util.Apollo11Client;
import com.eltiempo.mobiletest.util.Vars;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class AllFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView.Adapter adapter;
    private RecyclerView mListView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar myProgressBar;
    private FloatingActionButton fab;
    private Button deleteAllButton;
    private Button refreshButton;

    public static AllFragment newInstance(int index) {
        AllFragment fragment = new AllFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void populateListView(Apollo11 apollo11) {

        mListView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(getActivity(), apollo11);
        mListView.setAdapter(adapter);

        Vars.setSharedPreferences(getActivity(), "apollo11", new Gson().toJson(apollo11));
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
        fab = root.findViewById(R.id.fab);
        deleteAllButton = root.findViewById(R.id.deleteAllButton);
        refreshButton = root.findViewById(R.id.refreshButton);

        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        String apollo11Str = Vars.getSharedPreferences(getActivity(), "apollo11");

        if (apollo11Str.equals("")) {
            service();
        } else {
            myProgressBar.setVisibility(View.GONE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Search item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apollo11Str = Vars.getSharedPreferences(getActivity(), "apollo11");
                Gson gson = new Gson();
                Apollo11 apollo11 = gson.fromJson(apollo11Str, Apollo11.class);
                apollo11.getCollection().setItems(new ArrayList<Item>());
                populateListView(apollo11);
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

        String apollo11Str = Vars.getSharedPreferences(getActivity(), "apollo11");
        if (!apollo11Str.equals("")) {
            Gson gson = new Gson();
            Apollo11 apollo11 = gson.fromJson(apollo11Str, Apollo11.class);
            populateListView(apollo11);
        }
    }

    private void service() {
        /*Create handle for the RetrofitInstance interface*/
        Apollo11Client apollo11Client = Vars.getRetrofitInstance.create(Apollo11Client.class);

        Call<Apollo11> callApollo = apollo11Client.getApollo11("apollo 11");
        callApollo.enqueue(new Callback<Apollo11>() {
            @Override
            public void onResponse(Call<Apollo11> call, Response<Apollo11> response) {
                myProgressBar.setVisibility(View.GONE);
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<Apollo11> call, Throwable t) {
                myProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}