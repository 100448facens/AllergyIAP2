package com.allergyiap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.allergyiap.R;
import com.allergyiap.entities.ProductCatalogEntity;

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

        navigationView.setCheckedItem(R.id.nav_products);
        navigationView.getMenu().performIdentifierAction(R.id.nav_products, 0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                break;
            case R.id.nav_level:
                openFragment(LevelAllergyFragment.class);
                changeTabText(R.string.menu_level);
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
