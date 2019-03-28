package com.utsavgupta.cvbuilder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.utsavgupta.cvbuilder.R;

import java.util.List;


public class BlockPruebaAdapter extends RecyclerView.Adapter<BlockPruebaAdapter.PruebaViewHolder> {
    private User_details ud;
    List<String> names,times,attend_id,types;
    AlertDialog.Builder builder1;
    String[] escrito;
    ProgressDialog dialog;
    private Context context;
    private MainActivity myactivity;

    public BlockPruebaAdapter(List<String> names, List<String> times, List<String> attend_id,List<String> type) {
  this.names=names;
  this.times=times;
  this.attend_id=attend_id;
  this.types=type;

      //escrito = new String[lista.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_teacher_status_item_row,parent,false);
        this.ud=new User_details(v.getContext());
        context=v.getContext();
        myactivity=(MainActivity)context;
        return new PruebaViewHolder(v);

    }

    @Override
    public void onBindViewHolder(PruebaViewHolder holder, int position) {
         String name=names.get(position);
         String time=times.get(position);
         String attend_ids=attend_id.get(position);
         String type=types.get(position);
         holder.bindProducto(name,time,attend_ids,type);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{
        String[] img_name={"____View as____","Sample ","Default ","Classic ","Impressive ","Impressive Pro ","Impressive Pro1 ","Modern "};
        String fragmentname;
        TextView name,time;
        TextView view,delete;Spinner viewas;
        int accuracyt,slot_idt,taken_by_idt,school_idt,marked_by_id,marked_type=1;
        String remarkt,datett,marked_ont,datet;String message,status;
        double lattitudet,longitudet;
        String user_idt="",attendencestatust="";
        public PruebaViewHolder( View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.timestamp);
            view = itemView.findViewById(R.id.view);
            viewas=itemView.findViewById(R.id.viewas);
            delete = itemView.findViewById(R.id.delete);
            dialog=new ProgressDialog(itemView.getContext());
        }

        public void bindProducto(final String blockname, String schoolname, final String attend_ids, final String type) {


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {


                    builder1 = new AlertDialog.Builder(view.getContext());
                    builder1.setCancelable(false);

                    builder1.setTitle("Your CV will be deleted permanently!!");
                    builder1.setMessage("Are you sure?");

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                ud.deleteEntry(Integer.parseInt(attend_ids));
                                    // startActivity(new Intent(getContext(),BlockTabbedteacher.class));

                                    //delete.setVisibility(View.GONE);
                                dialog.cancel();

                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Fragment   fragment = new ProfileFragment();
                    loadFragment(fragment);
                    SharedPreferences loginData = myactivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginData.edit();
                    editor.putString("attend_idd", attend_ids);
                    editor.apply();
                    SharedPreferences loginData1 = myactivity.getSharedPreferences("fragment_name", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = loginData1.edit();
                    editor1.putString("name_main", blockname);
                    editor1.putString("view_status",type);
                    editor1.putString("viewas","same");
                    editor1.putString("for_profile_fragment_transaction","gift");
                    editor1.putString("create","no");
                    editor1.apply();
                }
            });
           /* viewas.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     ArrayAdapter<String> boards = new ArrayAdapter<String>(myactivity, android.R.layout.simple_spinner_item, img_name);
                     final Spinner board = new Spinner(myactivity);
                     board.setAdapter(boards);
                     fragmentname=board.getSelectedItem().toString();
                     Toast.makeText(myactivity.getApplicationContext(),fragmentname,Toast.LENGTH_SHORT).show();
                 }
             });*/
            ArrayAdapter<String> boards = new ArrayAdapter<String>(myactivity, android.R.layout.simple_spinner_dropdown_item, img_name);
            viewas.setAdapter(boards);

            viewas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                                           long arg3) {

                    fragmentname = arg0.getItemAtPosition(position).toString();
                    if (!(fragmentname.equals("____View as____"))) {
                        if(!(fragmentname.equals(type))) {

                            SharedPreferences loginData = myactivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = loginData.edit();
                            editor.putString("attend_idd", attend_ids);
                            editor.apply();
                            SharedPreferences loginData1 = myactivity.getSharedPreferences("fragment_name", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = loginData1.edit();
                            editor1.putString("name_main", blockname);
                            editor1.putString("view_status", fragmentname);
                            editor1.putString("viewas", "other");
                            editor1.putString("for_profile_fragment_transaction", "gift");
                            editor1.putString("create", "no");
                            editor1.apply();
                            Fragment fragment = new ProfileFragment();
                            loadFragment(fragment);
                            //Toast.makeText(myactivity.getApplicationContext(), fragmentname, Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Fragment   fragment = new ProfileFragment();
                            loadFragment(fragment);
                            SharedPreferences loginData = myactivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = loginData.edit();
                            editor.putString("attend_idd", attend_ids);
                            editor.apply();
                            SharedPreferences loginData1 = myactivity.getSharedPreferences("fragment_name", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = loginData1.edit();
                            editor1.putString("name_main", blockname);
                            editor1.putString("view_status",type);
                            editor1.putString("viewas","same");
                            editor1.putString("for_profile_fragment_transaction","gift");
                            editor1.putString("create","no");
                            editor1.apply();
                        }
                    }
                }

                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });
        name.setText(blockname);
        time.setText(schoolname);
        }


        }

    public void loadFragment(Fragment fragment) {
        // load fragment
     /*   if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 4) {
           getActivity().getSupportFragmentManager().popBackStackImmediate();
       }*/

        int count = myactivity.getSupportFragmentManager().getBackStackEntryCount();
      //  Toast.makeText(myactivity.getApplicationContext(),count+"Added",Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = myactivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
      /*  if (myactivity.getSupportFragmentManager().getBackStackEntryCount() > 4) {
            myactivity.getSupportFragmentManager().popBackStackImmediate();
        }*/
    }
   /* private void createPdf(){
        WindowManager wm = (WindowManager) myactivity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        myactivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            page = document.startPage(pageInfo);
        }

        Canvas canvas = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            canvas = page.getCanvas();
        }


        Paint paint = new Paint();
        canvas.drawPaint(paint);


        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            document.finishPage(page);
        }


        // write the document content
        String targetPdf = "/sdcard/testa3.pdf";
        File filePath = new File(targetPdf);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                document.writeTo(new FileOutputStream(filePath));
            }
            btn_generate.setText("Check PDF");
            boolean_save=true;
        } catch (IOException e) {
            e.printStackTrace();
         //   Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            document.close();
        }
    }*/
   /* private Bitmap takeScreenShot()
    {
        View u = (findViewById(R.id.scroll));

        ScrollView z = (ScrollView) findViewById(R.id.scroll);
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
            MediaStore.Images.Media.insertImage(getContentResolver(), b, "Screen", "screen");
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  b;

    }*/


}

