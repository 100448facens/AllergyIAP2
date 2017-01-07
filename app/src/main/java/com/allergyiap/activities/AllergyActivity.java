package com.allergyiap.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.allergyiap.R;
import com.allergyiap.adapters.ViewPagerAdapter;
import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.AllergyLevel;
import com.allergyiap.entities.CatalogEntity;
import com.allergyiap.utils.C;

public class AllergyActivity extends BaseActivity {


    private static final String TAG = "AllergyActivity";

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private Allergy allergy;
    private AllergyLevel allergyLevel;

    public Allergy getAllergy(){
        return allergy;
    }
    public AllergyLevel getAllergyLevel(){
        return allergyLevel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy);

        allergy = (Allergy)getIntent().getSerializableExtra(C.IntentExtra.Sender.VAR_ALLERGY);
        allergyLevel = (AllergyLevel)getIntent().getSerializableExtra(C.IntentExtra.Sender.VAR_ALLERGY2);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getSupportActionBar().setTitle(allergy.getAllergy_name());

        viewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupViewPager();
    }

    private void setupViewPager() {
        Log.v(TAG, ".setupViewPager");

        viewPager.removeAllViews();
        viewPager.removeAllViewsInLayout();
        //mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new AllergyProductsFragment(), getString(R.string.allergy_tab_products));
        adapter.addFrag(new AllergyAboutFragment(), getString(R.string.allergy_tab_about));

        viewPager.setAdapter(adapter);

        //initTabs();
    }
}
