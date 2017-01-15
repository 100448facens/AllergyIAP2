package com.allergyiap.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.allergyiap.R;
import com.allergyiap.beans.Station;
import com.allergyiap.entities.ProductCatalogEntity;
import com.allergyiap.service.StationService;
import com.allergyiap.service.UserService;
import com.allergyiap.utils.LocationService;
import com.allergyiap.utils.ReceiveAlarm;
import com.allergyiap.utils.Util;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private Menu menu;
    private boolean[] menuItemVisibility;
    private DrawerLayout drawer;

    public enum menuItemPosition {SEARCH, LOCATION}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(Util.station.getName_station());

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menuItemVisibility = new boolean[menuItemPosition.values().length];
        for (int i = 0; i < menuItemPosition.values().length; i++)
            menuItemVisibility[i] = false;


        navigationView.setCheckedItem(R.id.nav_level);
        navigationView.getMenu().performIdentifierAction(R.id.nav_level, 0);

        Menu menu=navigationView.getMenu();
        if(UserService.getCurrentUser().getIduser()==-1){
            menu.findItem(R.id.nav_profile).setVisible(false);
            menu.findItem(R.id.nav_login).setVisible(true);
        }else{
            menu.findItem(R.id.nav_profile).setVisible(false);
            menu.findItem(R.id.nav_login).setVisible(false);
            /*
            TextView t=(TextView)navigationView.findViewById(R.id.text_user);
            t.setText(UserService.getCurrentUser().getUser_name());
            t.setVisibility(View.VISIBLE);
            */
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        updateMenu();
        return true;
    }

    private void updateMenu() {
        if (this.menu != null) {
            this.menu.findItem(R.id.menu_search).setVisible(menuItemVisibility[menuItemPosition.SEARCH.ordinal()]);
            this.menu.findItem(R.id.menu_station).setVisible(menuItemVisibility[menuItemPosition.LOCATION.ordinal()]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_station) {
            startActivity(new Intent(this, LocationActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_login:
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.nav_profile:
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, SignupActivity.class));
                break;
            case R.id.nav_level:
                openFragment(LevelAllergyFragment.class);
                //changeTabText(R.string.menu_level);
                changeTabText(Util.station.getName_station());
                menuItemVisibility[menuItemPosition.SEARCH.ordinal()] = false;
                menuItemVisibility[menuItemPosition.LOCATION.ordinal()] = true;
                updateMenu();
                break;
            case R.id.nav_products:
                openFragment(ProductsFragment.class);
                changeTabText(R.string.menu_product);
                menuItemVisibility[menuItemPosition.SEARCH.ordinal()] = true;
                menuItemVisibility[menuItemPosition.LOCATION.ordinal()] = false;
                updateMenu();
                break;
            case R.id.nav_allergy:
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, MyAllergiesActivity.class));
                break;
            case R.id.nav_settings:
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_help:
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, HelpActivity.class));
                break;
        }

        return true;
    }

    private void changeTabText(int title) {
        toolbar.setTitle(title);
    }

    private void changeTabText(String title) {
        toolbar.setTitle(title);
    }

    private void openFragment(Class fragmentClass) {

        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.main_fragment, fragment).commit();

            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
