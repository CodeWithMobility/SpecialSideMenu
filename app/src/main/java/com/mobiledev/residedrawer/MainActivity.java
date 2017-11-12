package com.mobiledev.residedrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiledev.residedrawer.model.MenuModel;
import com.mobiledev.residedrawer.ui.FragmentAboutus;
import com.mobiledev.residedrawer.ui.FragmentContactus;
import com.mobiledev.residedrawer.ui.FragmentHome;
import com.mobiledev.residedrawer.ui.FragmentProfile;
import com.mobiledev.residedrawer.ui.FragmentSettings;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListsner {
    private ArrayList<MenuModel> itemList;
    private ResideLayout resideLayout;
    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareArrayLits();
        resideLayout = findViewById(R.id.reside_layout);
        titleView = findViewById(R.id.titleView);
        RecyclerView menuRecyclerView = findViewById(R.id.menuList);
        MenuAdapter adapter = new MenuAdapter(this, itemList, this);
        menuRecyclerView.setAdapter(adapter);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        ImageView menuButton = findViewById(R.id.sideMenuIcon);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideLayout.openPane();
            }
        });
        displayView(0);
    }


    public void prepareArrayLits() {
        itemList = new ArrayList<MenuModel>();
        AddObjectToList(R.drawable.home, getString(R.string.title_home));
        AddObjectToList(R.drawable.profile, getString(R.string.title_profile));
        AddObjectToList(R.drawable.aboutus, getString(R.string.title_aboutus));
        AddObjectToList(R.drawable.callus, getString(R.string.title_contactus));
        AddObjectToList(R.drawable.settings, getString(R.string.title_settings));
        AddObjectToList(R.drawable.logout, getString(R.string.title_logout));

    }

    // Add one item into the Array List
    public void AddObjectToList(int image, String title) {
        MenuModel bean = new MenuModel();
        bean.setIcon(image);
        bean.setTitle(title);
        itemList.add(bean);
    }

    @Override
    public void clickListener(int position) {
        displayView(position);
    }

    private void displayView(int position) {
        resideLayout.closePane();
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new FragmentHome();
                break;
            case 1:
                fragment = new FragmentProfile();
                break;
            case 2:
                fragment = new FragmentAboutus();
                break;
            case 3:
                fragment = new FragmentContactus();
                break;
            case 4:
                fragment = new FragmentSettings();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerLayout, fragment);
            fragmentTransaction.commit();

        }
    }

    public void updateTitle(String title) {
        titleView.setText(title);

    }
}