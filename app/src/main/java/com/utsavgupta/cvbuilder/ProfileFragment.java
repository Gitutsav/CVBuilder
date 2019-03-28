package com.utsavgupta.cvbuilder;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment  implements RewardedVideoAdListener {
    private static final String AD_UNIT_ID = "ca-app-pub-2009435266295761/1742108386";
    private static final String APP_ID =     "ca-app-pub-3940256099942544~3347511713";
    private static final long COUNTER_TIME =3;
    private static final int GAME_OVER_REWARD = 1;
    private int coinCount;
    private TextView coinCountText;
    private CountDownTimer countDownTimer;
    private boolean gameOver;
    private boolean gamePaused;
    private RewardedVideoAd rewardedVideoAd;
    private Button retryButton;
    private TextView showVideoButton;
    private long timeRemaining;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    private InterstitialAd mInterstitialAd;
    boolean boolean_save;
    LinearLayout pg_l,add_l,i_main,p_main,j_main,i2_l,j2_l,p2_l;
    User_details ud;int flag;ScrollView sc;
    int[] covers = new int[]{
            R.drawable.resume00,
            R.drawable.resume11,
            R.drawable.resume22,
            R.drawable.resume33,
            R.drawable.resume44,
            R.drawable.resume55,
            R.drawable.resume77
    };
   String[] img_name={"Sample ","Default ","Classic ","Impressive ","Impressive Pro ","Impressive Pro1 ","Modern "};
    Cursor personal, j1, j2, p1, p2, i1, i2, pg, ug, x, xii, skills, additional, sequence, image_cursor,is_saved,status,cv_list_data;
    Bitmap profpic;ImageButton save;
    List<String> skil_set=new ArrayList<>();
    TextView nam, mobi, loc,fragname,email_n,linked_n,ls_intern,ls_job,ls_project,ls_add,
            deg_pg, clg_pg, yoc_pg, cgpa_pg,
            deg_ug, clg_ug, yoc_ug, cgpa_ug,
            deg_xii, board1_xii, yoc_xii, cgpa1_xii,
            deg_x, board1_x, yoc_x, cgpa1_x,
            profil_i1, org_i1, period_i1, desc_i1,
            profil_i2, org_i2, period_i2, desc_i2,
            profil_j1, org_j1, period_j1, desc_j1,
            profil_j2, org_j2, period_j2, desc_j2,
            title1_p1, link_p1, period_p1, desc_p1,
            title1_p2, link_p2, period_p2, desc_p2,
            desc_add;
    ImageView iv,view_cv; int attend_id,attend_id_o;ImageButton back;Button create;
    int java,c,cpp,html,css,javascript,jquery,python,android,unity,coral,r,ml,ai,data;
    String pg_s="1",add_s="1",i_s="11",p_s="11",j_s="11";
   String name = "";
    String mob = "";
    String c_city = "";
    String s_city = "";
    String email="";
    String linkedin="";
    String image_prof ;
    String college_grad = "";
    String syear_grad = "";
    String eyear_grad = "";
    String degree_grad = "";
    String stream_grad = "";
    String cgpa_grad = "";
    String college_pgrad = "";
    String syear_pgrad = "";
    String eyear_pgrad = "";
    String degree_pgrad = "";
    String stream_pgrad = "";
    String cgpa_pgrad = "";
    String school_xii = "";
    String eyear_xii = "";
    String board_xii = "";
    String stream_xii = "";
    String cgpa_xii = "";
    String school_x = "";
    String eyear_x = "";
    String board_x = "";
    String stream_x = "";
    String cgpa_x = "";
    String profile_j1 = "";
    String organisation_j1 = "";
    String edate_j1 = "";
    String sdate_j1 = "";
    String location_j1 = "";
    String description_j1 = "";
    String profile_j2 = "";
    String organisation_j2 = "";
    String edate_j2 = "";
    String sdate_j2 = "";
    String location_j2 = "";
    String description_j2 = "";
    String profile_i1 = "";
    String organisation_i1 = "";
    String edate_i1 = "";
    String sdate_i1 = "";
    String location_i1 = "";
    String description_i1 = "";
    String profile_i2 = "";
    String organisation_i2 = "";
    String edate_i2 = "";
    String sdate_i2 = "";
    String location_i2 = "";
    String description_i2 = "";
    String title_p1 = "";
    String emonth_p1 = "";
    String smonth_p1 = "";
    String projectlink_p1 = "";
    String description_p1 = "";
    String title_p2 = "";
    String emonth_p2 = "";
    String smonth_p2 = "";
    String projectlink_p2 = "";
    String description_p2 = "";
    String description_add = "";
    private String datewt;
    String cvname="Abcd";
    private String attend_id_sp;
    private String fragmentname;
    private String view_status,create_status;
    View view;
    private Bitmap bitmap;
    private SharedPreferences loginData;
    private SharedPreferences.Editor editor;
    private String viewas;
    private int encryted_attednd_id=0;
    private Cursor sequence_o;
    TextView textViewretry;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile , container, false);
        // Inflate the layout for this fragment
        //fn_permission();
        final SharedPreferences prefs1 = getActivity().getSharedPreferences("fragment_name", MODE_PRIVATE);
        fragmentname = prefs1.getString("name_main", "No name defined");//"No name defined" is the default value.
        view_status=prefs1.getString("view_status","no name");
        viewas=prefs1.getString("viewas","no name");
        create_status=prefs1.getString("create","no_name");
        if(view_status.equals("Default ")){
            view=inflater.inflate(R.layout.rdefault,container,false);
        }
        else if(view_status.equals("Classic ")){
            view=inflater.inflate(R.layout.classic,container,false);
        }
        else if(view_status.equals("Sample ") ){
             view = inflater.inflate(R.layout.fragment_profile , container, false);
        }
        else if( view_status.equals("no_view"))
        {
            view = inflater.inflate(R.layout.fragment_profile , container, false);
        }
        else  if(view_status.equals("Impressive Pro "))
        {
            view = inflater.inflate(R.layout.impressive_pro , container, false);
        }
        else  if(view_status.equals("Impressive "))
        {
            view = inflater.inflate(R.layout.impressive , container, false);
        }
        else  if(view_status.equals("Impressive Pro1 "))
        {
            view = inflater.inflate(R.layout.impressive_pro_one , container, false);
        }
        else  if(view_status.equals("Modern "))
        {
            view = inflater.inflate(R.layout.modern , container, false);
        }
        textViewretry = view.findViewById(R.id.timer);
        MobileAds.initialize(getContext(), "ca-app-pub-2009435266295761~1912784762");
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) view.findViewById(R.id.adView);
       // adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getContext());
        rewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        // Create the "retry" button, which tries to show an interstitial between game plays.
        retryButton = view.findViewById(R.id.retry_button);
        retryButton.setVisibility(View.GONE);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        // Create the "show" button, which shows a rewarded video if one is loaded.
        showVideoButton = view.findViewById(R.id.show_video_button);
        // showVideoButton.setVisibility(View.GONE);
        showVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRewardedVideo();
            }
        });

        // Display current coin count to user.
        coinCountText = view.findViewById(R.id.coin_count_text);
        coinCount = 0;
        coinCountText.setText("Coins: " + coinCount);

        startGame();

        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
  /*      if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }

        }*/
        back=view.findViewById(R.id.back);
        sc=view.findViewById(R.id.scrol_profile);
        view_cv=view.findViewById(R.id.view_cv);
        /*try {
            view = inflater.inflate(R.layout.fragment_profile, container, false);
        } catch (InflateException e) {

        }*/

  /*      if(view_status.equals("view")){
            sc.setVisibility(View.GONE);
            view_cv.setVisibility(View.VISIBLE);
        for(int i=0;i<img_name.length;i++)
        {
            if(fragmentname.equals(img_name[i])){
                //Toast.makeText(getContext(),fragmentname+"  "+img_name[i],Toast.LENGTH_LONG).show();
                view_cv.setImageResource(covers[i]);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Drawable imag=getActivity().getResources().getDrawable(R.drawable.view_cv,getActivity().getTheme());
                        if(i!=0) {
                            view_cv.setBackground(imag);
                        }
                    }

                }
            }
            else {

            }

        }
            }*/
        //loadImageFromStorage();
        loginData = getContext().getSharedPreferences("fragment_name", Context.MODE_PRIVATE);
        editor = loginData.edit();
        create=(Button) view.findViewById(R.id.create);
        if(create_status.equals("no")){create.setVisibility(View.GONE);}
        else if(create_status.equals("yes")){create.setVisibility(View.VISIBLE);}
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CartFragment();
                showInterstitial();
                loadFragment(fragment);
                editor.putString("name_main", fragmentname);
                editor.putString("view_status",fragmentname);
                editor.apply();
            }
        });
         back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          showInterstitial();
          Fragment   fragment = new StoreFragment();
          String frag=prefs1.getString("for_profile_fragment_transaction","abcd");
          if(frag.equals("store")){
              fragment = new StoreFragment();
          }
          else if(frag.equals("gift")){
              fragment = new GiftsFragment();
          }
          
          loadFragment(fragment);
      }
  });
        SharedPreferences prefs = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        attend_id_sp = prefs.getString("attend_idd", "No name defined");//"No name defined" is the default value.
        nam = view.findViewById(R.id.name);
        mobi = view.findViewById(R.id.mob);
        loc = view.findViewById(R.id.city);
        email_n=view.findViewById(R.id.emaill);
        linked_n=view.findViewById(R.id.linkedinn);
        deg_pg = view.findViewById(R.id.degree_pg);
        clg_pg = view.findViewById(R.id.clg_pg);
        yoc_pg = view.findViewById(R.id.yoc_pg);
        cgpa_pg = view.findViewById(R.id.cgpa_pg);
        deg_ug = view.findViewById(R.id.degree_ug);
        clg_ug = view.findViewById(R.id.clg_ug);
        yoc_ug = view.findViewById(R.id.yoc_ug);
        cgpa_ug = view.findViewById(R.id.cgpa_ug);
        deg_x = view.findViewById(R.id.x);
        board1_x = view.findViewById(R.id.board1_x);
        yoc_x = view.findViewById(R.id.yoc_x);
        cgpa1_x = view.findViewById(R.id.cgpa1_x);
        deg_xii = view.findViewById(R.id.xii);
        board1_xii = view.findViewById(R.id.board1_xii);
        yoc_xii = view.findViewById(R.id.yoc_xii);
        cgpa1_xii = view.findViewById(R.id.cgpa1_xii);
        profil_i1 = view.findViewById(R.id.profile_i1);
        org_i1 = view.findViewById(R.id.org_i1);
        period_i1 = view.findViewById(R.id.period_i1);
        desc_i1 = view.findViewById(R.id.desc_i1);
        profil_i2 = view.findViewById(R.id.profile_i2);
        org_i2 = view.findViewById(R.id.org_i2);
        period_i2 = view.findViewById(R.id.period_i2);
        desc_i2 = view.findViewById(R.id.desc_i2);
        profil_j2 = view.findViewById(R.id.profile_j2);
        org_j2 = view.findViewById(R.id.org_j2);
        period_j2 = view.findViewById(R.id.period_j2);
        desc_j2 = view.findViewById(R.id.desc_j2);
        profil_j1 = view.findViewById(R.id.profile_j1);
        org_j1 = view.findViewById(R.id.org_j1);
        period_j1 = view.findViewById(R.id.period_j1);
        desc_j1 = view.findViewById(R.id.desc_j1);
        title1_p1 = view.findViewById(R.id.title_p1);
        link_p1 = view.findViewById(R.id.link_p1);
        period_p1 = view.findViewById(R.id.period_p1);
        desc_p1 = view.findViewById(R.id.desc_p1);
        title1_p2 = view.findViewById(R.id.title_p2);
        link_p2 = view.findViewById(R.id.link_p2);
        period_p2 = view.findViewById(R.id.period_p2);
        desc_p2 = view.findViewById(R.id.desc_p2);
        desc_add = view.findViewById(R.id.desc_add);
        iv = view.findViewById(R.id.imageView1);
        save = view.findViewById(R.id.save);
        pg_l=view.findViewById(R.id.pg_l);
        add_l=view.findViewById(R.id.a_main);
        i_main=view.findViewById(R.id.i_main);
        j_main=(LinearLayout) view.findViewById(R.id.j_main);
        p_main=view.findViewById(R.id.p_main);
        i2_l=view.findViewById(R.id.i2_l);
        j2_l=view.findViewById(R.id.j2_l);
        p2_l=view.findViewById(R.id.p2_l);
        ls_add=view.findViewById(R.id.ls_addition);
        ls_intern=view.findViewById(R.id.ls_intern);
        ls_job=view.findViewById(R.id.ls_job);
        ls_project=view.findViewById(R.id.ls_project);
        fragname=view.findViewById(R.id.textView5);
        if(viewas.equals("same")){
            if(fragmentname.length()>20){
                String  fragmentname1=fragmentname.substring(0,20);
                fragname.setText(fragmentname1);

            }
            else{
                fragname.setText(fragmentname);
            }

        }
        else if(viewas.equals("other")){
            if(fragmentname.length()>20){
              String  fragmentname1=fragmentname.substring(0,20);
              fragname.setText(fragmentname1);

            }
            else{
                fragname.setText(view_status+fragmentname);
            }
        }
        SimpleDateFormat datew = new SimpleDateFormat("dd MMM,yyyy HH:mm");
        datewt =datew.format(new Date());
        ud = new User_details(getContext());



        if(attend_id_sp.equals("last")) {
            sequence = ud.sequence_data();
            sequence.moveToLast();
            attend_id = sequence.getInt(0);
        }
        else if(attend_id_sp.equals("default")){
          //  Toast.makeText(getContext(),
            //        "Default Sample", Toast.LENGTH_SHORT).show();
            save.setVisibility(View.GONE);
        }
        else{
            attend_id=Integer.parseInt(attend_id_sp);
            }

        is_saved=ud.cv_data(attend_id);

        while (is_saved.moveToNext()){
            flag= Integer.parseInt(is_saved.getString(4));
        }

        if(!attend_id_sp.equals("default")) {
            skills = ud.skills_data(attend_id);
            while (skills.moveToNext()) {
                java = skills.getInt(1);
                if (java == 1) {
                    skil_set.add("Java");
                }
                c = skills.getInt(2);
                if (c == 1) {
                    skil_set.add("C");
                }
                cpp = skills.getInt(3);
                if (cpp == 1) {
                    skil_set.add("CPP");
                }
                html = skills.getInt(4);
                if (html == 1) {
                    skil_set.add("HTML");
                }
                css = skills.getInt(5);
                if (css == 1) {
                    skil_set.add("CSS");
                }
                javascript = skills.getInt(6);
                if (javascript == 1) {
                    skil_set.add("JavaScript");
                }
                jquery = skills.getInt(7);
                if (jquery == 1) {
                    skil_set.add("jQuery");
                }
                python = skills.getInt(8);
                if (python == 1) {
                    skil_set.add("Python");
                }
                android = skills.getInt(9);
                if (android == 1) {
                    skil_set.add("Android");
                }
                unity = skills.getInt(10);
                if (unity == 1) {
                    skil_set.add("Unity");
                }
                coral = skills.getInt(11);
                if (coral == 1) {
                    skil_set.add("Coral");
                }
                r = skills.getInt(12);
                if (r == 1) {
                    skil_set.add("R");
                }
                ml = skills.getInt(13);
                if (ml == 1) {
                    skil_set.add("ML");
                }
                ai = skills.getInt(14);
                if (ai == 1) {
                    skil_set.add("AI");
                }
                data = skills.getInt(15);
                if (data == 1) {
                    skil_set.add("DataScience");
                }
            }
            for (int i = 0; i < skil_set.size(); i++) {
                String name = "s" + (i + 1);
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                if (id != 0) {
                    TextView textView = (TextView) view.findViewById(id);
                    if (textView.getVisibility() == View.GONE) {
                        textView.setVisibility(View.VISIBLE);
                    }
                    textView.setText(skil_set.get(i));
                }
            }
            //   int attend_id=2;
          /*  image_cursor = ud.profile_image_data(attend_id);

            while (image_cursor.moveToNext()) {
                image_prof=image_cursor.getString(1);
            }
            profpic = decodeBase64(image_prof);*/

            //Glide.with(this).load(profpic).into(iv);
            //   iv.setImageBitmap(profpic);
            status=ud.status_list_data(attend_id);
            while (status.moveToNext()){
                pg_s=status.getString(1);
                add_s=status.getString(2);
                i_s=status.getString(3);
                j_s=status.getString(4);
                p_s=status.getString(5);
            }
            if(pg_s.equals("0")){
                pg_l.setVisibility(View.GONE);
            }
            if(add_s.equals("0")){
                add_l.setVisibility(View.GONE);
                ls_add.setVisibility(View.GONE);
            }
            if(i_s.equals("00")){i_main.setVisibility(View.GONE);ls_intern.setVisibility(View.GONE);} if(i_s.equals("10")){i2_l.setVisibility(View.GONE);}

            if(j_s.equals("00")){j_main.setVisibility(View.GONE);
            ls_job.setVisibility(View.GONE);}
            if(i_s.equals("10")){j2_l.setVisibility(View.GONE);}
            if(p_s.equals("00")){p_main.setVisibility(View.GONE);ls_project.setVisibility(View.GONE);} if(i_s.equals("10")){p2_l.setVisibility(View.GONE);}
            personal = ud.personal_details_data(attend_id);
            while (personal.moveToNext()) {
                name = personal.getString(1);
                mob = personal.getString(2);
                c_city = personal.getString(3);
                s_city = personal.getString(4);
                email=personal.getString(5);
                linkedin=personal.getString(6);
            }
            nam.setText(name.toUpperCase());
            mobi.setText("+91 " + mob);
            loc.setText(c_city + ", " + s_city);
            email_n.setText(email);
            linked_n.setText(linkedin);

            //////////////////////////
            p1 = ud.project1_data(attend_id);
            while (p1.moveToNext()) {
                title_p1 = p1.getString(1);
                smonth_p1 = p1.getString(2);
                emonth_p1 = p1.getString(3);
                projectlink_p1 = p1.getString(5);
                description_p1 = p1.getString(6);
            }
            title1_p1.setText(title_p1);
            period_p1.setText(smonth_p1 + " - " + emonth_p1);
            link_p1.setText(projectlink_p1);
            desc_p1.setText(description_p1);
            ////////////////////////
            p2 = ud.project2_data(attend_id);
            p2 = ud.project2_data(attend_id);
            while (p2.moveToNext()) {
                title_p2 = p2.getString(1);
                smonth_p2 = p2.getString(2);
                emonth_p2 = p2.getString(3);
                projectlink_p2 = p2.getString(5);
                description_p2 = p2.getString(6);
            }
            title1_p2.setText(title_p2);
            period_p2.setText(smonth_p2 + " - " + emonth_p2);
            link_p2.setText(projectlink_p2);
            desc_p2.setText(description_p2);
            ////////////////////
            i1 = ud.intern1_data(attend_id);
            while (i1.moveToNext()) {
                profile_i1 = i1.getString(2);
                organisation_i1 = i1.getString(3);
                location_i1 = i1.getString(4);
                sdate_i1 = i1.getString(5);
                edate_i1 = i1.getString(6);
                description_i1 = i1.getString(8);
            }
            profil_i1.setText(profile_i1);
            period_i1.setText(sdate_i1 + " - " + edate_i1);
            org_i1.setText(organisation_i1 + " (" + location_i1 + ")");
            desc_i1.setText(description_i1);
            /////////////////////////
            i2 = ud.intern2_data(attend_id);
            while (i2.moveToNext()) {
                profile_i2 = i2.getString(2);
                organisation_i2 = i2.getString(3);
                location_i2 = i2.getString(4);
                sdate_i2 = i2.getString(5);
                edate_i2 = i2.getString(6);
                description_i2 = i2.getString(8);
            }
            profil_i2.setText(profile_i2);
            period_i2.setText(sdate_i2 + " - " + edate_i2);
            org_i2.setText(organisation_i2 + " (" + location_i2 + ")");
            desc_i2.setText(description_i2);
            ////////////////////
            j1 = ud.job1_data(attend_id);
            while (j1.moveToNext()) {
                profile_j1 = j1.getString(1);
                organisation_j1 = j1.getString(2);
                location_j1 = j1.getString(3);
                sdate_j1 = j1.getString(4);
                edate_j1 = j1.getString(5);
                description_j1 = j1.getString(7);
            }
            profil_j1.setText(profile_j1);
            period_j1.setText(sdate_j1 + " - " + edate_j1);
            org_j1.setText(organisation_j1 + " (" + location_j1 + ")");
            desc_j1.setText(description_j1);
//////////////////////////////////////////
            j2 = ud.job2_data(attend_id);
            while (j2.moveToNext()) {
                profile_j2 = j2.getString(1);
                organisation_j2 = j2.getString(2);
                location_j2 = j2.getString(3);
                sdate_j2 = j2.getString(4);
                edate_j2 = j2.getString(5);
                description_j2 = j2.getString(7);
            }
            profil_j2.setText(profile_j2);
            period_j2.setText(sdate_j2 + " - " + edate_j2);
            org_j2.setText(organisation_j2 + " (" + location_j2 + ")");
            desc_j2.setText(description_j2);
////////////////////////////////////
            x = ud.secondary_x_data(attend_id);
            while (x.moveToNext()) {
                school_x = x.getString(2);
                eyear_x = x.getString(3);
                board_x = x.getString(4);
                cgpa_x = x.getString(5);
            }
            deg_x.setText("X (Secondary)");
            yoc_x.setText("Year of Completion: " + eyear_x);
            board1_x.setText(board_x + " Board (" + school_x + ")");
            cgpa1_x.setText("CGPA : " + cgpa_x);
            ///////////////////
            xii = ud.xii_data(attend_id);
            while (xii.moveToNext()) {
                school_xii = xii.getString(2);
                eyear_xii = xii.getString(3);
                board_xii = xii.getString(4);
                stream_xii = xii.getString(5);
                cgpa_xii = xii.getString(6);
            }
            deg_xii.setText("XII (Senior Secondary), " + stream_xii);
            yoc_xii.setText("Year of Completion: " + eyear_xii);
            board1_xii.setText(board_xii + " Board (" + school_xii + ")");
            cgpa1_xii.setText("CGPA : " + cgpa_xii);
/////////////////////////////////////
            skills = ud.skills_data(attend_id);
//////////////////////////////////
            additional = ud.additional_data(attend_id);
            while (additional.moveToNext()) {
                description_add = additional.getString(1);
            }
            desc_add.setText(description_add);
/////////////////////////////////////
            ug = ud.graduation_data(attend_id);
            while (ug.moveToNext()) {
                college_grad = ug.getString(2);
                syear_grad = ug.getString(3);
                eyear_grad = ug.getString(4);
                degree_grad = ug.getString(5);
                stream_grad = ug.getString(6);
                cgpa_grad = ug.getString(7);
            }
            deg_ug.setText(degree_grad + ", " + stream_grad + " (" + syear_grad + " - " + eyear_grad + ")");
            yoc_ug.setText("Year of Completion: " + eyear_grad);
            clg_ug.setText(college_grad);
            cgpa_ug.setText("CGPA : " + cgpa_grad);
//////////////////////////////////////
            pg = ud.post_graduation_data(attend_id);
            while (pg.moveToNext()) {
                college_pgrad = pg.getString(2);
                syear_pgrad = pg.getString(3);
                eyear_pgrad = pg.getString(4);
                degree_pgrad = pg.getString(5);
                stream_pgrad = pg.getString(6);
                cgpa_pgrad = pg.getString(7);
            }
            deg_pg.setText(degree_pgrad + ", " + stream_pgrad + " (" + syear_pgrad + " - " + eyear_pgrad + ")");
            yoc_pg.setText("Year of Completion: " + eyear_pgrad);
            clg_pg.setText(college_pgrad);
            cgpa_pg.setText("CGPA : " + cgpa_pgrad);
            if(flag==0) {

                save.setClickable(true);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("SAVE");
                        alertDialog.setMessage("Enter your CV Name");
                        final EditText input = new EditText(getContext());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        input.setLayoutParams(lp);
                        alertDialog.setView(input);
                        alertDialog.setIcon(R.drawable.floppy_disk);
                        alertDialog.setCancelable(false);
                        alertDialog.setPositiveButton("SAVE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                        cvname = input.getText().toString();

                                        if (cvname.equals("")) {
                                            alertDialog.setMessage("CV Name can't be null");
                                            Toast.makeText(getContext(),
                                                    "CV Name can't be null", Toast.LENGTH_SHORT).show();
                                            //Intent myIntent1 = new Intent(view.getContext(), Show.class);
                                            //startActivityForResult(myIntent1, 0);
                                        } else {
                                            alertDialog.setMessage("Saved");
                                            ud.insert_cv_list(cvname, String.valueOf(attend_id), datewt, "1",view_status);
                                            save.setBackgroundResource(R.drawable.check);
                                            save.setClickable(false);
                                            fragname.setText(cvname);
                                            Fragment   fragment = new GiftsFragment();
                                           // String frag=prefs1.getString("for_profile_fragment_transaction","abcd");
                                            loadFragment(fragment);
                                            dialog.cancel();
                                        }

                                    }
                                });

                        alertDialog.setNegativeButton("Later",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        alertDialog.show();

                    }
                });
            }
            else{
                if(viewas.equals("same")){
                save.setBackgroundResource(R.drawable.ic_downloading);
               // save.setClickable(false);

               save.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       Toast.makeText(getActivity().getApplicationContext(), "Saved in CV Builder", Toast.LENGTH_LONG).show();
                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                           if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                   != PackageManager.PERMISSION_GRANTED) {
                               requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                       REQUEST_PERMISSIONS);
                           } else {
                              // Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                               //startActivityForResult(cameraIntent, CAMERA_REQUEST);
                               createPdf();
                           }
                       }

                  /*     if (boolean_save) {
                           Intent intent = new Intent(getApplicationContext(), PDFViewActivity.class);
                           startActivity(intent);
                           Toast.makeText(getActivity().getApplicationContext(), "Intent", Toast.LENGTH_LONG).show();
                       } else {
                           if (boolean_permission) {
                              /// progressDialog = new ProgressDialog(MainActivity.this);
                              // progressDialog.setMessage("Please wait");
                               //  bitmap = loadBitmapFromView(ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight());
                               // bitmap = takeScreenShot();
                               Toast.makeText(getActivity().getApplicationContext(), "else", Toast.LENGTH_LONG).show();
                               createPdf();

                           } else {
                               Toast.makeText(getActivity().getApplicationContext(), "not allowed", Toast.LENGTH_LONG).show();
                           }
                         //  createPdf();

                       }*/

                   }
               });
                }
                else if(viewas.equals("other")){
                    cv_list_data=ud.cv_list_data();
                    cv_list_data.moveToLast();
                    encryted_attednd_id=cv_list_data.getInt(0);
                    save.setClickable(true);
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                            alertDialog.setTitle("SAVE");
                            alertDialog.setMessage("Enter your CV Name");
                            final EditText input = new EditText(getContext());
                            String name1="";
                            if(fragmentname.length()>20){
                                  name1=fragmentname.substring(0,20);
                                input.setText(name1);

                            }
                            else{
                                input.setText(view_status+fragmentname);
                            }


                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT);
                            input.setLayoutParams(lp);
                            alertDialog.setView(input);
                            alertDialog.setIcon(R.drawable.floppy_disk);
                            alertDialog.setCancelable(false);
                            alertDialog.setPositiveButton("SAVE",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            cvname = input.getText().toString();

                                            if (cvname.equals("")) {
                                                alertDialog.setMessage("CV Name can't be null");
                                                Toast.makeText(getContext(),
                                                        "CV Name can't be null", Toast.LENGTH_SHORT).show();
                                                //Intent myIntent1 = new Intent(view.getContext(), Show.class);
                                                //startActivityForResult(myIntent1, 0);
                                            } else {
                                                String attendid=String.valueOf(attend_id)+String.valueOf(encryted_attednd_id);
                                                sequence_o = ud.sequence_data();
                                                sequence_o.moveToLast();
                                                attend_id_o = sequence_o.getInt(0);
                                                alertDialog.setMessage("Saved");
                                                ud.insert_cv_list(cvname, String.valueOf(attend_id_o+1), datewt, "1",view_status);
                                                save.setBackgroundResource(R.drawable.check);
                                                save.setClickable(false);
                                                fragname.setText(cvname);
                                                ud.insert_status_list(pg_s,add_s,i_s,j_s,p_s);
                                                ud.insertpersonaldetails(name,mob,c_city,s_city,email,linkedin);
                                                ud.insertgraduation(0,college_grad,syear_grad,eyear_grad,degree_grad,stream_grad,cgpa_grad);
                                                ud.insertxii(0,school_xii,eyear_xii,board_xii,stream_xii,cgpa_xii);
                                                ud.insertsecondaryx(0,school_x,eyear_x,board_x,cgpa_x);
                                                ud.insertpostgraduation(0,college_pgrad,syear_pgrad,eyear_pgrad,degree_pgrad,stream_pgrad,cgpa_pgrad);
                                                ud.insertjob1(profile_j1,organisation_j1,location_j1,0,edate_j1,sdate_j1,description_j1);
                                                ud.insertjob2(profile_j2,organisation_j2,location_j2,0,edate_j2,sdate_j2,description_j2);
                                                ud.insertintern1(0,profile_i1,organisation_i1,location_i1,0,edate_i1,sdate_i1,description_i1);
                                                ud.insertintern2(0,profile_i2,organisation_i2,location_i2,0,edate_i2,sdate_i2,description_i2);
                                                ud.insertproject1(title_p1,projectlink_p1,0,emonth_p1,smonth_p1,description_p1);
                                                ud.insertproject2(title_p2,projectlink_p2,0,emonth_p2,smonth_p2,description_p2);
                                                ud.insertskills(java,c,cpp,html,css,javascript,jquery,python,android,unity,coral,r,ml,ai,data);
                                                ud.insertadditional(description_add);
                                                Fragment   fragment = new GiftsFragment();
                                                // String frag=prefs1.getString("for_profile_fragment_transaction","abcd");
                                                loadFragment(fragment);
                                                dialog.cancel();

                                            }

                                        }
                                    });

                            alertDialog.setNegativeButton("Later",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                            alertDialog.show();

                        }
                    });
                }
            }

        }
        return view;
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
    public void loadFragment(Fragment fragment) {
        // load fragment
   /*   if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 4) {
           getActivity().getSupportFragmentManager().popBackStackImmediate();
       }*/

        int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
      //  Toast.makeText(getActivity().getApplicationContext(),count+"Added",Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
      /*  if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 4) {
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }*/
    }
    private void createPdf(){
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            document = new PdfDocument();
        }

        PdfDocument.PageInfo pageInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 2).create();
        }
        PdfDocument.Page page = null;
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT) {
            page = document.startPage(pageInfo);
        }

        Canvas canvas = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            canvas = page.getCanvas();
        }


        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = takeScreenShot();
        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            document.finishPage(page);
        }
        // write the document content
        Document doc = new Document();
        doc.setPageSize(PageSize.A4);



            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CV Builder";

            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();

          //  Log.d("PDFCreator", "PDF Path: " + path);


            File file = new File(dir, fragmentname+".pdf");
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

     //   PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();

            String targetPdf = "/sdcard/"+fragmentname+".pdf";
        File filePath = new File(targetPdf);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
               // document.writeTo(new FileOutputStream(filePath));
                document.writeTo(fOut);

            }
            //btn_generate.setText("Check PDF");
            boolean_save=true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            document.close();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "permission granted", Toast.LENGTH_LONG).show();
              /*  Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
            } else {
                Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

/*    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);

            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;


        }

    }*/
  /*  @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(getContext(), "onRequestPermissionResult", Toast.LENGTH_LONG).show();
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                boolean_permission = true;


            } else {
                Toast.makeText(getContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

            }
        }
    }*/

    private Bitmap takeScreenShot()
    {
        View u =  view.findViewById(R.id.scrol_profile);

        ScrollView z = (ScrollView) view.findViewById(R.id.scrol_profile);
        int totalHeight = z.getChildAt(0).getHeight();
        int totalWidth = z.getChildAt(0).getWidth();

        Bitmap b = getBitmapFromView(u,totalHeight,totalWidth);
        //Save bitmap
        String extr = Environment.getExternalStorageDirectory()+"/Folder/";
        String fileName = "report.jpg";
        File myPath = new File(extr, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), b, "Screen", "screen");
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  b;

    }
    public Bitmap getBitmapFromView(View view, int totalHeight, int totalWidth) {

        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth,totalHeight , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
    private void loadImageFromStorage()
    {
        Toast.makeText(getContext(), "Please allow the allow the permission", Toast.LENGTH_LONG).show();

        try {
            ContextWrapper cw = new ContextWrapper(getContext());

            File directory = cw.getDir("imageDir", Context.MODE_WORLD_READABLE);
             File f=new File(directory.toString(), "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            iv.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

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
    @Override
    public void onPause() {
        super.onPause();
        pauseGame();
        rewardedVideoAd.pause(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!gameOver && gamePaused) {
            resumeGame();
        }
        rewardedVideoAd.resume(getContext());
    }

    private void pauseGame() {
        countDownTimer.cancel();
        gamePaused = true;
    }

    private void resumeGame() {
        createTimer(timeRemaining);
        gamePaused = false;
    }

    private void loadRewardedVideoAd() {
        if (!rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        }
    }

    private void addCoins(int coins) {
        coinCount += coins;
        coinCountText.setText("Coins: " + coinCount);
    }

    private void startGame() {
        // Hide the retry button, load the ad, and start the timer.
        retryButton.setVisibility(View.GONE);
        showVideoButton.setVisibility(View.GONE);
        loadRewardedVideoAd();
        createTimer(COUNTER_TIME);
        gamePaused = false;
        gameOver = false;
    }

    // Create the game timer, which counts down to the end of the level
    // and shows the "retry" button.
    private void createTimer(long time) {

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(time * 1000, 50) {
            @Override
            public void onTick(long millisUnitFinished) {
                timeRemaining = ((millisUnitFinished / 1000) + 1);
                textViewretry.setText(timeRemaining+"");
            }

            @Override
            public void onFinish() {
                if (rewardedVideoAd.isLoaded()) {
                    showVideoButton.setVisibility(View.VISIBLE);
                }
                else{
                    retryButton.setVisibility(View.VISIBLE);
                }
                textViewretry.setText("Game!");
                addCoins(GAME_OVER_REWARD);
               // showVideoButton.setVisibility(View.VISIBLE);
                gameOver = true;
            }
        };
        countDownTimer.start();
    }

    private void showRewardedVideo() {
        showVideoButton.setVisibility(View.GONE);
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        // Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        // Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        // Preload the next video ad.
        retryButton.setVisibility(View.VISIBLE);
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        //Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        // Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewarded(RewardItem reward) {
        //Toast.makeText(this,String.format(" onRewarded! currency: %s amount: %d", reward.getType(),reward.getAmount()), Toast.LENGTH_SHORT).show();
        addCoins(reward.getAmount());
        retryButton.setVisibility(View.VISIBLE);
        showVideoButton.setVisibility(View.GONE);
    }

    @Override
    public void onRewardedVideoStarted() {
        //  Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        // Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }

}