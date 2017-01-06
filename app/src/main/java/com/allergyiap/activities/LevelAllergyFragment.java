package com.allergyiap.activities;


import android.content.Context;
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
import com.allergyiap.adapters.AllergiesAdapter;
import com.allergyiap.entities.AllergyLevelEntity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelAllergyFragment extends Fragment {

    static final String TAG = "LevelAllergyFragment";

    MainActivity activity;
    Context context;
    private AllergiesAdapter adapter;
    private RecyclerView recyclerView;
    private AsyncTask<Void, Void, Void> task;

    List<AllergyLevelEntity> allergy;

    public LevelAllergyFragment() {
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
        super.onDestroyView();

        adapter = null;

        if (task != null) {
            task.cancel(true);
            task = null;
        }
    }

    private void loadData() {
        task = new LoadAllergiesBT();
        task.execute();
    }

    private void loadAdapter(final List<AllergyLevelEntity> list) {
        Log.d(TAG, ".loadAdapter");

        if (adapter == null)
            adapter = new AllergiesAdapter(context, list);
        else
            adapter.setAllergies(list);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AllergiesAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, AllergyLevelEntity alertEntity) {
                showAllergyDetail(alertEntity);
            }
        });
    }

    private void showAllergyDetail(AllergyLevelEntity alertEntity) {
        /*Intent intent = new Intent(this, MapAllergyLevelsDetailsActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(C.IntentExtra.Sender.VAR_ALLERGY2, alertEntity);
        intent.putExtras(b);
        startActivity(intent);*/
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
            loadAdapter(allergy);
        }
    }
}
