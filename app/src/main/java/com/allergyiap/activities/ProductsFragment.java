package com.allergyiap.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allergyiap.R;
import com.allergyiap.adapters.CatalogAdapter;
import com.allergyiap.entities.CatalogEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    static final String TAG = "ProductsFragment";

    MainActivity activity;
    Context context;
    private CatalogAdapter adapter;
    private RecyclerView recyclerView;
    List<CatalogEntity> catalogs = new ArrayList<>();
    private AsyncTask<Void, Void, Void> task;


    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        context = getActivity().getApplicationContext();

        //activity.updateLocale();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_products, container, false);
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, ".onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) activity.findViewById(R.id.scrollableview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        loadData();
    }

    @Override
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView");
        super.onDestroyView();

        if (task != null) {
            task.cancel(true);
            task = null;
        }
        adapter = null;
    }

    private void loadData() {
        task = new LoadAllergiesBT();
        task.execute();
    }

    private void loadAdapter(final List<CatalogEntity> list) {
        Log.d(TAG, ".loadAdapter");

        if (adapter == null)
            adapter = new CatalogAdapter(context, list);
        else
            adapter.setCatalogs(list);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CatalogAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, CatalogEntity catalogEntity) {
                //setContentView(R.layout.product_info);
                startActivity(new Intent(context, ProductCatalogMapActivity.class));

            }
        });
    }

    private class LoadAllergiesBT extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            activity.findViewById(R.id.progress_bar).setVisibility(View.GONE);
            loadAdapter(catalogs);
        }
    }
}
