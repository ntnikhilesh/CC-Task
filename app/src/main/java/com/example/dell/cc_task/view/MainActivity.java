package com.example.dell.cc_task.view;

import android.app.SearchManager;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.cc_task.R;


public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener {


     SearchView searchView;
    String mTag="android";
    String flag2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
flag2="0";
        goto_firstfragment();

    }//end onCreate

    //go to first fragment
    public void goto_firstfragment()
    {
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag", mTag);
        bundle.putString("flag2", flag2);
// set FirstFragment Arguments
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack("f1");
        fragmentTransaction.commit();
    }



/*
    //inflate menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        inflater.inflate(R.menu.search_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
         searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));



        return true;
    }

*/





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
// action with ID action_settings was selected
            case R.id.action_activity:
                Toast.makeText(this, "coming soon... ", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_creation:
                Toast.makeText(this, "Sorted item on Cration", Toast.LENGTH_SHORT)
                        .show();
                
                break;
            default:
                break;
        }

        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }





    //Search operation
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.search_menu, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    Toast.makeText(MainActivity.this, "close", Toast.LENGTH_SHORT).show();
                    //some operation
                    return true;
                }
            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(MainActivity.this, "open", Toast.LENGTH_SHORT).show();
                    //some operation
                }
            });
            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchPlate.setHint("Search");
            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            // use this method for search process
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // use this method when query submitted
                         mTag=query;

                    // removes all whitespaces and non-visible characters (e.g., tab, \n).
                    mTag = mTag.replaceAll("\\s+","");
                    flag2="1";
                        goto_firstfragment();

                       // Toast.makeText(MainActivity.this, "Tag not found", Toast.LENGTH_SHORT).show();

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            //findViewById(R.id.default_title).setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }






}
