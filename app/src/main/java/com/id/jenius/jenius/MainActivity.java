package com.id.jenius.jenius;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.id.jenius.jenius.model.Contacts;
import com.id.jenius.jenius.presenter.mainActivity.MainPresenter;
import com.id.jenius.jenius.presenter.mainActivity.MainViewInterface;
import com.id.jenius.jenius.recylerViewAdapter.RecyclerViewAdapter;
import com.id.jenius.jenius.util.ProgressDialogBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainViewInterface {

    @BindView(R.id.recyleview)
    RecyclerView recyclerView;

    private MainPresenter mainPresenter;
    private RecyclerViewAdapter adapter;
    private ProgressDialogBar progressDialogBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter = new MainPresenter(this);
        mainPresenter.getContacts();
        progressDialogBar = new ProgressDialogBar();
        progressDialogBar.ProgressDialogBar(this);
    }

    @Override
    public void displayData(Contacts contacts){
        adapter = new RecyclerViewAdapter(contacts, MainActivity.this,this){};
        recyclerView.setAdapter(adapter);
        progressDialogBar.HideProgressDialogBar();
    }

    @Override
    public void displayError(String e){
        Toast.makeText(this,e, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgressBar(){

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_data) {
            startActivity(new Intent(this, PostContactsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void deleteData(String id){
        mainPresenter.deleteContacts(id);
        Log.d("asda",id);
    }
}
