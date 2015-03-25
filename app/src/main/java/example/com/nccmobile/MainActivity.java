package example.com.nccmobile;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activeandroid.query.Select;

public class MainActivity extends ActionBarActivity
        implements ItemListFragment.OnFragmentInteractionListener {
    private List<String> mNavTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    // used for passing position into change fragment
    private static final int ITEMLISTFRAGMENT = 0;
    private static final int ITEMDETAILEDFRAGMENT = 10;


    private String mTitle = "NCC 2015";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavTitles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.nav_titles_array)));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mNavTitles));
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                null,
                R.string.drawer_open,
                R.string.drawer_close
        );

        Fragment fragment = new ItemListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();

        mDrawerLayout.closeDrawer(mDrawerList);

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // call ActionBarDrawerToggle.onOptionsItemSelected(), if it returns true
        // then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(long id) {
        ItemFragment.selectedItem = new Select().from(Item.class).where("_id = ?", id).executeSingle();
        changeFragment(ITEMDETAILEDFRAGMENT);
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            changeFragment(position);
        }

    }

    public void changeFragment(int position) {
        Bundle args = new Bundle();
        changeFragment(position, args);
    }

    public void changeFragment(int position, Bundle args) {
        Fragment fragment = null;
        switch (position) {
            case ITEMLISTFRAGMENT:
                fragment = new ItemListFragment();
                break;
            case ITEMDETAILEDFRAGMENT:
                fragment = new ItemFragment();
                break;
            default:
                fragment = new ItemListFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).addToBackStack("home").commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            if ( position < mNavTitles.size() ) {
                setTitle(mNavTitles.get(position));
            } else {
                setTitle(mTitle);
            }

            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}

