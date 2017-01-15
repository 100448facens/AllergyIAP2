package com.allergyiap.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.allergyiap.R;
import com.allergyiap.adapters.StationAdapter;
import com.allergyiap.beans.Station;
import com.allergyiap.service.StationService;
import com.allergyiap.utils.C;
import com.allergyiap.utils.CommonServices;
import com.allergyiap.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends BaseActivity {

    private static final String TAG = "LocationActivity";
    List<Station> stations = new ArrayList<>();
    private StationAdapter adapter;
    private RecyclerView recyclerView;
    private AsyncTask<Void, Void, List<Station>> task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CommonServices.RESULT_LOCATION_STATION){
            //if(requestCode == CommonServices.RESULT_RESTART) {
                setResult(CommonServices.RESULT_RESTART, new Intent());
                finish();
            //}
        }
    }

    @Override
    public void onBackPressed() {
        setResult(CommonServices.RESULT_RESTART, new Intent());
        super.onBackPressed();
    }

    private void loadData() {
        task = new LoadStationsBT();
        task.execute();
    }

    private void loadAdapter(final List<Station> list) {
        Log.d(TAG, ".loadAdapter");

        if (adapter == null)
            adapter = new StationAdapter(context, list);
        else
            adapter.setStation(list);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new StationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Station alertEntity) {
                //((CheckedTextView)view).toggle();
                showMap(alertEntity);
            }
        });
    }

    private void showMap(Station alertEntity) {
        Intent intent = new Intent(this, LocationMapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(C.IntentExtra.Sender.VAR_STATION, alertEntity);
        intent.putExtras(bundle);
        startActivityForResult(intent, CommonServices.RESULT_LOCATION_STATION);
    }

    private class LoadStationsBT extends AsyncTask<Void, Void, List<Station>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Station> doInBackground(Void... params) {

            return StationService.getAll();
        }

        @Override
        protected void onPostExecute(List<Station> result) {
            super.onPostExecute(result);
            findViewById(R.id.progress_bar).setVisibility(View.GONE);
            stations = result;
            loadAdapter(result);
        }
    }
}
