package com.allergyiap.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allergyiap.R;
import com.allergyiap.beans.Allergy;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllergyAboutFragment extends Fragment {

    static final String TAG = "AllergyAboutFragment";

    AllergyActivity activity;
    Context context;
    ViewHolder viewHolder;

    public AllergyAboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AllergyActivity) getActivity();
        context = getActivity().getApplicationContext();

        //activity.updateLocale();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_allergy_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, ".onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        viewHolder = new ViewHolder(view);

        loadData();
    }

    private void loadData() {

        Allergy allergy = activity.getAllergy();
        viewHolder.description.setText(allergy.getAllergy_description());
    }

    /**
     * Class To represent a view with elements
     */
    class ViewHolder {
        TextView description;

        public ViewHolder(View view) {

            description = (TextView) view.findViewById(R.id.description);
        }
    }
}
