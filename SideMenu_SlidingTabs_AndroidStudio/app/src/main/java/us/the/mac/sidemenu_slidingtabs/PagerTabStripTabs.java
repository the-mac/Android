package us.the.mac.sidemenu_slidingtabs;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class PagerTabStripTabs extends FragmentActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_tab_strip_tabs);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.drawer_list);

        //Swiping fragments with tabs at the top
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab);
        String[] drawerItems = getResources().getStringArray(R.array.drawer_items); //Found in 'values/strings.xml'

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {//Navigation Drawer is completely open
                getActionBar().setTitle(getString(R.string.app_name));
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {//Navigation Drawer is completely closed
                getActionBar().setTitle(getString(R.string.title_activity_pager_tab_strip_tabs));
                super.onDrawerClosed(drawerView);
            }
        };

        //Shows Navigation Drawer menu icon on the top left of the screen (click-able)
        getActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerItems));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());


        //View Pager initialization
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.holo_blue)); //Found in 'values/colors.xml'
        viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager()));
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            //Do something when item on Navigation Drawer clicked
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pager_tab_strip_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //ActionBarToggle handles navigation drawer events
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Must be called at this state for the ActionBarDrawerToggle
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //Must be called at this state for the ActionBarDrawerToggle
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    //Adapter for ViewPager
    private class TabsPagerAdapter extends FragmentPagerAdapter {

        private String[] tabNames;

        public TabsPagerAdapter(FragmentManager fm){
            super(fm);
            tabNames = getResources().getStringArray(R.array.tab_names);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new CustomFragment();

            Bundle args = new Bundle();
            args.putInt(CustomFragment.KEY_NUMBER, position);

            //Send information to each individual fragment this way
            fragment.setArguments(args);

            return fragment; //Return fragment to be shown to user
        }

        @Override //The number of tabs to create
        public int getCount() {
            return tabNames.length;
        }

        @Override //Return tab titles the array
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }

}
