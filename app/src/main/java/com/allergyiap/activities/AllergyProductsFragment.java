package com.allergyiap.activities;

import android.content.Context;
import android.content.Intent;
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
import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.ProductCatalog;
import com.allergyiap.service.ProductCatalogService;
import com.allergyiap.utils.C;

import java.util.ArrayList;
import java.util.List;

public class AllergyProductsFragment extends Fragment {

    static final String TAG = "AllergyProductsFragment";

    private AllergyActivity activity;
    private Context context;
    private View view;
    private CatalogAdapter adapter;
    private RecyclerView recyclerView;
    private AsyncTask<Void, Void, List<ProductCatalog>> task;

    private Allergy allergy;

    public AllergyProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AllergyActivity) getActivity();
        context = getActivity().getApplicationContext();

        //activity.updateLocale();
        allergy = activity.getAllergy();
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
        this.view = view;
        recyclerView = (RecyclerView) view.findViewById(R.id.scrollableview);
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
        task = new LoadProductsByAllergyBT();
        task.execute();
    }

    private void loadAdapter(final List<ProductCatalog> list) {
        Log.d(TAG, ".loadAdapter");

        if (adapter == null)
            adapter = new CatalogAdapter(context, list);
        else
            adapter.setCatalogs(list);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CatalogAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, ProductCatalog catalogEntity) {
                //setContentView(R.layout.product_info);

                Intent intent = new Intent(activity, ProductCatalogMapActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(C.IntentExtra.Sender.VAR_PRODUCT, catalogEntity);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private class LoadProductsByAllergyBT extends AsyncTask<Void, Void, List<ProductCatalog>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected List<ProductCatalog> doInBackground(Void... params) {

            //ProductCatalogService.getAllByAllergy(allergy.getIdallergy());
            ProductCatalogService.getAll();
            return null;
        }

        @Override
        protected void onPostExecute(List<ProductCatalog> result) {
            super.onPostExecute(result);
            view.findViewById(R.id.progress_bar).setVisibility(View.GONE);
            loadAdapter(result);
        }
    }
}
