package com.utsavgupta.cvbuilder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.utsavgupta.cvbuilder.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;


public class CartFragment extends Fragment {
    String pg_s="1",add_s="1",i_s="11",p_s="11",j_s="11";
    TextView personal,education,jobs,interns,por,trainings,projects,skills,additionals,mjob,minternship,mproject,madditional;
    byte[] image_blob;Boolean isjobexpanded=false,isinternshipexpanded=false,isprojectexpanded=false,isadditionalexpanded=false;
    TextView frgname;MainActivity myactivity;
    LinearLayout pers,edu,job,intern,po,train,pro,ski,add;
    List<String> city_names=new ArrayList<>(); private InterstitialAd mInterstitialAd;
    private User_details ud;private static final int SELECTED_PICTURE=1;
    ImageView iv;Uri uri;Bitmap bitmap;
    //Inserting----------------------
    Button submit, button,img;EditText ename,emob,addition;
    private AutoCompleteTextView autoTextView,currentcity,secondcity;
    String[] fruits = {"Apple", "Banana", "Cherry", "Date","Dates", "Grape", "Kiwi", "Mango", "Pear"};
    String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
    String[] cities,states,colleges,degrees,streamug,degreespg,schools;
    StringBuffer stringBuffer;
    String country="";
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener1,mDateSetListener2,mDateSetListener3,
            mDateSetListener4,mDateSetListener5,mDateSetListener6,mDateSetListener7,
            mDateSetListener8,mDateSetListener9,mDateSetListener10,mDateSetListener11;
    private PermissionsChecker checker;
    TextView ug,xii,x,pg,  j1,j2  ,i1,i2,  p1,p2;
    String[] board={"ICSE","ISC","CBSE","Government Boards"};
    String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] streamxii={"Science(with Mathematics)","Science(with Biology)","Arts","Commerce(with Mathematics)","Commerce(with Economics)"};
    private String edatej2;
    private String sdatej2;
    private String sdatej1,edatej1; private String sdatei1,edatei1;
    private String sdatep1,edatep1; private String sdatep2,edatep2;
    private String sdatei2,edatei2;
    private TextWatcher textWatcher;
    private int num1;
    private int REQUEST_GET_SINGLE_FILE=1;
    private String myBase64Image;
    private String attend_ids="last";
    private String fragmentname;
    private ImageButton back;
    private BottomNavigationView navigation;
    EditText email,linkedin;
    int java=0,c=0,cpp=0,html=0,css=0,javascript=0,jquery=0,python=0,android1=0,unity=0,coral=0,r=0,ml=0,ai=0,data=0;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_cart, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        MobileAds.initialize(getContext(), "ca-app-pub-2009435266295761~1912784762");
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) view.findViewById(R.id.adView);

        // adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

       // MobileAds.initialize(getContext(), "ca-app-pub-3940256099942544~3347511713");
        // Load an ad into the AdMob banner view.
      //  AdView adView = (AdView) view.findViewById(R.id.adView);
        // adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
       // AdRequest adRequest = new AdRequest.Builder().build();
       // adView.loadAd(adRequest);


        // Create the text view to show the level number.
        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
      mInterstitialAd = newInterstitialAd();
      loadInterstitial();
        iv=(ImageView)view.findViewById(R.id.imageView1);
        img=(Button)view.findViewById(R.id.button1);
        button=(Button)view.findViewById(R.id.button);
        submit=view.findViewById(R.id.submitx);
        ename=view.findViewById(R.id.name);
        emob=view.findViewById(R.id.mob);
        email=view.findViewById(R.id.email);
        linkedin=view.findViewById(R.id.linkedin);
       // emob.addTextChangedListener(textWatcher);
        addition=view.findViewById(R.id.additional);
        personal=view.findViewById(R.id.personal);
        education=view.findViewById(R.id.educ);
        pers=view.findViewById(R.id.pers);
        edu=view.findViewById(R.id.edu);
        job=view.findViewById(R.id.job);
        jobs=view.findViewById(R.id.jobinfo);
        interns=view.findViewById(R.id.internshipinfo);
        projects=view.findViewById(R.id.projectinfo);
        skills=view.findViewById(R.id.skillinfo);
        additionals=view.findViewById(R.id.additionalinfo);
        ud=new User_details(getContext());
        mjob=view.findViewById(R.id.mjob);
        madditional=view.findViewById(R.id.madditional);
        mproject=view.findViewById(R.id.mproject);
        minternship=view.findViewById(R.id.minternship);
        intern=view.findViewById(R.id.internship);
        pro=view.findViewById(R.id.project);
        ski=view.findViewById(R.id.skill);
        add=view.findViewById(R.id.additionals);
        cities=getResources().getStringArray(R.array.india_top_places);
        states=getResources().getStringArray(R.array.india_states);
        colleges=getResources().getStringArray(R.array.india_top_colleges);
        degrees=getResources().getStringArray(R.array.degress);
        degreespg=getResources().getStringArray(R.array.degreepg);
        streamug=getResources().getStringArray(R.array.streamug);
        schools=getResources().getStringArray(R.array.schools);
        ug=view.findViewById(R.id.ug);
        xii=view.findViewById(R.id.xii);
        x=view.findViewById(R.id.x);
        pg=view.findViewById(R.id.pg);

        j1=view.findViewById(R.id.j1);
        j2=view.findViewById(R.id.j2);

        i1=view.findViewById(R.id.i1);
        i2=view.findViewById(R.id.i2);

        p1=view.findViewById(R.id.p1);
        p2=view.findViewById(R.id.p2);
//Creating the instance of ArrayAdapter containing list of fruit names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.select_dialog_item,cities);
        ArrayAdapter<String> adapter_colleges = new ArrayAdapter<String>
                (getContext(), android.R.layout.select_dialog_item,colleges);
        ArrayAdapter<String> adapter_streamxii = new ArrayAdapter<String>
                (getContext(), android.R.layout.select_dialog_item,streamxii);
        ArrayAdapter<String> adapter_streamug = new ArrayAdapter<String>
                (getContext(), android.R.layout.select_dialog_item,streamug);
        ArrayAdapter<String> adapter_schools = new ArrayAdapter<String>
                (getContext(), android.R.layout.select_dialog_item,schools);
        frgname=view.findViewById(R.id.textView5);
        //Getting the instance of AutoCompleteTextView
        currentcity=view.findViewById(R.id.current_city);
        currentcity.setAdapter(adapter);
        currentcity.setThreshold(1);
        secondcity=view.findViewById(R.id.second_city);
        secondcity.setAdapter(adapter);
        secondcity.setThreshold(1);
        myactivity=(MainActivity)getContext();
        navigation = myactivity.findViewById(R.id.navigation);


        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        /*AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);*/
        stringBuffer=new StringBuffer();
       // addMenuItemsFromJson();
        // country=country+stringBuffer.toString();
        //checker = new PermissionsChecker(this);

        // createPdf(FileUtils.getAppPath(getApplicationContext()) + "12345.pdf");
        //Adding years to the start year spinner for graduation
        SharedPreferences prefs = getActivity().getSharedPreferences("fragment_name", MODE_PRIVATE);
        fragmentname = prefs.getString("name_main", "No name defined");//"No name defined" is the default value.
        frgname.setText(fragmentname);
        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1990; i <= thisYear+10; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter_s_years = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, years);
        final View test1View = view.findViewById(R.id.test1);
        final View test2View = view.findViewById(R.id.test2);
        final View test3View = view.findViewById(R.id.test3);
        final View test4View = view.findViewById(R.id.test4);
        final View test8View = view.findViewById(R.id.test8);
        final View test9View = view.findViewById(R.id.test9);
        final View test5View = view.findViewById(R.id.test5);
        final View test6View = view.findViewById(R.id.test6);
        final View test7View = view.findViewById(R.id.test7);
        final View test10View = view.findViewById(R.id.test10);
        final View test11View = view.findViewById(R.id.test11);
        final TextView startmonth = test10View.findViewById(R.id.sdate);
        final TextView endmonth = test10View.findViewById(R.id.edate);
        final TextView startmonthx = test11View.findViewById(R.id.sdate);
        final TextView endmonthx = test11View.findViewById(R.id.edate);
        final TextView startmonthi1 = test6View.findViewById(R.id.sdate);
        final TextView endmonthi1 = test6View.findViewById(R.id.edate);
        final TextView startmonthxi2 = test7View.findViewById(R.id.sdate);
        final TextView endmonthxi2 = test7View.findViewById(R.id.edate);
        final TextView startmonthj1 = test8View.findViewById(R.id.sdate);
        final TextView endmonthj1 = test8View.findViewById(R.id.edate);
        final TextView startmonthxj2 = test9View.findViewById(R.id.sdate);
        final TextView endmonthxj2 = test9View.findViewById(R.id.edate);
        final AutoCompleteTextView locationi1=test6View.findViewById(R.id.location);
        final EditText cgpaug=test1View.findViewById(R.id.cgpa);
        final EditText cgpapg=test4View.findViewById(R.id.cgpa);
        final EditText cgpax=test3View.findViewById(R.id.cgpa);
        final EditText cgpaxii=test2View.findViewById(R.id.cgpa);
        final EditText profilej1=test8View.findViewById(R.id.profile);
        final EditText profilej2=test9View.findViewById(R.id.profile);
        final EditText organisationj1=test8View.findViewById(R.id.organisation);
        final EditText organisationj2=test9View.findViewById(R.id.organisation);
        final EditText descriptionj1=test8View.findViewById(R.id.description);
        final EditText descriptionj2=test9View.findViewById(R.id.description);
        final CheckBox cwhj1=test8View.findViewById(R.id.checkBox);
        final CheckBox cwhj2=test9View.findViewById(R.id.checkBox);
        final EditText profilei1=test6View.findViewById(R.id.profile);
        final EditText profilei2=test7View.findViewById(R.id.profile);
        final EditText organisationi1=test6View.findViewById(R.id.organisation);
        final EditText organisationi2=test7View.findViewById(R.id.organisation);
        final EditText descriptioni1=test6View.findViewById(R.id.description);
        final EditText descriptioni2=test7View.findViewById(R.id.description);
        final CheckBox cwhi1=test6View.findViewById(R.id.checkBox);
        final CheckBox cwhi2=test7View.findViewById(R.id.checkBox);
        final EditText titlep1=test10View.findViewById(R.id.title);
        final EditText titlep2=test11View.findViewById(R.id.title);
        final EditText descriptionp1=test10View.findViewById(R.id.description);
        final EditText descriptionp2=test11View.findViewById(R.id.description);
        final EditText projectlinkp1=test10View.findViewById(R.id.plink);
        final EditText projectlinkp2=test11View.findViewById(R.id.plink);
        final CheckBox cwhp1=test10View.findViewById(R.id.checkBox);
        final CheckBox cwhp2=test11View.findViewById(R.id.checkBox);
        final AutoCompleteTextView locationi2=test7View.findViewById(R.id.location);
        final AutoCompleteTextView locationj1=test8View.findViewById(R.id.location);
        final AutoCompleteTextView locationj2=test9View.findViewById(R.id.location);
        final AutoCompleteTextView collegeug=test1View.findViewById(R.id.college);
        final AutoCompleteTextView collegepg=test4View.findViewById(R.id.college);
        final AutoCompleteTextView streamxii=test2View.findViewById(R.id.stream);
        final AutoCompleteTextView streamug=test1View.findViewById(R.id.stream);
        final AutoCompleteTextView streampg=test4View.findViewById(R.id.stream);
        final AutoCompleteTextView schoolsx=test3View.findViewById(R.id.school);
        final AutoCompleteTextView schoolsxii=test2View.findViewById(R.id.school);
        final RadioGroup rg_grad= test1View.findViewById(R.id.radio_group);
        final RadioGroup rg_pgrad= test4View.findViewById(R.id.radio_group);
        final RadioGroup rg_xii= test2View.findViewById(R.id.radio_group);
        final RadioGroup rg_x= test3View.findViewById(R.id.radio_group);
        final RadioGroup rg_i1= test6View.findViewById(R.id.radio_group);
        final RadioGroup rg_i2= test7View.findViewById(R.id.radio_group);
        final RadioButton rb_grad1=test1View.findViewById(R.id.radio1);
        final RadioButton rb_grad2=test1View.findViewById(R.id.radio2);
        final CheckBox cjava=test5View.findViewById(R.id.java);
        final CheckBox cc=test5View.findViewById(R.id.c);
        final CheckBox ccpp=test5View.findViewById(R.id.cpp);
        final CheckBox chtml=test5View.findViewById(R.id.html);
        final CheckBox cjavascript=test5View.findViewById(R.id.javascript);
        final CheckBox cjquery=test5View.findViewById(R.id.jquery);
        final CheckBox ccss=test5View.findViewById(R.id.css);
        final CheckBox cpython=test5View.findViewById(R.id.python);
        final CheckBox cr=test5View.findViewById(R.id.r);
        final CheckBox cunity=test5View.findViewById(R.id.unity);
        final CheckBox ccoral=test5View.findViewById(R.id.coral);
        final CheckBox candroid=test5View.findViewById(R.id.android);
        final CheckBox cml=test5View.findViewById(R.id.ml);
        final CheckBox cai=test5View.findViewById(R.id.ai);
        final CheckBox cdata=test5View.findViewById(R.id.data);
        schoolsx.setAdapter(adapter_schools);
        schoolsx.setThreshold(1);
        schoolsxii.setAdapter(adapter_schools);
        schoolsxii.setThreshold(1);
        streamxii.setAdapter(adapter_streamxii);
        streamxii.setThreshold(1);
        streamug.setAdapter(adapter_streamug);
        streamug.setThreshold(1);
        streampg.setAdapter(adapter_streamug);
        streampg.setThreshold(1);
        collegepg.setThreshold(1);
        collegepg.setAdapter(adapter_colleges);
        collegeug.setThreshold(1);
        collegeug.setAdapter(adapter_colleges);
        locationi1.setAdapter(adapter);
        locationi1.setThreshold(1);
        locationi2.setAdapter(adapter);
        locationi2.setThreshold(1);
        locationj1.setAdapter(adapter);
        locationj1.setThreshold(1);
        locationj2.setAdapter(adapter);
        locationj2.setThreshold(1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//loadImageFromStorage();
            }
        });
        endmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                edatep1 = months[month-1] + "/" + year;
                endmonth.setText(edatep1);
            }
        };
        startmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                sdatep1 = months[month-1] + "/" + year;
                startmonth.setText(sdatep1);
            }
        };
        endmonthx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                edatep2= months[month-1] + "/" + year;
                endmonthx.setText(edatep2);
            }
        };
        startmonthx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener3,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                sdatep2 = months[month-1] + "/" + year;
                startmonthx.setText(sdatep2);
            }
        };
        endmonthi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener4,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener4 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                edatei1 = day+"th"+" "+months[month-1] + "," + year;
                endmonthi1.setText(edatei1);
            }
        };
        startmonthi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener5,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener5 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                sdatei1 = day+"th"+" "+months[month-1] + "," + year;
                startmonthi1.setText(sdatei1);
            }
        };
        endmonthxi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener6,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener6 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                edatei2 = day+"th"+" "+months[month-1] + "," + year;
                endmonthxi2.setText(edatei2);
            }
        };
        startmonthxi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener7,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener7 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                sdatei2 = day+"th"+" "+months[month-1] + "," + year;
                startmonthxi2.setText(sdatei2);
            }
        };
        //-----------------------------------------------------------
        endmonthj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener8,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener8 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                edatej1 = day+"th"+" "+months[month-1] + "," + year;
                endmonthj1.setText(edatej1);
            }
        };
        startmonthj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener9,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener9 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                sdatej1 = day+"th"+" "+months[month-1] + "," + year;
                startmonthj1.setText(sdatej1);
            }
        };
        endmonthxj2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener10,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener10 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                edatej2 = day+"th"+" "+months[month-1] + "," + year;
                endmonthxj2.setText(edatej2);
            }
        };
        startmonthxj2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener11,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener11 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                sdatej2 = day+"th"+" "+months[month-1] + "," + year;
                startmonthxj2.setText(sdatej2);
            }
        };
        ArrayAdapter<String> degrees_adap = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, degrees);
        final Spinner degreeug = (Spinner)test1View.findViewById(R.id.spinner);
        degreeug.setAdapter(degrees_adap);
        ArrayAdapter<String> degrees_adapg = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, degreespg);
        final Spinner degreepg = (Spinner)test4View.findViewById(R.id.spinner);
        degreepg.setAdapter(degrees_adapg);
        ArrayAdapter<String> boards = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, board);
        final Spinner board = (Spinner)test2View.findViewById(R.id.spinner);
        board.setAdapter(boards);
        final Spinner boardx = (Spinner)test3View.findViewById(R.id.spinner);
        boardx.setAdapter(boards);
        final Spinner startYear = (Spinner)test1View.findViewById(R.id.radio5);
        startYear.setAdapter(adapter_s_years);
        final Spinner endYear = (Spinner)test1View.findViewById(R.id.radio6);
        endYear.setAdapter(adapter_s_years);
        final Spinner startYearpg = (Spinner)test4View.findViewById(R.id.radio5pg);
        startYearpg.setAdapter(adapter_s_years);
        final Spinner endYearpg = (Spinner)test4View.findViewById(R.id.radio6pg);
        endYearpg.setAdapter(adapter_s_years);
        final Spinner y_o_c_xii = (Spinner)test2View.findViewById(R.id.radio5xii);
        y_o_c_xii.setAdapter(adapter_s_years);
        final Spinner y_o_c_x = (Spinner)test3View.findViewById(R.id.radio5x);
        y_o_c_x.setAdapter(adapter_s_years);
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (checker.lacksPermissions(REQUIRED_PERMISSION)) {
                    PermissionsActivity.startActivityForResult(EnterDetails.this, PERMISSION_REQUEST_CODE, REQUIRED_PERMISSION);
                } else {
                    createPdf(FileUtils.getAppPath(getApplicationContext()) + "12345.pdf");
                }*/
                //Toast.makeText(getApplicationContext(),country.toString(),Toast.LENGTH_LONG).show();

                if(pers.getVisibility()== View.VISIBLE) { pers.setVisibility(View.GONE);
                personal.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                    personal.setCompoundDrawablePadding(3);
                }
                else if(pers.getVisibility()== View.GONE){pers.setVisibility(View.VISIBLE);
                    personal.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                    personal.setCompoundDrawablePadding(3);}
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edu.getVisibility()== View.VISIBLE) {edu.setVisibility(View.GONE);
                    education.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                    education.setCompoundDrawablePadding(3);}
                else if(edu.getVisibility()== View.GONE){edu.setVisibility(View.VISIBLE);
                    education.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                    education.setCompoundDrawablePadding(3);}
            }
        });


        ug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edu.getVisibility()== View.VISIBLE){
                    if(test1View.getVisibility()== View.VISIBLE) {test1View.setVisibility(View.GONE);
                        }
                    else if(test1View.getVisibility()== View.GONE){test1View.setVisibility(View.VISIBLE);
                        }
                }}
        });
        xii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edu.getVisibility()== View.VISIBLE){
                    if(test2View.getVisibility()== View.VISIBLE) {test2View.setVisibility(View.GONE);
                        }
                    else if(test2View.getVisibility()== View.GONE){test2View.setVisibility(View.VISIBLE);
                        }
                }}
        });
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edu.getVisibility()== View.VISIBLE){
                    if(test3View.getVisibility()== View.VISIBLE) {test3View.setVisibility(View.GONE);
                        }
                    else if(test3View.getVisibility()== View.GONE){test3View.setVisibility(View.VISIBLE);
                        }
                }}
        });
        pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edu.getVisibility() == View.VISIBLE) {
                    if (test4View.getVisibility() == View.GONE) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Post Graduate");
                alertDialog.setMessage("Have you done or pursuing your Post Graduation??");

                alertDialog.setIcon(R.drawable.floppy_disk);
               // alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();


                                test4View.setVisibility(View.VISIBLE);

                            }

                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                                pg.setVisibility(View.GONE);
                                pg_s="0";
                            }
                        });

                alertDialog.show();}
                else if(test4View.getVisibility() == View.VISIBLE){
                        test4View.setVisibility(View.GONE);}
            }
        }

        });

        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (job.getVisibility() == View.GONE) {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Jobs Info");
                    alertDialog.setMessage("Have you done any Job yet??");

                    alertDialog.setIcon(R.drawable.floppy_disk);
                   // alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                    job.setVisibility(View.VISIBLE);
                                    jobs.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                                    jobs.setCompoundDrawablePadding(3);
                                }


                            });

                    alertDialog.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.cancel();
                                    jobs.setVisibility(View.GONE);
                                    mjob.setVisibility(View.GONE);
                                    j_s="00";
                                }
                            });

                    alertDialog.show();
                } else if (job.getVisibility() == View.VISIBLE) {
                    job.setVisibility(View.GONE);
                    jobs.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                    jobs.setCompoundDrawablePadding(3);
                }
            }
        });
        j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(job.getVisibility()== View.VISIBLE){
                    if(test8View.getVisibility()== View.VISIBLE) {test8View.setVisibility(View.GONE);
                      }
                    else if(test8View.getVisibility()== View.GONE){test8View.setVisibility(View.VISIBLE);
                        }
                }
            }
        });
        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(job.getVisibility()== View.VISIBLE){
                    if(test9View.getVisibility()== View.GONE){
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Jobs Info.");
                alertDialog.setMessage("Have you done any other job?");

                alertDialog.setIcon(R.drawable.floppy_disk);
               // alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();


                                     test9View.setVisibility(View.VISIBLE);


                            }


                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                                j2.setVisibility(View.GONE);
                                j_s="10";
                            }
                        });

                alertDialog.show();}
                else if(test9View.getVisibility()== View.VISIBLE) {test9View.setVisibility(View.GONE);
            }
            }

            }
        });


        interns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intern.getVisibility()== View.GONE){
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Internship Info");
                alertDialog.setMessage("Have you done any internship yet?");

                alertDialog.setIcon(R.drawable.floppy_disk);
             //   alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                                    intern.setVisibility(View.VISIBLE);
                                    interns.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                                    interns.setCompoundDrawablePadding(3);}



                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                                interns.setVisibility(View.GONE);
                                minternship.setVisibility(View.GONE);
                                i_s="00";
                            }
                        });

                alertDialog.show();}
                else if(intern.getVisibility()== View.VISIBLE) {intern.setVisibility(View.GONE);
                interns.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                interns.setCompoundDrawablePadding(3);}

            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intern.getVisibility()== View.VISIBLE){
                    if(test6View.getVisibility()== View.VISIBLE) {test6View.setVisibility(View.GONE);
                        }
                    else if(test6View.getVisibility()== View.GONE){test6View.setVisibility(View.VISIBLE);
                        }
                }}
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intern.getVisibility()== View.VISIBLE){
                    if(test7View.getVisibility()== View.GONE){
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Internship Info.");
                alertDialog.setMessage("Have you done any other internship?");

                alertDialog.setIcon(R.drawable.floppy_disk);
              //  alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();


                                    test7View.setVisibility(View.VISIBLE);
                                    }




                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                                i2.setVisibility(View.GONE);
                                i_s="10";
                            }
                        });

                alertDialog.show();}
                else if(test7View.getVisibility()== View.VISIBLE) {test7View.setVisibility(View.GONE);
            }

            }

            }
        });
        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pro.getVisibility()== View.GONE){
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Project Info");
                alertDialog.setMessage("Have you done any project yet?");

                alertDialog.setIcon(R.drawable.floppy_disk);
              //  alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                                    pro.setVisibility(View.VISIBLE);
                                    projects.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                                    projects.setCompoundDrawablePadding(3);}



                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                                projects.setVisibility(View.GONE);
                                mproject.setVisibility(View.GONE);
                                p_s="00";
                            }
                        });

                alertDialog.show();}
                else if(pro.getVisibility()== View.VISIBLE) {pro.setVisibility(View.GONE);
                projects.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                projects.setCompoundDrawablePadding(3);
                }

            }
        });
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pro.getVisibility()== View.VISIBLE){
                    if(test10View.getVisibility()== View.VISIBLE) {test10View.setVisibility(View.GONE);
                       }
                    else if(test10View.getVisibility()== View.GONE){test10View.setVisibility(View.VISIBLE);
                        }
                }}
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pro.getVisibility()== View.VISIBLE){
                    if(test11View.getVisibility()== View.GONE){
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Project Info.");
                alertDialog.setMessage("Do you have any other project??");

                alertDialog.setIcon(R.drawable.floppy_disk);
            //    alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();


                                     test11View.setVisibility(View.VISIBLE);
                                    }




                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                                p2.setVisibility(View.GONE);
                                p_s="10";
                            }
                        });

                alertDialog.show();}
                else if(test11View.getVisibility()== View.VISIBLE) {test11View.setVisibility(View.GONE);
            }

}
            }
        });
        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ski.getVisibility()== View.VISIBLE) {ski.setVisibility(View.GONE);
                    skills.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                    skills.setCompoundDrawablePadding(3);}
                else if(ski.getVisibility()== View.GONE){ski.setVisibility(View.VISIBLE);
                    skills.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                    skills.setCompoundDrawablePadding(3);}
            }
        });
        additionals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add.getVisibility()== View.GONE){
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Additional Info.");
                alertDialog.setMessage("Do you want to describe any of your additional skills?");

                alertDialog.setIcon(R.drawable.floppy_disk);
              //  alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                                    add.setVisibility(View.VISIBLE);
                                    additionals.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                                    additionals.setCompoundDrawablePadding(3);}



                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                                additionals.setVisibility(View.GONE);
                                madditional.setVisibility(View.GONE);
                                add_s="0";
                            }
                        });

                alertDialog.show();}
                else if(add.getVisibility()== View.VISIBLE) {add.setVisibility(View.GONE);
                additionals.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                additionals.setCompoundDrawablePadding(3);}


            }
        });

         textWatcher =new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(emob.getText().length()>0) {
                    int num = Integer.parseInt(emob.getText().toString());
                    if (num>=1 && num<=24)
                    {
                        //save the number
                        num1=num;
                    }
                    else{
                        Toast.makeText(getContext(),"Please enter the code in the range of 1-24",Toast.LENGTH_SHORT).show();
                        emob.setText("");
                        num1=-1;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        if(num1==-1)
        {
            Toast.makeText(getContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }
        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
                Fragment   fragment = new StoreFragment();
                loadFragment(fragment);
            }
        });
        // Inserting into the local db
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name="",mob="",c_city="",s_city="",emaill="",linkedinn="",
                        college_grad="",syear_grad="",eyear_grad="",degree_grad="",stream_grad="",cgpa_grad="",
                        college_pgrad="",syear_pgrad="",eyear_pgrad="",degree_pgrad="",stream_pgrad="",cgpa_pgrad="",
                        school_xii="",eyear_xii="",board_xii="",stream_xii="",cgpa_xii="",
                        school_x="",eyear_x="",board_x="",stream_x="",cgpa_x="",
                        profile_j1="",organisation_j1="",edate_j1="",sdate_j1="",location_j1="",description_j1="",
                        profile_j2="",organisation_j2="",edate_j2="",sdate_j2="",location_j2="",description_j2="",
                        profile_i1="",organisation_i1="",edate_i1="",sdate_i1="",location_i1="",description_i1="",
                        profile_i2="",organisation_i2="",edate_i2="",sdate_i2="",location_i2="",description_i2="",
                        title_p1="",emonth_p1="",smonth_p1="",projectlink_p1="",description_p1="",
                        title_p2="",emonth_p2="",smonth_p2="",projectlink_p2="",description_p2="",
                        description_add="";
                int status_grad=0,status_pgrad=0,status_xii=0,status_x=0,cwh_j1=0,
                        cwh_j2=0,cwh_i1=0,cwh_i2=0,type_i1=0,type_i2=0,cwh_p1=0,cwh_p2=0;

                byte[] image = new byte[0];
                //personal details
                name=ename.getText().toString();
                mob=emob.getText().toString();
                c_city=currentcity.getText().toString();
                s_city=secondcity.getText().toString();
                emaill=email.getText().toString();
                linkedinn=linkedin.getText().toString();
//profile_image
//if(bitmap!=null){image=getBytes(bitmap);}


//graduation
                int selectedId = rg_grad.getCheckedRadioButtonId();
                RadioButton radioButton = test1View.findViewById(selectedId);
                if(radioButton.getText().equals("Pursuing")){
                    status_grad=0;
                }
                else{
                    status_grad=1;
                }
                college_grad=collegeug.getText().toString();
                syear_grad = startYear.getSelectedItem().toString();
                eyear_grad=endYear.getSelectedItem().toString();
                stream_grad=streamug.getText().toString();
                degree_grad=degreeug.getSelectedItem().toString();
                cgpa_grad=cgpaug.getText().toString();
// post graduation
                int selectedIdpg = rg_pgrad.getCheckedRadioButtonId();
                RadioButton radioButtonpg = test4View.findViewById(selectedIdpg);
                if(radioButtonpg.getText().equals("Pursuing")){
                    status_pgrad=0;
                }
                else{
                    status_pgrad=1;
                }
                college_pgrad=collegepg.getText().toString();
                syear_pgrad = startYearpg.getSelectedItem().toString();
                eyear_pgrad=endYearpg.getSelectedItem().toString();
                stream_pgrad=streampg.getText().toString();
                degree_pgrad=degreepg.getSelectedItem().toString();
                cgpa_pgrad=cgpapg.getText().toString();


//xii
                int selectedIdxii = rg_xii.getCheckedRadioButtonId();
                RadioButton radioButtonxii = test2View.findViewById(selectedIdxii);
                if(radioButtonxii.getText().equals("Pursuing")){
                    status_xii=0;
                }
                else{
                    status_xii=1;
                }
                school_xii=schoolsxii.getText().toString();
                eyear_xii=y_o_c_xii.getSelectedItem().toString();
                stream_xii=streamxii.getText().toString();
                board_xii=board.getSelectedItem().toString();
                cgpa_xii=cgpaxii.getText().toString();

                //x
                int selectedIdx = rg_x.getCheckedRadioButtonId();
                RadioButton radioButtonx = test3View.findViewById(selectedIdx);
                if(radioButtonx.getText().equals("Pursuing")){
                    status_x=0;
                }
                else{
                    status_x=1;
                }
                school_x=schoolsx.getText().toString();
                eyear_x=y_o_c_x.getSelectedItem().toString();
                board_x=boardx.getSelectedItem().toString();
                cgpa_x=cgpax.getText().toString();
//job1
                profile_j1=profilej1.getText().toString();
                organisation_j1=organisationj1.getText().toString();
                location_j1=locationj1.getText().toString();
                sdate_j1=sdatej1;
                edate_j1=edatej1;
                description_j1=descriptionj1.getText().toString();
                if(cwhj1.isChecked()){cwh_j1=1;}
//job2
                profile_j2=profilej2.getText().toString();
                organisation_j2=organisationj2.getText().toString();
                location_j2=locationj2.getText().toString();
                sdate_j2=sdatej2;
                edate_j2=edatej2;
                description_j2=descriptionj2.getText().toString();
                if(cwhj2.isChecked()){cwh_j2=1;}
//intern1
                int selectedIdi1 = rg_i1.getCheckedRadioButtonId();
                RadioButton radioButtoni1 = test6View.findViewById(selectedIdi1);
                if(radioButtoni1.getText().equals("Pursuing")){
                    type_i1=0;
                }
                else{
                    type_i1=1;
                }
                profile_i1=profilei1.getText().toString();
                organisation_i1=organisationi1.getText().toString();
                location_i1=locationi1.getText().toString();
                sdate_i1=sdatei1;
                edate_i1=edatei1;
                description_i1=descriptioni1.getText().toString();
                if(cwhi1.isChecked()){cwh_i1=1;}
//intern2
                int selectedIdi2 = rg_i2.getCheckedRadioButtonId();
                RadioButton radioButtoni2 = test7View.findViewById(selectedIdi2);
                if(radioButtoni2.getText().equals("Pursuing")){
                    type_i2=0;
                }
                else{
                    type_i2=1;
                }
                profile_i2=profilei2.getText().toString();
                organisation_i2=organisationi2.getText().toString();
                location_i2=locationi2.getText().toString();
                sdate_i2=sdatei2;
                edate_i2=edatei2;
                description_i2=descriptioni2.getText().toString();
                if(cwhi2.isChecked()){cwh_i2=1;}
// project1
                title_p1=titlep1.getText().toString();
                smonth_p1=sdatep1;
                emonth_p1=edatep1;
                description_p1=descriptionp1.getText().toString();
                projectlink_p1=projectlinkp1.getText().toString();
                if(cwhp1.isChecked()){cwh_p1=1;}
// project2
                title_p2=titlep2.getText().toString();
                smonth_p2=sdatep2;
                emonth_p2=edatep2;
                description_p2=descriptionp2.getText().toString();
                projectlink_p2=projectlinkp2.getText().toString();

                if(cwhp2.isChecked()){cwh_p2=1;}// skills
               /* cjava.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){java=1;}
                    }
                });
                cc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){c=1;}
                    }
                });
                ccpp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){cpp=1;}
                    }
                });
                cjavascript.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){javascript=1;}
                    }
                });
                cjquery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){jquery=1;}
                    }
                });


                chtml.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){html=1;}
                    }
                }); ccss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){css=1;}
                    }
                }); candroid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){android1=1;}
                    }
                }); cunity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){unity=1;}
                    }
                });
                ccoral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){coral=1;}
                    }
                }); cpython.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){python=1;}
                    }
                }); cr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){r=1;}
                    }
                }); cml.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){ml=1;}
                    }
                }); cai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){ai=1;}
                    }
                });
                cdata.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){data=1;}
                    }
                });
               */
                if(cjava.isChecked()){java=1;}
                 if(cc.isChecked()){c=1;}
                   if(ccpp.isChecked()){cpp=1;}
                    if(cjavascript.isChecked()){javascript=1;}
                       if(cjquery.isChecked()){jquery=1;}
                        if(chtml.isChecked()){html=1;}
                         if(ccss.isChecked()){css=1;}
                           if(candroid.isChecked()){android1=1;}
                           if(cunity.isChecked()){unity=1;}
                if(ccoral.isChecked()){coral=1;}
                if(cpython.isChecked()){python=1;}
                if(cr.isChecked()){r=1;}
                if(cml.isChecked()){ml=1;}
                if(cai.isChecked()){ai=1;}
                if(cdata.isChecked()){data=1;}

// additional
                description_add=addition.getText().toString();
              /* if(bitmap == null){
                    Toast.makeText(getContext(), "Can't submit without attaching pic", Toast.LENGTH_SHORT).show();
                }*/
                  if (name.equals("") || mob.equals("") || s_city.equals("") || c_city.equals("")|| emaill.equals("")) {
                    Toast.makeText(getContext(), "Enter Personal Details", Toast.LENGTH_SHORT).show();
                } else if (college_grad.isEmpty() || stream_grad.isEmpty() || cgpa_grad.isEmpty()) {
                    Toast.makeText(getContext(), "Enter Graduation Details", Toast.LENGTH_SHORT).show();
                } else if ((Integer.parseInt(syear_grad) == Integer.parseInt(eyear_grad))) {
                    Toast.makeText(getContext(), "Enter Valid Graduation Year Detaills", Toast.LENGTH_SHORT).show();
                } else if (school_xii.isEmpty() || stream_xii.isEmpty() || cgpa_xii.isEmpty()) {
                    Toast.makeText(getContext(), "Enter Intermediate Details", Toast.LENGTH_SHORT).show();
                } else if (school_x.isEmpty() || cgpa_x.isEmpty()) {
                    Toast.makeText(getContext(), "Enter Secondary Details", Toast.LENGTH_SHORT).show(); }
                else if ((college_pgrad.isEmpty() || stream_pgrad.isEmpty() || cgpa_pgrad.isEmpty()) &&(pg_s.equals("1"))){

                    Toast.makeText(getContext(), "Enter Post Graduation Details", Toast.LENGTH_SHORT).show();
                } else if ((Integer.parseInt(syear_pgrad) == Integer.parseInt(eyear_pgrad))&&(pg_s.equals("1"))) {
                   if(pg_s.equals("1")){
                    Toast.makeText(getContext(), "Enter Valid Post Graduation Year Detaills", Toast.LENGTH_SHORT).show(); }}
                 else if((Integer.parseInt(eyear_xii)-Integer.parseInt(eyear_x))!=2){
                    Toast.makeText(getContext(),"Year of completion of Secondary " +
                            "should be two years before year of completion of Intermediate",Toast.LENGTH_SHORT).show();
                } else if(!(Integer.parseInt(syear_grad)>=Integer.parseInt(eyear_xii))){
                    Toast.makeText(getContext(),"Start Year for UG cannot be less than " +
                            "year of completion for Intermediate",Toast.LENGTH_SHORT).show();}
              /*  else if(!(Integer.parseInt(syear_pgrad)>=Integer.parseInt(eyear_grad))){
                   if(pg_s.equals("1")){
                    Toast.makeText(getContext(),"Start Year for PG cannot be less than end year gor UG",Toast.LENGTH_SHORT).show();}
                }*/
               else if((profile_j1.isEmpty()|| organisation_j1.isEmpty()
                        || location_j1.isEmpty() || description_j1.isEmpty() || sdate_j1.isEmpty() || edate_j1.isEmpty())&&((j_s.equals("11"))||(j_s.equals("10")))){

                    Toast.makeText(getContext(), "Enter Job 1 Details", Toast.LENGTH_SHORT).show();
                }
               /* else if(sdate_j1.equals(edate_j1)){
                    Toast.makeText(getContext(), "Start and end date for Job 1 should not be same", Toast.LENGTH_SHORT).show();
                }*/
               else if((profile_j2.isEmpty()|| organisation_j2.isEmpty()
                        || location_j2.isEmpty() || description_j2.isEmpty() || sdate_j2.isEmpty() ||
                       edate_j2.isEmpty())&&(j_s.equals("11"))){
                    Toast.makeText(getContext(), "Enter Job 2 Details", Toast.LENGTH_SHORT).show();
                }
               /* else if(sdate_j2.equals(edate_j2)){
                    Toast.makeText(getContext(), "Start and end date for Job 2 should not be same", Toast.LENGTH_SHORT).show();
                }*/
               else if((profile_i1.isEmpty()|| organisation_i1.isEmpty()
                        || location_i1.isEmpty() || description_i1.isEmpty() || sdate_i1.isEmpty()
                       || edate_i1.isEmpty())&&((i_s.equals("11"))||(i_s.equals("10")))){
                    Toast.makeText(getContext(), "Enter internship 1 Details", Toast.LENGTH_SHORT).show();
                }
              /*  else if(sdate_i1.equals(edate_i1)){
                    Toast.makeText(getContext(), "Start and end date for Internship 1 should not be same", Toast.LENGTH_SHORT).show();
                }*/
               else if((profile_i2.isEmpty()|| organisation_i2.isEmpty()
                        || location_i2.isEmpty() || description_i2.isEmpty() || sdate_i2.isEmpty()
                       || edate_i2.isEmpty())&&(i_s.equals("11"))){
                    Toast.makeText(getContext(), "Enter Internship 2 Details", Toast.LENGTH_SHORT).show();
                }
             /*   else if(sdate_i2.equals(edate_i2)){
                    Toast.makeText(getContext(), "Start and end date for Internship 2 should not be same", Toast.LENGTH_SHORT).show();
                }*/
               else if((title_p1.isEmpty()|| description_p1.isEmpty() || sdatep1.isEmpty() ||
                       edatep1.isEmpty())&&((p_s.equals("11"))||(p_s.equals("10")))){
                    Toast.makeText(getContext(), "Enter Project 1 Details", Toast.LENGTH_SHORT).show();
                }
           /*     else if(sdatep1.equals(edatep1)){
                    Toast.makeText(getContext(), "Start and end date for project 1 should not be same", Toast.LENGTH_SHORT).show();
                }*/
               else if((title_p2.isEmpty()|| description_p2.isEmpty() || sdatep2.isEmpty() || edatep2.isEmpty())&&(p_s.equals("11"))){
                    Toast.makeText(getContext(), "Enter Project 2 Details", Toast.LENGTH_SHORT).show();
                }
              /*  else if(sdatep1.equals(edatep2)){
                    Toast.makeText(getContext(), "Start and end date for project 2 should not be same", Toast.LENGTH_SHORT).show();
                }*/
              else if((description_add.isEmpty()&&(add_s.equals("1")))) {
                    Toast.makeText(getContext(),"Enter Additional Detaills",Toast.LENGTH_SHORT).show();
                }

                else {
               // Toast.makeText(getContext(),pg_s+"_"+add_s+"_"+i_s+"_"+j_s+"_"+ p_s,Toast.LENGTH_SHORT).show();
                ud.sequence("grudge");
              //  ud.insertprofileimage(myBase64Image);
                ud.insert_status_list(pg_s,add_s,i_s,j_s,p_s);
                ud.insertpersonaldetails(name,mob,c_city,s_city,emaill,linkedinn);
                ud.insertgraduation(status_grad,college_grad,syear_grad,eyear_grad,degree_grad,stream_grad,cgpa_grad);
                ud.insertxii(status_xii,school_xii,eyear_xii,board_xii,stream_xii,cgpa_xii);
                ud.insertsecondaryx(status_x,school_x,eyear_x,board_x,cgpa_x);
                ud.insertpostgraduation(status_pgrad,college_pgrad,syear_pgrad,eyear_pgrad,degree_pgrad,stream_pgrad,cgpa_pgrad);
                ud.insertjob1(profile_j1,organisation_j1,location_j1,cwh_j1,edate_j1,sdate_j1,description_j1);
                ud.insertjob2(profile_j2,organisation_j2,location_j2,cwh_j2,edate_j2,sdate_j2,description_j2);
                ud.insertintern1(type_i1,profile_i1,organisation_i1,location_i1,cwh_i1,edate_i1,sdate_i1,description_i1);
                ud.insertintern2(type_i2,profile_i2,organisation_i2,location_i2,cwh_i2,edate_i2,sdate_i2,description_i2);
                ud.insertproject1(title_p1,projectlink_p1,cwh_p1,emonth_p1,smonth_p1,description_p1);
                ud.insertproject2(title_p2,projectlink_p2,cwh_p2,emonth_p2,smonth_p2,description_p2);
                ud.insertskills(java,c,cpp,html,css,javascript,jquery,python,android1,unity,coral,r,ml,ai,data);
                ud.insertadditional(description_add);

                SharedPreferences loginData = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = loginData.edit();
                editor.putString("attend_idd", "last");
                editor.apply();
                SharedPreferences prefs1 = getActivity().getSharedPreferences("fragment_name", MODE_PRIVATE);
                fragmentname = prefs1.getString("name_main", "No name defined");
                String holder_name=fragmentname;
                SharedPreferences loginData1 = getActivity().getSharedPreferences("fragment_name", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = loginData1.edit();
                editor1.putString("name_main", holder_name);
                editor1.putString("view_status",holder_name);
                editor1.putString("viewas","same");
                editor1.putString("create","no");
                editor1.apply();
                      Fragment   fragment = new ProfileFragment();
                      loadFragment(fragment);
                     showInterstitial();
               }
            }


    });

    /*    img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickerPhotoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickerPhotoIntent, 1);
            }
        });*/
        return view;
    }
    public void loadFragment(Fragment fragment) {
        // load fragment

        int count = myactivity.getSupportFragmentManager().getBackStackEntryCount();
      //  Toast.makeText(myactivity.getApplicationContext(),count+"Added",Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
       /* if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 4) {
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }*/
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if(requestCode == RESULT_OK) {
                    Log.i("MainActivity", "case 0");
                }
                break;
            case 1:
                if(resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i("MainActivity", "selected Image = "+selectedImage);
                    Glide.with(this).load(selectedImage).into(iv);
                   // this.iv.setImageURI(selectedImage);
                    Bitmap converetdImage = getResizedBitmap(bitmap, 500);
                   // iv.setImageBitmap(bitmap);
                    try {
                        saveToInternalStorage(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myBase64Image = encodeToBase64(converetdImage, Bitmap.CompressFormat.JPEG, 80);
                   // image_blob=getBytes(bitmap);
                }
                break;
        }
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width-30, height-50, true);
    }
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
    private String saveToInternalStorage(Bitmap bitmapImage) throws IOException {
        ContextWrapper cw = new ContextWrapper(getContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        ExifInterface ei = new ExifInterface(String.valueOf(mypath));
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch(orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                bitmapImage = rotateImage(bitmapImage, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                bitmapImage = rotateImage(bitmapImage, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                bitmapImage = rotateImage(bitmapImage, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                bitmapImage = bitmapImage;
        }
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
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
      /* private void loadImageFromStorage()
    {

        try {
            ContextWrapper cw = new ContextWrapper(getContext());
            File directory = cw.getFilesDir();
            File file = new File(directory, "profile.jpg");
           // File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(file));
            iv.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }*/

}

