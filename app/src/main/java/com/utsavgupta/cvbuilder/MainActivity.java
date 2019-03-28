package com.utsavgupta.cvbuilder;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import static com.utsavgupta.cvbuilder.LogUtils.LOGE;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
             private ActionBar toolbar;
    Context mContext;int i=0;
             private static final String BACK_STACK_ROOT_TAG = "root_fragment";

             private AHBottomNavigation nav;
             BottomNavigationView navigation;
SharedPreferences loginData1;
SharedPreferences.Editor editor1;
    PermissionsChecker checker;
             private int j=0;
             private InterstitialAd mInterstitialAd;
             //  private int count=4;

             @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        loginData1 = getSharedPreferences("fragment_name", Context.MODE_PRIVATE);
        editor1 = loginData1.edit();
       navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                 mInterstitialAd = newInterstitialAd();
                 loadInterstitial();
        // attaching bottom sheet behaviour - hide / show on scroll
       CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        //toolbar.setTitle("Shop");
        loadFragment(new StoreFragment());

        //  startActivity(new Intent(MainActivity.this,EnterDetails.class));
        mContext = getApplicationContext();

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);

        checker = new PermissionsChecker(this);

        //createPdf(FileUtils.getAppPath(mContext) + "1234.pdf");

        /**
         *
         */
        //onBackPressed();

        }
             private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                     = new BottomNavigationView.OnNavigationItemSelectedListener() {


                 @Override
                 public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                     Fragment fragment;
                     switch (item.getItemId()) {
                         case R.id.navigation_shop:
                           //  toolbar.setTitle("Shop");

                             editor1.putString("name_main", "Sample");
                             editor1.putString("view_status","no_view");
                             editor1.apply();
                             fragment = new StoreFragment();

                            // onBackPressed();
                             loadFragment(fragment);
                             //addFragmentOnTop(fragment);
                             return true;
                         case R.id.navigation_gifts:
                             //toolbar.setTitle("My Gifts");

                             editor1.putString("name_main", "Sample");
                             editor1.putString("view_status","no_view");
                             editor1.apply();
                             fragment = new GiftsFragment();
                            // onBackPressed();
                             loadFragment(fragment);
                             return true;
                         case R.id.navigation_cart:
                          //   toolbar.setTitle("Cart");
                            showInterstitial();
                             editor1.putString("name_main", "Sample");
                             editor1.putString("view_status","no_view");

                             editor1.apply();
                             fragment = new CartFragment();
                            // onBackPressed();
                             loadFragment(fragment);
                             return true;
                         case R.id.navigation_profile:
                             //toolbar.setTitle("Profile");
                             fragment = new ProfileFragment();
                             showInterstitial();
                             editor1.putString("name_main", "Sample");
                             editor1.putString("view_status","no_view");
                             editor1.putString("create","yes");
                             editor1.putString("viewas","same");
                             editor1.apply();
                             SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                             SharedPreferences.Editor editor = loginData.edit();
                             editor.putString("attend_idd", "default");
                             editor.apply();
                            // onBackPressed();
                             loadFragment(fragment);
                             return true;
                     }

                     return false;
                 }
             };


             private void loadFragment(Fragment fragment) {
               //  FragmentManager fragmentManager = getSupportFragmentManager();
                // fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                 // load fragment

                 if (getSupportFragmentManager().getBackStackEntryCount() > 3) {
                     getSupportFragmentManager().popBackStackImmediate();
                 }
                 FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                 transaction.replace(R.id.frame_container, fragment);
                // addFragmentOnTop(fragment);
                 transaction.addToBackStack(null);
                 transaction.commit();
             }


    public void createPdf(String dest) {

        if (new File(dest).exists()) {
            new File(dest).delete();
        }

        try {
            /**
             * Creating Document
             */
            Document document = new Document();

            // Location to save
            PdfWriter.getInstance(document, new FileOutputStream(dest));

            // Open to write
            document.open();

            // Document Settings
            document.setPageSize(PageSize.A4);
            document.addCreationDate();
            document.addAuthor("Android School");
            document.addCreator("Pratik Butani");

            /***
             * Variables for further use....
             */
            BaseColor mColorAccent = new BaseColor(0, 153, 204, 255);
            float mHeadingFontSize = 20.0f;
            float mValueFontSize = 26.0f;

            /**
             * How to USE FONT....
             */
            BaseFont urName = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);

            // LINE SEPARATOR
            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setLineColor(new BaseColor(0, 0, 0, 68));

            // Title Order Details...
            // Adding Title....
            Font mOrderDetailsTitleFont = new Font(urName, 36.0f, Font.NORMAL, BaseColor.BLACK);
            Chunk mOrderDetailsTitleChunk = new Chunk("Order Details", mOrderDetailsTitleFont);
            Paragraph mOrderDetailsTitleParagraph = new Paragraph(mOrderDetailsTitleChunk);
            mOrderDetailsTitleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(mOrderDetailsTitleParagraph);

            // Fields of Order Details...
            // Adding Chunks for Title and value
            Font mOrderIdFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
            Chunk mOrderIdChunk = new Chunk("Order No:", mOrderIdFont);
            Paragraph mOrderIdParagraph = new Paragraph(mOrderIdChunk);
            document.add(mOrderIdParagraph);

            Font mOrderIdValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
            Chunk mOrderIdValueChunk = new Chunk("#123123", mOrderIdValueFont);
            Paragraph mOrderIdValueParagraph = new Paragraph(mOrderIdValueChunk);




            document.add(mOrderIdValueParagraph);
            Drawable d = getResources().getDrawable(R.drawable.action_responce);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.setRotation(200);
            image.setAbsolutePosition(100f,150f);
            document.add(image);
           /* Image img = Image.getInstance(R.drawable.action_responce);
            document.add(new Paragraph("Sample 1: This is simple image demo."));
            document.add(img);*/
            // Adding Line Breakable Space....
            document.add(new Paragraph(""));
            // Adding Horizontal Line...
            document.add(new Chunk(lineSeparator));
            // Adding Line Breakable Space....
            document.add(new Paragraph(""));

            // Fields of Order Details...
            Font mOrderDateFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
            Chunk mOrderDateChunk = new Chunk("Order Date:", mOrderDateFont);
            Paragraph mOrderDateParagraph = new Paragraph(mOrderDateChunk);
            document.add(mOrderDateParagraph);

            Font mOrderDateValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
            Chunk mOrderDateValueChunk = new Chunk("06/07/2017", mOrderDateValueFont);
            Paragraph mOrderDateValueParagraph = new Paragraph(mOrderDateValueChunk);
            document.add(mOrderDateValueParagraph);

            document.add(new Paragraph(""));
            document.add(new Chunk(lineSeparator));
            document.add(new Paragraph(""));

            // Fields of Order Details...
            Font mOrderAcNameFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
            Chunk mOrderAcNameChunk = new Chunk("Account Name:", mOrderAcNameFont);
            Paragraph mOrderAcNameParagraph = new Paragraph(mOrderAcNameChunk);
            document.add(mOrderAcNameParagraph);

            Font mOrderAcNameValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
            Chunk mOrderAcNameValueChunk = new Chunk("Pratik Butani", mOrderAcNameValueFont);
            Paragraph mOrderAcNameValueParagraph = new Paragraph(mOrderAcNameValueChunk);
            document.add(mOrderAcNameValueParagraph);

            document.add(new Paragraph(""));
            document.add(new Chunk(lineSeparator));
            document.add(new Paragraph(""));

            document.close();

            Toast.makeText(mContext, "Created... :)", Toast.LENGTH_SHORT).show();

            FileUtils.openFile(mContext, new File(dest));

        } catch (IOException | DocumentException ie) {
            LOGE("createPdf: Error " + ie.getLocalizedMessage());
        } catch (ActivityNotFoundException ae) {
            Toast.makeText(mContext, "No application found to open this file.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            Toast.makeText(mContext, "Permission Granted to Save", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Permission not granted, Try again!", Toast.LENGTH_SHORT).show();
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
       /*     public void addFragmentOnTop(Fragment fragment) {
                 getSupportFragmentManager()
                         .beginTransaction()
                         .replace(R.id.container, fragment)
                         .addToBackStack(null)
                         .commit();
             }

             public void onBackPressed() {
                 FragmentManager fragments = getSupportFragmentManager();
                 Fragment homeFrag = new StoreFragment();
                 Toast.makeText(getApplicationContext(),fragments.getBackStackEntryCount(),Toast.LENGTH_SHORT).show();

                 if (fragments.getBackStackEntryCount() > 1) {
                     // We have fragments on the backstack that are poppable
                     fragments.popBackStackImmediate();
                 } else if (homeFrag == null || !homeFrag.isVisible()) {
                     // We aren't showing the home screen, so that is the next stop on the back journey
                     navigation.setSelectedItemId(R.id.navigation_shop);
                 } else if(homeFrag.isVisible()){
                     // We are already showing the home screen, so the next stop is out of the app.
                     supportFinishAfterTransition();
                 }
             }*/


          /*   @Override
             public void onTabSelected(int position, boolean wasSelected) {
                 // Pop off everything up to and including the current tab
                 FragmentManager fragmentManager = getSupportFragmentManager();
                 fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                 // Add the new tab fragment
                 fragmentManager.beginTransaction()
                         .replace(R.id.container, TabFragment.newInstance(position + 1), String.valueOf(position))
                         .addToBackStack(BACK_STACK_ROOT_TAG)
                         .commit();
             }*/
         @Override
          public void onBackPressed() {

              FragmentManager fragmentManager = getSupportFragmentManager();
             // Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
              int count = fragmentManager.getBackStackEntryCount();
            //  if(getSupportFragmentManager().getFragment(n))

             // count=count-1;
        /*     if(count==1)
             {
                 finish();
                 //super.onBackPressed();

                 Toast.makeText(getApplicationContext(),count+"Added",Toast.LENGTH_SHORT).show();
                 return;

             }*/
             if(count>3) {
                 //if()
                 if (count == (count - 3 + i)) {
                     finish();
                     //super.onBackPressed();

                   //  Toast.makeText(getApplicationContext(), count + "Added", Toast.LENGTH_SHORT).show();
                     return;

                     //additional code
                 } else {// --count;
                     ++i;
                     fragmentManager.popBackStack();
                    // Toast.makeText(getApplicationContext(), count + "Popped out", Toast.LENGTH_SHORT).show();
                 }
             }
           /*  else if(count>=4 && count <8){

                 if (count == (count - 4 + j)) {
                     finish();
                     //super.onBackPressed();

                     Toast.makeText(getApplicationContext(), count + "Added", Toast.LENGTH_SHORT).show();
                     return;

                     //additional code
                 } else {// --count;
                     ++j;
                     fragmentManager.popBackStack();
                     Toast.makeText(getApplicationContext(), count + "Popped out", Toast.LENGTH_SHORT).show();
                 }
            }*/
             else {
                 if (count == 1) {
                     finish();
                     //super.onBackPressed();

                  //   Toast.makeText(getApplicationContext(),count+"Added",Toast.LENGTH_SHORT).show();
                     return;

                     //additional code
                 }

                 else
                 {// --count;
                     //++i;
                     fragmentManager.popBackStack();
                     //Toast.makeText(getApplicationContext(),count+"Popped out",Toast.LENGTH_SHORT).show();
                 }
             }

          }
             private InterstitialAd newInterstitialAd() {
                 InterstitialAd interstitialAd = new InterstitialAd(getApplicationContext());
                 interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
                 interstitialAd.setAdListener(new AdListener() {
                     @Override
                     public void onAdLoaded() {
                         ///mNextLevelButton.setEnabled(true);
                     }

                     @Override
                     public void onAdFailedToLoad(int errorCode) {
                         //  mNextLevelButton.setEnabled(true);
                     }

                     @Override
                     public void onAdClosed() {
                         // Proceed to the next level.
                         goToNextLevel();
                     }
                 });
                 return interstitialAd;
             }

             private void showInterstitial() {
                 // Show the ad if it's ready. Otherwise toast and reload the ad.
                 if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                     mInterstitialAd.show();
                 } else {
                     // Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
                     goToNextLevel();
                 }
             }

             private void loadInterstitial() {
                 // Disable the next level button and load the ad.
                 //   mNextLevelButton.setEnabled(false);
                 AdRequest adRequest = new AdRequest.Builder()
                         .setRequestAgent("android_studio:ad_template").build();
                 mInterstitialAd.loadAd(adRequest);
             }

             private void goToNextLevel() {
                 // Show the next level and reload the ad to prepare for the level after.
                 // mLevelTextView.setText("Level " + (++mLevel));
                 mInterstitialAd = newInterstitialAd();
                 loadInterstitial();
             }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
