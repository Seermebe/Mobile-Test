package com.eltiempo.mobiletest.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eltiempo.mobiletest.R;
import com.eltiempo.mobiletest.RecyclerViewAdapter;
import com.eltiempo.mobiletest.model.Apollo11;
import com.eltiempo.mobiletest.model.Item;
import com.eltiempo.mobiletest.util.Vars;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavoriteFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView.Adapter adapter;
    private RecyclerView mListView;
    private RecyclerView.LayoutManager layoutManager;
    private static Apollo11 apollo11;
    private static Activity activity;

    public static FavoriteFragment newInstance(int index) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void populateListView(Apollo11 apollo11) {

        List<Item> items = apollo11.getCollection().getItems();

        apollo11.getCollection().setItems(new ArrayList<Item>());

        for (Item item : items) {
            if (item.getData().get(0).isFavorite()) {
                apollo11.getCollection().getItems().add(item);
            }
        }

        mListView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(getActivity(), apollo11);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_favorite, container, false);

        activity = getActivity();
        mListView = root.findViewById(R.id.mListView);

        String apollo11Str = Vars.getSharedPreferences(getActivity(), "apollo11");
        Gson gson = new Gson();
        apollo11 = gson.fromJson(apollo11Str, Apollo11.class);
        if (apollo11 != null)
            populateListView(apollo11);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        String apollo11Str = Vars.getSharedPreferences(getActivity(), "apollo11");
        Gson gson = new Gson();
        apollo11 = gson.fromJson(apollo11Str, Apollo11.class);
        if (apollo11 != null)
            populateListView(apollo11);

    }
}