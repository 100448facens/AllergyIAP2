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
import com.allergyiap.adapters.AllergiesLevelAdapter;
import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.AllergyLevel;
import com.allergyiap.service.AllergyLevelService;
import com.allergyiap.utils.C;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelAllergyFragment extends Fragment {

    static final String TAG = "LevelAllergyFragment";

    MainActivity activity;
    Context context;
    View view;
    private AllergiesLevelAdapter adapter;
    private RecyclerView recyclerView;
    private AsyncTask<Void, Void, List<AllergyLevel>> task;

    List<AllergyLevel> allergiesLevel;

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
        this.view = view;
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
        task = new LoadAllergyLevelBT();
        task.execute();
    }

    private void loadAdapter(final List<AllergyLevel> list) {
        Log.d(TAG, ".loadAdapter");

        if (adapter == null)
            adapter = new AllergiesLevelAdapter(context, list);
        else
            adapter.setAllergies(list);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AllergiesLevelAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, AllergyLevel allergyLevel, Allergy allergy) {
                showAllergyDetail(allergy, allergyLevel);
            }
        });
    }

    private void showAllergyDetail(Allergy allergy, AllergyLevel allergyLevel) {
        Intent intent = new Intent(activity, AllergyActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(C.IntentExtra.Sender.VAR_ALLERGY, allergy);
        b.putSerializable(C.IntentExtra.Sender.VAR_ALLERGY2, allergyLevel);
        intent.putExtras(b);
        startActivity(intent);
    }


    private class LoadAllergyLevelBT extends AsyncTask<Void, Void, List<AllergyLevel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected List<AllergyLevel> doInBackground(Void... params) {

            return AllergyLevelService.getAll();
        }

        @Override
        protected void onPostExecute(List<AllergyLevel> result) {
            super.onPostExecute(result);
            view.findViewById(R.id.progress_bar).setVisibility(View.GONE);
            Log.d(TAG, ".onPostExecute LoadAllergyLevelBT");
            loadAdapter(result);
        }
    }
}
