package com.plumsdealscalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.plumsdealscalendar.app.MyApplication;
import com.plumsdealscalendar.fragments.Login;
import com.plumsdealscalendar.fragments.ProfileEdit;
import com.plumsdealscalendar.fragments.ProfileView;
import com.plumsdealscalendar.fragments.Settings;
import com.plumsdealscalendar.models.login.Payload;
import com.plumsdealscalendar.interfaces.UI_Interfaces;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements UI_Interfaces {
    RadioGroup main_menu_group;
    FrameLayout frame_place_inner, frame_place_outer;
    FragmentManager fragmentManager;
    static String TAG = "MainActivity";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // find the retained fragment on activity restarts
//        FragmentManager fm = getFragmentManager();
//        dataFragment = (DataFragment) fm.findFragmentByTag(“data”);
//
//        // create the fragment and data the first time
//        if (dataFragment == null) {
//            // add the fragment
//            dataFragment = new DataFragment();
//            fm.beginTransaction().add(dataFragment, “data”).commit();
//            // load the data from the web
//            dataFragment.setData(loadMyData());
//        }
//
//        // the data is available in dataFragment.getData()
//        ...


        main_menu_group = (RadioGroup) findViewById(R.id.main_menu_group);
        frame_place_inner = (FrameLayout) findViewById(R.id.frame_place_inner);
        frame_place_outer = (FrameLayout) findViewById(R.id.frame_place_outer);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                //fragmentManager.executePendingTransactions();
                //Log.d(TAG, "Data onBackStackChanged count = " + fragmentManager.getBackStackEntryCount());
                //Log.d(TAG, "Data onBackStackChanged list = "
                //        + fragmentManager.getFragments().toString());
            }
        });

        main_menu_group.check(R.id.menu_calendar_item);

        main_menu_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.menu_calendar_item:
                        Toast.makeText(getBaseContext(), "Selected Calendar", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_busines_item:
                        Toast.makeText(getBaseContext(), "Selected Busines", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_settings_item:
                        Toast.makeText(getBaseContext(), "Selected Settings", Toast.LENGTH_SHORT).show();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_place_inner, new Login(), "login")
                                //.addToBackStack("settings")
                                .commit();
                        break;

                }

            }
        });

    }

    @Override
    public void LoginComplete(Payload payload) {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_place_inner, new Settings(), "settings")
                .addToBackStack("settings")
                .commit();
    }

    @Override
    public void ProfileView() {
        Log.d(TAG, "Data ProfileView");
        frame_place_inner.setVisibility(View.GONE);
        frame_place_outer.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction()
                .replace(R.id.frame_place_outer, new ProfileView(), "profile_view") //frame_place_outer
                .addToBackStack("profile_view")
                .commit();
    }

    @Override
    public void ProfileEdit() {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_place_outer, new ProfileEdit(), "profile_edit") //frame_place_outer
                .addToBackStack("profile_edit")
                .commit();
    }

    @Override
    public void HideOuterFrame() {
        Log.d(TAG, "Data HideOuterFrame");
        frame_place_outer.setVisibility(View.GONE);
        frame_place_inner.setVisibility(View.VISIBLE);
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // store the data in the fragment
//        //dataFragment.setData(collectMyLoadedData());
//    }

    @Override
    public void onBackPressed() {
        //fragmentManager.executePendingTransactions();
        Log.d(TAG, "Data onBackPressed count = " + fragmentManager.getBackStackEntryCount());
//        if (fragmentManager.getBackStackEntryCount() > 1) {
//            HideOuterFrame();
//        }
        if (fragmentManager.getBackStackEntryCount() > 0) {
            //Back to stack
            fragmentManager.popBackStack();
        } else {
            //Finnish activity dialog
            super.onBackPressed();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getInstance().cancelAllRequests();
    }
}
