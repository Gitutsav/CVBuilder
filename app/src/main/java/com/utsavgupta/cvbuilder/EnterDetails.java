package com.utsavgupta.cvbuilder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EnterDetails extends Activity {
TextView personal,education,jobs,interns,por,trainings,projects,skills,additionals;
LinearLayout pers,edu,job,intern,po,train,pro,ski,add;
    List<String> city_names=new ArrayList<>();
    private com.utsavgupta.cvbuilder.User_details ud;
   //Inserting----------------------
    Button submit;EditText ename,emob,addition;
    private AutoCompleteTextView autoTextView,currentcity,secondcity;
    String[] fruits = {"Apple", "Banana", "Cherry", "Date","Dates", "Grape", "Kiwi", "Mango", "Pear"};
    String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
    String[] cities,states,colleges,degrees,streamug,degreespg,schools;
    StringBuffer stringBuffer;
    String country="";
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener1,mDateSetListener2,mDateSetListener3,
            mDateSetListener4,mDateSetListener5,mDateSetListener6,mDateSetListener7,
    mDateSetListener8,mDateSetListener9,mDateSetListener10,mDateSetListener11;
    private com.utsavgupta.cvbuilder.PermissionsChecker checker;
    TextView ug,xii,x,pg,  j1,j2  ,i1,i2,  p1,p2;
    String[] board={"ICSE","ISC","CBSE","Government Boards"};
    String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] streamxii={"Science(with Mathematics)","Science(with Biology)","Arts","Commerce(with Mathematics)","Commerce(with Economics)"};
    private String edatej2;
    private String sdatej2;
    private String sdatej1,edatej1; private String sdatei1,edatei1;
    private String sdatep1,edatep1; private String sdatep2,edatep2;
    private String sdatei2,edatei2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        submit=findViewById(R.id.submitx);
        ename=findViewById(R.id.name);
        emob=findViewById(R.id.mob);
        addition=findViewById(R.id.additional);
        personal=findViewById(R.id.personal);
        education=findViewById(R.id.educ);
        pers=findViewById(R.id.pers);
        edu=findViewById(R.id.edu);
        job=findViewById(R.id.job);
        jobs=findViewById(R.id.jobinfo);
        interns=findViewById(R.id.internshipinfo);
        projects=findViewById(R.id.projectinfo);
        skills=findViewById(R.id.skillinfo);
        additionals=findViewById(R.id.additionalinfo);
        ud=new User_details(getApplicationContext());

        intern=findViewById(R.id.internship);
        pro=findViewById(R.id.project);
        ski=findViewById(R.id.skill);
        add=findViewById(R.id.additionals);
        cities=getResources().getStringArray(R.array.india_top_places);
        states=getResources().getStringArray(R.array.india_states);
        colleges=getResources().getStringArray(R.array.india_top_colleges);
        degrees=getResources().getStringArray(R.array.degress);
        degreespg=getResources().getStringArray(R.array.degreepg);
        streamug=getResources().getStringArray(R.array.streamug);
        schools=getResources().getStringArray(R.array.schools);
        ug=findViewById(R.id.ug);
        xii=findViewById(R.id.xii);
        x=findViewById(R.id.x);
        pg=findViewById(R.id.pg);

        j1=findViewById(R.id.j1);
        j2=findViewById(R.id.j2);

        i1=findViewById(R.id.i1);
        i2=findViewById(R.id.i2);

        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);
//Creating the instance of ArrayAdapter containing list of fruit names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,cities);
        ArrayAdapter<String> adapter_colleges = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,colleges);
        ArrayAdapter<String> adapter_streamxii = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,streamxii);
        ArrayAdapter<String> adapter_streamug = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,streamug);
        ArrayAdapter<String> adapter_schools = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,schools);

        //Getting the instance of AutoCompleteTextView
        currentcity=findViewById(R.id.current_city);
        currentcity.setAdapter(adapter);
        currentcity.setThreshold(1);
        secondcity=findViewById(R.id.second_city);
        secondcity.setAdapter(adapter);
        secondcity.setThreshold(1);
        /*AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);*/
        stringBuffer=new StringBuffer();
        addMenuItemsFromJson();
       // country=country+stringBuffer.toString();
        //checker = new PermissionsChecker(this);

       // createPdf(FileUtils.getAppPath(getApplicationContext()) + "12345.pdf");
        //Adding years to the start year spinner for graduation

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1990; i <= thisYear+10; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter_s_years = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        final View test1View = findViewById(R.id.test1);
        final View test2View = findViewById(R.id.test2);
        final View test3View = findViewById(R.id.test3);
        final View test4View = findViewById(R.id.test4);
        final View test8View = findViewById(R.id.test8);
        final View test9View = findViewById(R.id.test9);
        final View test5View = findViewById(R.id.test5);
        final View test6View = findViewById(R.id.test6);
        final View test7View = findViewById(R.id.test7);
        final View test10View = findViewById(R.id.test10);
        final View test11View = findViewById(R.id.test11);
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
        endmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
                        EnterDetails.this,
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
        ArrayAdapter<String> degrees_adap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, degrees);
        final Spinner degreeug = (Spinner)test1View.findViewById(R.id.spinner);
        degreeug.setAdapter(degrees_adap);
        ArrayAdapter<String> degrees_adapg = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, degreespg);
        final Spinner degreepg = (Spinner)test4View.findViewById(R.id.spinner);
        degreepg.setAdapter(degrees_adapg);
        ArrayAdapter<String> boards = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, board);
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
                if(pers.getVisibility()== View.VISIBLE) { pers.setVisibility(View.GONE);  }
                else if(pers.getVisibility()== View.GONE){pers.setVisibility(View.VISIBLE);}
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edu.getVisibility()== View.VISIBLE) {edu.setVisibility(View.GONE);  }
                else if(edu.getVisibility()== View.GONE){edu.setVisibility(View.VISIBLE); }
            }
        });


            ug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edu.getVisibility()== View.VISIBLE){
                    if(test1View.getVisibility()== View.VISIBLE) {test1View.setVisibility(View.GONE);  }
                    else if(test1View.getVisibility()== View.GONE){test1View.setVisibility(View.VISIBLE); }
                }}
            });
            xii.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edu.getVisibility()== View.VISIBLE){
                    if(test2View.getVisibility()== View.VISIBLE) {test2View.setVisibility(View.GONE);  }
                    else if(test2View.getVisibility()== View.GONE){test2View.setVisibility(View.VISIBLE); }
                }}
            });
            x.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edu.getVisibility()== View.VISIBLE){
                    if(test3View.getVisibility()== View.VISIBLE) {test3View.setVisibility(View.GONE);  }
                    else if(test3View.getVisibility()== View.GONE){test3View.setVisibility(View.VISIBLE); }
                }}
            });
            pg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edu.getVisibility()== View.VISIBLE){
                    if(test4View.getVisibility()== View.VISIBLE) {test4View.setVisibility(View.GONE);  }
                    else if(test4View.getVisibility()== View.GONE){test4View.setVisibility(View.VISIBLE); }
                }}
            });

        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(job.getVisibility()== View.VISIBLE) {job.setVisibility(View.GONE);  }
                else if(job.getVisibility()== View.GONE){job.setVisibility(View.VISIBLE); }
            }
        });
        j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(job.getVisibility()== View.VISIBLE){
                    if(test8View.getVisibility()== View.VISIBLE) {test8View.setVisibility(View.GONE);  }
                    else if(test8View.getVisibility()== View.GONE){test8View.setVisibility(View.VISIBLE); }
                }}
        });
        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(job.getVisibility()== View.VISIBLE){
                    if(test9View.getVisibility()== View.VISIBLE) {test9View.setVisibility(View.GONE);  }
                    else if(test9View.getVisibility()== View.GONE){test9View.setVisibility(View.VISIBLE); }
                }}
        });


        interns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intern.getVisibility()== View.VISIBLE) {intern.setVisibility(View.GONE);  }
                else if(intern.getVisibility()== View.GONE){intern.setVisibility(View.VISIBLE); }
            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intern.getVisibility()== View.VISIBLE){
                    if(test6View.getVisibility()== View.VISIBLE) {test6View.setVisibility(View.GONE);  }
                    else if(test6View.getVisibility()== View.GONE){test6View.setVisibility(View.VISIBLE); }
                }}
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intern.getVisibility()== View.VISIBLE){
                    if(test7View.getVisibility()== View.VISIBLE) {test7View.setVisibility(View.GONE);  }
                    else if(test7View.getVisibility()== View.GONE){test7View.setVisibility(View.VISIBLE); }
                }}
        });
        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pro.getVisibility()== View.VISIBLE) {pro.setVisibility(View.GONE);  }
                else if(pro.getVisibility()== View.GONE){pro.setVisibility(View.VISIBLE); }
            }
        });
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pro.getVisibility()== View.VISIBLE){
                    if(test10View.getVisibility()== View.VISIBLE) {test10View.setVisibility(View.GONE);  }
                    else if(test10View.getVisibility()== View.GONE){test10View.setVisibility(View.VISIBLE); }
                }}
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pro.getVisibility()== View.VISIBLE){
                    if(test11View.getVisibility()== View.VISIBLE) {test11View.setVisibility(View.GONE);  }
                    else if(test11View.getVisibility()== View.GONE){test11View.setVisibility(View.VISIBLE); }
                }}
        });
        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ski.getVisibility()== View.VISIBLE) {ski.setVisibility(View.GONE);  }
                else if(ski.getVisibility()== View.GONE){ski.setVisibility(View.VISIBLE); }
            }
        });
        additionals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add.getVisibility()== View.VISIBLE) {add.setVisibility(View.GONE);  }
                else if(add.getVisibility()== View.GONE){add.setVisibility(View.VISIBLE); }
            }
        });

   // Inserting into the local db
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "", mob = "", c_city = "", s_city = "",
                        college_grad = "", syear_grad = "", eyear_grad = "", degree_grad = "", stream_grad = "", cgpa_grad = "",
                        college_pgrad = "", syear_pgrad = "", eyear_pgrad = "", degree_pgrad = "", stream_pgrad = "", cgpa_pgrad = "",
                        school_xii = "", eyear_xii = "", board_xii = "", stream_xii = "", cgpa_xii = "",
                        school_x = "", eyear_x = "", board_x = "", stream_x = "", cgpa_x = "",
                        profile_j1 = "", organisation_j1 = "", edate_j1 = "", sdate_j1 = "", location_j1 = "", description_j1 = "",
                        profile_j2 = "", organisation_j2 = "", edate_j2 = "", sdate_j2 = "", location_j2 = "", description_j2 = "",
                        profile_i1 = "", organisation_i1 = "", edate_i1 = "", sdate_i1 = "", location_i1 = "", description_i1 = "",
                        profile_i2 = "", organisation_i2 = "", edate_i2 = "", sdate_i2 = "", location_i2 = "", description_i2 = "",
                        title_p1 = "", emonth_p1 = "", smonth_p1 = "", projectlink_p1 = "", description_p1 = "",
                        title_p2 = "", emonth_p2 = "", smonth_p2 = "", projectlink_p2 = "", description_p2 = "",
                        description_add = "";
                int status_grad = 0, status_pgrad = 0, status_xii = 0, status_x = 0, cwh_j1 = 0,
                        cwh_j2 = 0, cwh_i1 = 0, cwh_i2 = 0, type_i1 = 0, type_i2 = 0, cwh_p1 = 0, cwh_p2 = 0;
                int java = 0, c = 0, cpp = 0, html = 0,
                        ml=0,ai=0,data=0,css = 0, javascript = 0, jquery = 0, python = 0, android = 0, unity = 0, coral = 0, r = 0;
//personal details
                name = ename.getText().toString();
                mob = emob.getText().toString();
                c_city = currentcity.getText().toString();
                s_city = secondcity.getText().toString();

//graduation
                int selectedId = rg_grad.getCheckedRadioButtonId();
                RadioButton radioButton = test1View.findViewById(selectedId);
                if (radioButton.getText().equals("Pursuing")) {
                    status_grad = 0;
                } else {
                    status_grad = 1;
                }
                college_grad = collegeug.getText().toString();
                syear_grad = startYear.getSelectedItem().toString();
                eyear_grad = endYear.getSelectedItem().toString();
                stream_grad = streamug.getText().toString();
                degree_grad = degreeug.getSelectedItem().toString();
                cgpa_grad = cgpaug.getText().toString();
// post graduation
                int selectedIdpg = rg_pgrad.getCheckedRadioButtonId();
                RadioButton radioButtonpg = test4View.findViewById(selectedIdpg);
                if (radioButtonpg.getText().equals("Pursuing")) {
                    status_pgrad = 0;
                } else {
                    status_pgrad = 1;
                }
                college_pgrad = collegepg.getText().toString();
                syear_pgrad = startYearpg.getSelectedItem().toString();
                eyear_pgrad = endYearpg.getSelectedItem().toString();
                stream_pgrad = streampg.getText().toString();
                degree_pgrad = degreepg.getSelectedItem().toString();
                cgpa_pgrad = cgpapg.getText().toString();


//xii
                int selectedIdxii = rg_xii.getCheckedRadioButtonId();
                RadioButton radioButtonxii = test2View.findViewById(selectedIdxii);
                if (radioButtonxii.getText().equals("Pursuing")) {
                    status_xii = 0;
                } else {
                    status_xii = 1;
                }
                school_xii = schoolsxii.getText().toString();
                eyear_xii = y_o_c_xii.getSelectedItem().toString();
                stream_xii = streamxii.getText().toString();
                board_xii = board.getSelectedItem().toString();
                cgpa_xii = cgpaxii.getText().toString();

                //x
                int selectedIdx = rg_x.getCheckedRadioButtonId();
                RadioButton radioButtonx = test3View.findViewById(selectedIdx);
                if (radioButtonx.getText().equals("Pursuing")) {
                    status_x = 0;
                } else {
                    status_x = 1;
                }
                school_x = schoolsx.getText().toString();
                eyear_x = y_o_c_x.getSelectedItem().toString();
                board_x = boardx.getSelectedItem().toString();
                cgpa_x = cgpax.getText().toString();
//job1
                profile_j1 = profilej1.getText().toString();
                organisation_j1 = organisationj1.getText().toString();
                location_j1 = locationj1.getText().toString();
                sdate_j1 = sdatej1;
                edate_j1 = edatej1;
                description_j1 = descriptionj1.getText().toString();
                if (cwhj1.isChecked()) {
                    cwh_j1 = 1;
                }
//job2
                profile_j2 = profilej2.getText().toString();
                organisation_j2 = organisationj2.getText().toString();
                location_j2 = locationj2.getText().toString();
                sdate_j2 = sdatej2;
                edate_j2 = edatej2;
                description_j2 = descriptionj2.getText().toString();
                if (cwhj2.isChecked()) {
                    cwh_j2 = 1;
                }
//intern1
                int selectedIdi1 = rg_i1.getCheckedRadioButtonId();
                RadioButton radioButtoni1 = test6View.findViewById(selectedIdi1);
                if (radioButtoni1.getText().equals("Pursuing")) {
                    type_i1 = 0;
                } else {
                    type_i1 = 1;
                }
                profile_i1 = profilei1.getText().toString();
                organisation_i1 = organisationi1.getText().toString();
                location_i1 = locationi1.getText().toString();
                sdate_i1 = sdatei1;
                edate_i1 = edatei1;
                description_i1 = descriptioni1.getText().toString();
                if (cwhi1.isChecked()) {
                    cwh_i1 = 1;
                }
//intern2
                int selectedIdi2 = rg_i2.getCheckedRadioButtonId();
                RadioButton radioButtoni2 = test7View.findViewById(selectedIdi2);
                if (radioButtoni2.getText().equals("Pursuing")) {
                    type_i2 = 0;
                } else {
                    type_i2 = 1;
                }
                profile_i2 = profilei2.getText().toString();
                organisation_i2 = organisationi2.getText().toString();
                location_i2 = locationi2.getText().toString();
                sdate_i2 = sdatei2;
                edate_i2 = edatei2;
                description_i2 = descriptioni2.getText().toString();
                if (cwhi2.isChecked()) {
                    cwh_i2 = 1;
                }
// project1
                title_p1 = titlep1.getText().toString();
                smonth_p1 = sdatep1;
                emonth_p1 = edatep1;
                description_p1 = descriptionp1.getText().toString();
                projectlink_p1 = projectlinkp1.getText().toString();
                if (cwhp1.isChecked()) {
                    cwh_p1 = 1;
                }
// project2
                title_p2 = titlep2.getText().toString();
                smonth_p2 = sdatep2;
                emonth_p2 = edatep2;
                description_p2 = descriptionp2.getText().toString();
                projectlink_p2 = projectlinkp2.getText().toString();
                if (cwhp2.isChecked()) {
                    cwh_p2 = 1;
                }
// skills
                if (cjava.isChecked()) {
                    java = 1;
                }
                if (cc.isChecked()) {
                    c = 1;
                }
                if (ccpp.isChecked()) {
                    cpp = 1;
                }
                if (cjavascript.isChecked()) {
                    javascript = 1;
                }
                if (cjquery.isChecked()) {
                    jquery = 1;
                }
                if (chtml.isChecked()) {
                    html = 1;
                }
                if (ccss.isChecked()) {
                    css = 1;
                }
                if (candroid.isChecked()) {
                    android = 1;
                }
                if (cunity.isChecked()) {
                    unity = 1;
                }
                if (ccoral.isChecked()) {
                    coral = 1;
                }
                if (cpython.isChecked()) {
                    python = 1;
                }
                if (cr.isChecked()) {
                    r = 1;
                }
// additional
               /*if (name.equals("") || mob.equals("") || s_city.equals("") || c_city.equals("")) {
                    Toast.makeText(EnterDetails.this, "Enter Personal Details", Toast.LENGTH_SHORT).show();
                } else if (college_grad.isEmpty() || stream_grad.isEmpty() || cgpa_grad.isEmpty()) {
                    Toast.makeText(EnterDetails.this, "Enter Graduation Details", Toast.LENGTH_SHORT).show();
                } else if (syear_grad.equals(eyear_grad) || Integer.parseInt(syear_grad) > Integer.parseInt(eyear_grad)) {
                    Toast.makeText(EnterDetails.this, "Enter Valid Graduation Year Detaills", Toast.LENGTH_SHORT).show();
                } else if (college_pgrad.isEmpty() || stream_pgrad.isEmpty() || cgpa_pgrad.isEmpty()) {
                    Toast.makeText(EnterDetails.this, "Enter Graduation Details", Toast.LENGTH_SHORT).show();
                } else if (syear_pgrad.equals(eyear_grad) || Integer.parseInt(syear_pgrad) > Integer.parseInt(eyear_pgrad)) {
                    Toast.makeText(EnterDetails.this, "Enter Valid Post Graduation Year Detaills", Toast.LENGTH_SHORT).show();
                } else if (school_xii.isEmpty() || stream_xii.isEmpty() || cgpa_xii.isEmpty()) {
                    Toast.makeText(EnterDetails.this, "Enter Intermediate Details", Toast.LENGTH_SHORT).show();
                } else if (school_x.isEmpty() || cgpa_x.isEmpty()) {
                    Toast.makeText(EnterDetails.this, "Enter Intermediate Details", Toast.LENGTH_SHORT).show();
                }
              else if(school_x.equals(eyear_grad) || Integer.parseInt(syear_grad)>Integer.parseInt(eyear_grad)){
                   Toast.makeText(EnterDetails.this,"Enter Valid Graduation Year Detaills",Toast.LENGTH_SHORT).show();
               }*/
            //  if(2==2){ Toast.makeText(EnterDetails.this, "Enter Personal Details", Toast.LENGTH_SHORT).show();}
             //   else {
                    description_add = addition.getText().toString();
                    ud.insertpersonaldetails(name, mob, c_city, s_city,"email","linkedin");
                    ud.insertgraduation(status_grad, college_grad, syear_grad, eyear_grad, degree_grad, stream_grad, cgpa_grad);
                    ud.insertxii(status_xii, school_xii, eyear_xii, board_xii, stream_xii, cgpa_xii);
                    ud.insertsecondaryx(status_x, school_x, eyear_x, board_x, cgpa_x);
                    ud.insertpostgraduation(status_pgrad, college_pgrad, syear_pgrad, eyear_pgrad, degree_pgrad, stream_pgrad, cgpa_pgrad);
                    ud.insertjob1(profile_j1, organisation_j1, location_j1, cwh_j1, edate_j1, sdate_j1, description_j1);
                    ud.insertjob2(profile_j2, organisation_j2, location_j2, cwh_j2, edate_j2, sdate_j2, description_j2);
                    ud.insertintern1(type_i1, profile_i1, organisation_i1, location_i1, cwh_i1, edate_i1, sdate_i1, description_i1);
                    ud.insertintern2(type_i2, profile_i2, organisation_i2, location_i2, cwh_i2, edate_i2, sdate_i2, description_i2);
                    ud.insertproject1(title_p1, projectlink_p1, cwh_p1, emonth_p1, smonth_p1, description_p1);
                    ud.insertproject2(title_p2, projectlink_p2, cwh_p2, emonth_p2, smonth_p2, description_p2);
                    ud.insertskills(java, c, cpp, html, css, javascript, jquery, python, android, unity, coral, r,ml,ai,data);
                    ud.insertadditional(description_add);

                }

});



    }

    private void addMenuItemsFromJson() {
        try {
            String jsonDataString = readJsonDataFromFile();
            JSONObject parentobj=new JSONObject(jsonDataString);
            JSONArray menuItemsJsonArray = parentobj.getJSONArray("Countries");

            //for (int i = 0; i < menuItemsJsonArray.length(); ++i) {

                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(100);
                String Country = menuItemObject.getString("CountryName");

                    JSONArray states = menuItemObject.getJSONArray("States");

                    for (int j = 0; j < states.length(); j++) {
                        JSONObject stateobj = states.getJSONObject(j);
                        String statename = stateobj.getString("StateName");
                        JSONArray citiesarray = stateobj.getJSONArray("Cities");
                        for (int k = 0; k < citiesarray.length(); k++) {
                            String city = (String) citiesarray.get(k);

                            stringBuffer.append( city + "_" + statename + ",");
                            city_names.add( city + "_" + statename);

                        }
                    }
          countries = city_names.toArray(new String[city_names.size()]);



              /*  String menuItemDescription = menuItemObject.getString("description");
                String menuItemPrice = menuItemObject.getString("price");
                String menuItemCategory = menuItemObject.getString("category");
                String menuItemImageName = menuItemObject.getString("photo");*/

               /* Pojo pojo = new Pojo(menuItemName, menuItemDescription, menuItemPrice,
                        menuItemCategory, menuItemImageName);
                mRecyclerViewItems.add(pojo);*/
            //}
        } catch (IOException | JSONException exception) {
            Log.e(MainActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
    }
    private String readJsonDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.contstatecity);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }
  /*  public void createPdf(String dest) {

        if (new File(dest).exists()) {
            new File(dest).delete();
        }

        try {
            /**
             * Creating Document
             */
           /* Document document = new Document();

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
           /* BaseColor mColorAccent = new BaseColor(0, 153, 204, 255);
            float mHeadingFontSize = 20.0f;
            float mValueFontSize = 26.0f;

            /**
             * How to USE FONT....
             */
          /*  BaseFont urName = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);

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
            Chunk mOrderIdValueChunk = new Chunk(country.toString(), mOrderIdValueFont);
            Paragraph mOrderIdValueParagraph = new Paragraph(mOrderIdValueChunk);




            document.add(mOrderIdValueParagraph);

            document.add(new Paragraph(""));
            document.add(new Chunk(lineSeparator));
            document.add(new Paragraph(""));



            document.close();

            Toast.makeText(getApplicationContext(), "Created... :)", Toast.LENGTH_SHORT).show();

            FileUtils.openFile(getApplicationContext(), new File(dest));

        } catch (IOException | DocumentException ie) {
            LOGE("createPdf: Error " + ie.getLocalizedMessage());
        } catch (ActivityNotFoundException ae) {
            Toast.makeText(getApplicationContext(), "No application found to open this file.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            Toast.makeText(getApplicationContext(), "Permission Granted to Save", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Permission not granted, Try again!", Toast.LENGTH_SHORT).show();
        }
    }*/
}
