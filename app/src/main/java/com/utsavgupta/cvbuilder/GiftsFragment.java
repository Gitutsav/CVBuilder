package com.utsavgupta.cvbuilder;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.utsavgupta.cvbuilder.LogUtils.LOGE;
import static com.utsavgupta.cvbuilder.PermissionsActivity.PERMISSION_REQUEST_CODE;
import static com.utsavgupta.cvbuilder.PermissionsChecker.REQUIRED_PERMISSION;


public class GiftsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    Context mContext;
    ImageButton back;
    PermissionsChecker checker;
    Bitmap screen;
    private LinearLayout rlview;
    private RecyclerView recyclerview;
    List<String> name=new ArrayList<>(),timestamp=new ArrayList<>(),
            attend_id=new ArrayList<>(),type=new ArrayList<>();
    private User_details sbmit_details;
    BlockPruebaAdapter adapter;
    Cursor submit;
    private LinearLayout no_record;
    private SwipeRefreshLayout swipeRefreshLayout;
    private InterstitialAd mInterstitialAd;
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton;
    private TextView mLevelTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        View view= inflater.inflate(R.layout.fragment_gifts, container, false);
        MobileAds.initialize(getContext(), "ca-app-pub-2009435266295761~1912784762");
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) view.findViewById(R.id.adView);
        // adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        // Create the text view to show the level number.
        //mLevelTextView = (TextView) findViewById(R.id.level);
        //mLevel = START_LEVEL;

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        recyclerview = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.rvPrueba);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.simpleSwipeRefreshLayout);
        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(llm);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
        sbmit_details=new User_details(mContext);
        submit=sbmit_details.cv_list_data();
        name.clear();timestamp.clear();attend_id.clear();type.clear();
        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
                Fragment   fragment = new StoreFragment();
                loadFragment(fragment);
            }
        });
        while (submit.moveToNext()) {
            type.add(submit.getString(5));
            name.add(submit.getString(3));
            timestamp.add(submit.getString(2));
            attend_id.add(String.valueOf(submit.getInt(1)));
        }
        Collections.reverse(name);
        Collections.reverse(timestamp);

        Collections.reverse(attend_id);
        Collections.reverse(type);

        //  adapter.notifyDataSetChanged();
        adapter = new BlockPruebaAdapter(name,timestamp,attend_id,type);
        no_record=view.findViewById(R.id.no_record);
        if(adapter.getItemCount()==0){
            no_record.setVisibility(View.VISIBLE);
        }
        else{
            no_record.setVisibility(View.GONE);
            recyclerview.setAdapter(adapter);}
        checker = new PermissionsChecker(getContext());

        //createPdf(FileUtils.getAppPath(mContext) + "1234.pdf");

        /**
         *
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                sbmit_details=new User_details(mContext);
                submit=sbmit_details.cv_list_data();
                name.clear();timestamp.clear();attend_id.clear();type.clear();
                while (submit.moveToNext()) {
                    type.add(submit.getString(5));
                    name.add(submit.getString(3));
                    timestamp.add(submit.getString(2));
                    attend_id.add(String.valueOf(submit.getInt(1)));
                }
                Collections.reverse(name);
                Collections.reverse(timestamp);
                Collections.reverse(type);
                Collections.reverse(attend_id);
                adapter = new BlockPruebaAdapter(name,timestamp,attend_id,type);
                if(adapter.getItemCount()==0){
                    no_record.setVisibility(View.VISIBLE);
                }
                else{
                    no_record.setVisibility(View.GONE);
                    recyclerview.setAdapter(adapter);}
                swipeRefreshLayout.setRefreshing(false);

            }
        });
       /* mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
            }
        });*/
        TextView fab = (TextView) view.findViewById(R.id.gif);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"fab", Toast.LENGTH_SHORT).show();
                if (checker.lacksPermissions(REQUIRED_PERMISSION)) {
                    Toast.makeText(getContext(),"fab if", Toast.LENGTH_SHORT).show();
                    PermissionsActivity.startActivityForResult(getActivity(), PERMISSION_REQUEST_CODE, REQUIRED_PERMISSION);
                } else {
                    Toast.makeText(getContext(),"fab else", Toast.LENGTH_SHORT).show();
                    createPdf(FileUtils.getAppPath(mContext) + "faded.pdf");
                }
            }
        });
      /*  rlview = view.findViewById(R.id.llllgif);

        rlview.setDrawingCacheEnabled(true);
        // this is the important code :)
        // Without it the view will have a dimension of 0,0 and the bitmap will be null

        rlview.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        rlview.layout(0, 0, rlview.getMeasuredWidth(), rlview.getMeasuredHeight());

        rlview.buildDrawingCache(true);
        screen = Bitmap.createBitmap(rlview.getDrawingCache());
        rlview.setDrawingCacheEnabled(false);*/
        return view;
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
           document.setPageSize(PageSize.A3);
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
           /* Drawable d = getResources().getDrawable(R.drawable.ic_launcher_background);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();*/
            /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
            screen.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.setRotation(200);
            image.setAbsolutePosition(100f,150f);
            document.add(image);*/
          /*  Image img = Image.getInstance(R.drawable.action_responce);
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
    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        /*LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
        mImage.setLayoutParams(layoutParams);*/
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            Toast.makeText(mContext, "Permission Granted to Save", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Permission not granted, Try again!", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onRefresh() {

    }
    public void loadFragment(Fragment fragment) {
        // load fragment
      /*  if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 4) {
           getActivity().getSupportFragmentManager().popBackStackImmediate();
        }*/

        int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
     //   Toast.makeText(getActivity().getApplicationContext(),count+"Added",Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
     /*   if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 4) {
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }*/
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(getContext());
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
}
