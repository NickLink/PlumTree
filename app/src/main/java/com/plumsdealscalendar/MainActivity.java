package com.plumsdealscalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.plumsdealscalendar.fragments.Login;
import com.plumsdealscalendar.fragments.Settings;
import com.plumsdealscalendar.models.login.Payload;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements Login.LoginCompleteListener {
    RadioGroup main_menu_group;
    FrameLayout frame_place_inner, frame_place_outer;
    FragmentManager fragmentManager;

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

        main_menu_group.check(R.id.menu_calendar_item);

        main_menu_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.menu_calendar_item:
                        Toast.makeText(getBaseContext(), "Selected Calendar", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_busines_item:
                        Toast.makeText(getBaseContext(), "Selected Busines", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_settings_item:
                        Toast.makeText(getBaseContext(), "Selected Settings", Toast.LENGTH_SHORT).show();
                        fragmentManager.beginTransaction().replace(R.id.frame_place_inner, new Login(), "login").commit();
                        break;

                }

            }
        });

    }

    @Override
    public void LoginComplete(Payload payload) {
        fragmentManager.beginTransaction().replace(R.id.frame_place_inner, new Settings(), "settings").commit();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // store the data in the fragment
//        //dataFragment.setData(collectMyLoadedData());
//    }

}
