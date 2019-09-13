package com.sutapa.neworiyanewspaper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<NewsItemClass> mNewsList;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsSourceList();
        recyclerViewConfig();
        showAbout();
        rateApp();
        shareApp();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("TAG","Ad loading success");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.d("TAG","Ad loading Failed "+errorCode);

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });


    }

    private void newsSourceList() {
        mNewsList = new ArrayList<>();
        mNewsList.add(new NewsItemClass(R.drawable.sambad, "http://sambadepaper.com/", "http://sambad.in/ "));
        mNewsList.add(new NewsItemClass(R.drawable.dharitri, "http://www.dharitri.com/e-Paper/Bhubaneswar/", "http://www.dharitri.com/ "));
        mNewsList.add(new NewsItemClass(R.drawable.samaja, "http://www.samajaepaper.in/", "http://www.samajalive.in/"));
        mNewsList.add(new NewsItemClass(R.drawable.samaya, "http://www.samayaepaper.com/", "http://odishasamaya.com/"));
        mNewsList.add(new NewsItemClass(R.drawable.prameya, "http://epaper.prameyanews.com/", ""));
        mNewsList.add(new NewsItemClass(R.drawable.pragatibadi, "http://pragativadi.com/epaper/", "http://pragativadi.com/ "));
        mNewsList.add(new NewsItemClass(R.drawable.otv, "", "http://odishatv.in/"));
        mNewsList.add(new NewsItemClass(R.drawable.odisha_samachar, "", "http://www.eodishasamachar.com/"));
        mNewsList.add(new NewsItemClass(R.drawable.odia_pua, "", "http://odiapua.com/"));
        mNewsList.add(new NewsItemClass(R.drawable.dinalipi, "https://dinalipiepaper.com/", "http://www.dinalipi.com/"));
        mNewsList.add(new NewsItemClass(R.drawable.bhaskar, "http://odishabhaskarepaper.com/", "http://www.odishabhaskar.com/"));
        mNewsList.add(new NewsItemClass(R.drawable.anupam_bharat, "http://epaper.anupambharatonline.com/", "http://www.anupambharatonline.com/"));

    }

    private void recyclerViewConfig() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new NewsAdapter(mNewsList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);


    }

    private void showAbout() {

        TextView aboutTextView = findViewById(R.id.about);
        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog.Builder builder1 = builder.setMessage(getString(R.string.about));
                builder.setTitle("About Us");
                builder.setCancelable(true);

                builder.setPositiveButton("Share App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // finish();
                        shareAppIntent();
                    }
                });

                builder.setNegativeButton("Rate App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        rateAppIntent();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });
    }


    private void rateApp() {

        TextView rateAppTextView = findViewById(R.id.rateApp);
        rateAppTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateAppIntent();
            }
        });

    }

    private void rateAppIntent() {
        final String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(appUrl));
        //  intent.setPackage("com.android.vending");
        startActivity(intent);
    }

    private void shareApp() {
        TextView shareAppTextView = findViewById(R.id.shareApp);
        shareAppTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareAppIntent();
            }
        });
    }

    private void shareAppIntent() {
        final String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, appUrl + " Enjoy the best app for Odia News, Install Now");
        //  intent.putExtra(Intent.EXTRA_TEXT,"Enjoy the best app for Odia News, Install Now");

        try {
            startActivity(Intent.createChooser(intent, "Select an action"));
        } catch (android.content.ActivityNotFoundException ex) {
            // (handle error)
        }
    }

    @Override
    public void onBackPressed() {

        if (mInterstitialAd.isLoaded()) {

            mInterstitialAd.show();
        }
        backPressDialog();
      //  super.onBackPressed();
    }


    private void backPressDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog.Builder builder1 = builder.setMessage("ବନ୍ଦ କରିବାକୁ ଚାହୁଁଛନ୍ତି କି ?");
        builder.setTitle("ବନ୍ଦ କରନ୍ତୁ ?");
        builder.setCancelable(true);

        builder.setPositiveButton("ହଁ , ବନ୍ଦ କର", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // finish();
                finish();
            }
        });

        builder.setNegativeButton("ନାଁ , ରୁହନ୍ତୁ ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
