package com.allergyiap.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;

import com.allergyiap.R;
import com.allergyiap.adapters.AllergyAdapter;
import com.allergyiap.beans.Allergy;
import com.allergyiap.service.AllergyService;
import com.allergyiap.service.UserAllergyService;

import java.util.ArrayList;
import java.util.List;

public class MyAllergiesActivity extends BaseActivity {

    private static final String TAG = "MyAllergiesActivity";
    List<Allergy> allergies = new ArrayList<>();
    private AllergyAdapter adapter;
    private RecyclerView recyclerView;
    private AsyncTask<Void, Void, List<Allergy>> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_allergies);

        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (task != null) {
            task.cancel(true);
            task = null;
        }
    }

    private void loadData() {
        task = new LoadAllergiesBT();
        task.execute();
    }

    private void loadAdapter(final List<Allergy> list) {
        Log.d(TAG, ".loadAdapter");

        if (adapter == null)
            adapter = new AllergyAdapter(context, list);
        else
            adapter.setAllergy(list);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AllergyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Allergy alertEntity) {
                CheckedTextView v = (CheckedTextView) view;
                v.toggle();
                UserAllergyService.setAllergyToTheCurrentUser(alertEntity.getIdallergy(), v.isChecked());
            }
        });
    }

    private class LoadAllergiesBT extends AsyncTask<Void, Void, List<Allergy>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Allergy> doInBackground(Void... params) {

            return AllergyService.getAll();
        }

        @Override
        protected void onPostExecute(List<Allergy> result) {
            super.onPostExecute(result);
            findViewById(R.id.progress_bar).setVisibility(View.GONE);
            allergies = result;
            loadAdapter(result);
        }
    }
}
