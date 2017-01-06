package com.allergyiap.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.allergyiap.R;
import com.allergyiap.adapters.ViewPagerAdapter;
import com.allergyiap.entities.CatalogEntity;
import com.allergyiap.entities.StationEntity;
import com.allergyiap.utils.C;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class ProductCatalogMapActivity extends BaseActivity {

    private static final String TAG = "PCMActivity";

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private CatalogEntity product;

    public CatalogEntity getProduct(){
        return product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_catalog_map);

        product = (CatalogEntity)getIntent().getSerializableExtra(C.IntentExtra.Sender.VAR_PRODUCT);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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

        adapter.addFrag(new ProductAboutFragment(), getString(R.string.product_tab_wherebuy));
        adapter.addFrag(new ProductWhereBuyFragment(), getString(R.string.product_tab_about));

        viewPager.setAdapter(adapter);

        //initTabs();
    }



}