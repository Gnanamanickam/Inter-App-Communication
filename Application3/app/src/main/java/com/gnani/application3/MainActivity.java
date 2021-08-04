package com.gnani.application3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity implements seriesFragment.OnListItemSelectedListener {

    private final static String DANGEROUS_PERMISSION = "edu.uic.cs478.s19.kaboom";
    public static ArrayList<Integer> img = new ArrayList<>(
            Arrays.asList(R.drawable.friends,R.drawable.barbarian,R.drawable.behindhereyes,R.drawable.bigbangtheory,
                    R.drawable.blackmirror, R.drawable.breakingbad,R.drawable.dark,R.drawable.got,
                    R.drawable.lastkingdom,R.drawable.modernfamily,R.drawable.narcos,R.drawable.personofinterest,
                    R.drawable.prisonbreak,R.drawable.punisher,R.drawable.queensgambit,R.drawable.sherlock,R.drawable.thehundred,
                    R.drawable.theone,R.drawable.twoandhalfmen,R.drawable.umbrellaacademy,R.drawable.vikings,
                    R.drawable.whitecollar,R.drawable.witcher,R.drawable.twd,R.drawable.cobrakai));

    private imageFragment iFragment = new imageFragment();
    private seriesFragment lFragment = new seriesFragment();
    public static String[] series_name;
    public static String[] series_url;
    public static String[] series_images;
    private FrameLayout LayoutName;
    private FrameLayout LayoutImage;
    private int index = -1;
    private String url;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To display the support action bar .
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher_background);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
//        setSupportActionBar(toolbar);

        // To get the resources from values folder in res .
        series_name = getResources().getStringArray(R.array.series_name);
        series_url = getResources().getStringArray(R.array.series_url);
        series_images = getResources().getStringArray(R.array.series_image);
        LayoutName = (FrameLayout) findViewById(R.id.title);
        LayoutImage = (FrameLayout) findViewById(R.id.image);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("savedIndex");
            if (index != -1) {
                if (getSupportFragmentManager().getFragment(savedInstanceState,"iFragment") != null) {
                    iFragment = (imageFragment) getSupportFragmentManager().getFragment(savedInstanceState,"iFragment");
                    iFragment.showImage(index);
                    setLayout();
                }
            }
        }

        // To reset the layout on changes
        fragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.title, lFragment, "lFragment");
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();


    }

    // To create options menu from the options menu given in the menu folder .
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

// check which item is selected
        if(item.getTitle().equals("Launch applications A1 and A2")) {
            if (index != -1) {
                url = series_url[index];

//                Intent launchIntent = new Intent(Intent.ACTION_MAIN);
//                launchIntent.setClassName("com.gnani.application1", "com.gnani.application1.MainActivity");
//                finish();
//                //Check whether launch event is null . If not start the activity .
//                if (launchIntent != null) {
//                    startActivity(launchIntent);
//                }

                Intent app1 = new Intent("com.gnani.application3");
                app1.putExtra("url", url);
                sendOrderedBroadcast(app1, "edu.uic.cs478.s19.kaboom");
                finishAndRemoveTask();

            }
            else {
                Toast.makeText(MainActivity.this,"Select a Series",Toast.LENGTH_LONG).show();
            }
        }
        else if(item.getTitle().equals("Exit A3")) {
            Toast.makeText(MainActivity.this,"Closing the application",Toast.LENGTH_LONG).show();
            finishAndRemoveTask();
        }
        return super.onOptionsItemSelected(item);
    }


    public void setLayout(){
        // To get the Orientation
        int Orientation = getResources().getConfiguration().orientation;
        //Portrait
        if (Orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (!iFragment.isAdded()) {
                // To take the whole layout
                LayoutName.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
                LayoutImage.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 0f));
            } else {
                // To take the whole layout
                LayoutName.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 0f));
                LayoutImage.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
            }
        }
        //Landscape
        else {
            if (!iFragment.isAdded()) {
                // To take the whole layout
                LayoutName.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
                LayoutImage.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 0f));
            } else {
                // To take 1/3 of the image width
                LayoutName.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
                // To take 2/3 of the image width
                LayoutImage.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));

            }
        }

    }

    @Override
    public void onSeriesListItemSelected(int selected) {

            if (!iFragment.isAdded()) {
                // Start a new FragmentTransaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Adding it to the layout
                fragmentTransaction.add(R.id.image, iFragment);
                // Add the Fragment Transaction to the back stack
                fragmentTransaction.addToBackStack(null);
                // To commit the Fragment Transaction
                fragmentTransaction.commit();
                // To execute the committed FragmentTransaction
                fragmentManager.executePendingTransactions();
            }

            if (iFragment.getImage() != selected) {
                //To show the image based on position
                index = selected;
                url = series_url[index];
            }
        iFragment.showImage(index);
    }

    // Save currently shown image
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle) ;
        // save the current index in the bundle
        if (index != -1) {
            bundle.putInt("savedIndex", index) ;
        }
        else {
            bundle.putInt("savedIndex", -1 ) ;
        }
        // if there is an image selection, save the image fragment
        if (iFragment.isAdded()) {
            fragmentManager.putFragment(bundle, "iFragment", iFragment);
        }
        // save the title fragment
        fragmentManager.putFragment(bundle, "lFragment", lFragment);
    }

    // To return the series names.
    @Override
    public String[] getSeriesTitle() {
        return this.series_name;
    }

    //Set image in the start if image fragment is not null .
    public void onStart(){
        super.onStart() ;
        if (index >= 0) {
            if (iFragment != null) {
                iFragment.showImage(index);
            }
        }
    }

    //Set index value equal to -1 to prevent the app from crashing on orientation change after pressing back button .
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        index = -1;
    }
}