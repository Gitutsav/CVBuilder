package com.utsavgupta.cvbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class User_details extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name

    private static final String DATABASE_NAME = "user_database1.db";
    private static final String TABLE_NAME_sequence = "sequence";
    private static final String TABLE_NAME_personal_details = "personal_details";
    private static final String TABLE_NAME_graduation = "graduation";
    private static final String TABLE_NAME_xii = "xii";
    private static final String TABLE_NAME_secondary_x = "secondary_x";
    private static final String TABLE_NAME_post_graduation = "post_graduation";
    private static final String TABLE_NAME_job1 = "job1";
    private static final String TABLE_NAME_job2 = "job2";
    private static final String TABLE_NAME_intern1 = "intern1";
    private static final String TABLE_NAME_intern2 = "intern2";
    private static final String TABLE_NAME_project1 = "project1";
    private static final String TABLE_NAME_project2 = "project2";
    private static final String TABLE_NAME_skills = "skills";
    private static final String TABLE_NAME_additional = "additional";
    private static final String TABLE_NAME_pic = "profile_image";
    private static final String TABLE_NAME_cv_list= "cv_list";
    private static final String TABLE_NAME_status_list="status_list";

    private static String sequence="create table sequence(user_id integer primary key autoincrement, "+" data text); ";
    private static String cv_list =
            "create table  cv_list (user_id integer primary key autoincrement, " +"attend_id text,"+"date_time text,"+"name text,"+"flag text,"+
                    "type text); ";
    private static String status_list =
            "create table  status_list (user_id integer primary key autoincrement, " +"pg_s text,"+"add_s text,"+"i_s text,"+"j_s text,"+
                    "p_s text); ";
    private static String profile_image =
            "create table  profile_image (user_id integer primary key autoincrement, " +
                   "image_data text); ";
    private static String personal_details =
            "create table personal_details (user_id integer primary key autoincrement, " +
                    "fname text,"+"mob text, "+"c_city text, "+"s_city text,"+"email text,"+"linkedin text); ";

    private static String graduation =
            "create table graduation (user_id integer primary key autoincrement, " +
                    "status integer,"+"college text,"+"s_year text,"
                    + "e_year text ,"+"degree text,"+"stream text, "+"cgpa text);";
    private static String xii =
            "create table xii (user_id integer primary key autoincrement, " +
                    "status integer,"+"school text,"
                    + "e_year text ,"+"board text,"+"stream text, "+"cgpa text);";
    private static String secondary_x =
            "create table secondary_x (user_id integer primary key autoincrement, " +
                    "status integer,"+"school text,"
                    + "e_year text ,"+"board text,"+"cgpa text);";
    private static String post_graduation =
            "create table post_graduation (user_id integer primary key autoincrement, " +
                    "status integer,"+"college text,"+"s_year text,"
                    + "e_year text ,"+"degree text,"+"stream text, "+"cgpa text);";
    private static String job1 =
            "create table job1 (user_id integer primary key autoincrement, " +
                    "profile text,"+"organisation text,"+"location text,"+ "s_year text ,"
                    + "e_year text ,"+
                    "status integer,"+"description text);";
    private static String job2 =
            "create table job2 (user_id integer primary key autoincrement, " +
                    "profile text,"+"organisation text,"+"location text,"+ "s_year text ,"
                    + "e_year text ,"+
                    "status integer,"+"description text);";
    private static String intern1 =
            "create table intern1 (user_id integer primary key autoincrement, " +"type integer,"+
                    "profile text,"+"organisation text,"+"location text,"+ "s_year text ,"
                    + "e_year text ,"+
                    "status integer,"+"description text);";
    private static String intern2 =
            "create table intern2 (user_id integer primary key autoincrement, " +"type integer,"+
                    "profile text,"+"organisation text,"+"location text,"+ "s_year text ,"
                    + "e_year text ,"+
                    "status integer,"+"description text);";
    private static String project1 =
            "create table project1 (user_id integer primary key autoincrement, " +
                    "title text,"+ "s_year text ,"
                    + "e_year text ,"+
                    "status integer,"+ "project_link text ,"+"description text);";
    private static String project2 =
            "create table project2 (user_id integer primary key autoincrement, " +
                    "title text,"+ "s_year text ,"
                    + "e_year text ,"+
                    "status integer,"+ "project_link text ,"+"description text);";
    private static String skills =
            "create table skills (user_id integer primary key autoincrement, " +
                    "java integer,"+
                    "c integer,"+
                    "cpp integer,"+
                    "html integer,"+
                    "css integer,"+
                    "javascript integer,"+
                    "jquery integer,"+
                    "python integer,"+
                    "android integer,"+
                    "unity integer,"+
                    "coral integer,"+
                    "r integer,"+
                    "ml integer,"+
                    "ai integer,"+
                    "data integer);";
    private static String additional =
            "create table additional (user_id integer primary key autoincrement, " +
                   "description text);";

    public User_details(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(status_list);
        db.execSQL(sequence);
        db.execSQL(personal_details);
        db.execSQL(graduation);
        db.execSQL(xii);
        db.execSQL(secondary_x);
        db.execSQL(post_graduation);
        db.execSQL(job1);
        db.execSQL(job2);
        db.execSQL(intern1);
        db.execSQL(intern2);
        db.execSQL(project1);
        db.execSQL(project2);
        db.execSQL(skills);
        db.execSQL(additional);
        db.execSQL(profile_image);
        db.execSQL(cv_list);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS status_list");
        db.execSQL("DROP TABLE IF EXISTS sequence");
        db.execSQL("DROP TABLE IF EXISTS personal_details");
        db.execSQL("DROP TABLE IF EXISTS graduation");
        db.execSQL("DROP TABLE IF EXISTS xii");
        db.execSQL("DROP TABLE IF EXISTS secondary_x");
        db.execSQL("DROP TABLE IF EXISTS post_graduation");
        db.execSQL("DROP TABLE IF EXISTS job1");
        db.execSQL("DROP TABLE IF EXISTS job2");
        db.execSQL("DROP TABLE IF EXISTS intern1");
        db.execSQL("DROP TABLE IF EXISTS intern2");
        db.execSQL("DROP TABLE IF EXISTS project1");
        db.execSQL("DROP TABLE IF EXISTS project2");
        db.execSQL("DROP TABLE IF EXISTS skills");
        db.execSQL("DROP TABLE IF EXISTS "+ "additional");
        db.execSQL("DROP TABLE IF EXISTS "+ "profile_image");
        db.execSQL("DROP TABLE IF EXISTS cv_list");
        onCreate(db);
    }
    public long insert_cv_list(String name,String attend_id,String date_time,String flag,String type){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("attend_id", attend_id);
        values.put("date_time", date_time);
        values.put("flag", flag);
        values.put("type", type);
        // insert row
        long id = db.insert(TABLE_NAME_cv_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insert_status_list(String pg_s,String add_s,String i_s,String j_s,String p_s){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("pg_s", pg_s);
        values.put("add_s", add_s);
        values.put("i_s", i_s);
        values.put("j_s", j_s);
        values.put("p_s", p_s);
        // insert row
        long id = db.insert(TABLE_NAME_status_list, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long sequence(String image){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("data", image);




        // insert row
        long id = db.insert(TABLE_NAME_sequence, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertprofileimage(String image){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("image_data", image);



        // insert row
        long id = db.insert(TABLE_NAME_pic, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertpersonaldetails(String fname, String mob, String c_city, String s_city,String email,String linkedin){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("fname", fname);
        values.put("mob", mob);
        values.put("c_city", c_city);
        values.put("s_city", s_city);
        values.put("email",email);
        values.put("linkedin",linkedin);



        // insert row
        long id = db.insert(TABLE_NAME_personal_details, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertgraduation(Integer status, String college, String s_year, String e_year,
                                 String degree, String stream, String cgpa){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("college",college);
        values.put("s_year",s_year);
        values.put("e_year",e_year);
        values.put("degree", degree);
        values.put("stream",stream);
        values.put("cgpa",cgpa);

        // insert row
        long id = db.insert(TABLE_NAME_graduation, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertpostgraduation(Integer status, String college, String s_year, String e_year,
                                     String degree, String stream, String cgpa){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("college",college);
        values.put("s_year",s_year);
        values.put("e_year",e_year);
        values.put("degree", degree);
        values.put("stream",stream);
        values.put("cgpa",cgpa);

        // insert row
        long id = db.insert(TABLE_NAME_post_graduation, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertxii(Integer status, String school, String e_year,
                          String board, String stream, String cgpa){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("school",school);
        values.put("e_year",e_year);
        values.put("board", board);
        values.put("stream",stream);
        values.put("cgpa",cgpa);

        // insert row
        long id = db.insert(TABLE_NAME_xii, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertsecondaryx(Integer status, String school, String e_year,
                                 String board, String cgpa){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("school",school);
        values.put("e_year",e_year);
        values.put("board", board);
        values.put("cgpa",cgpa);

        // insert row
        long id = db.insert(TABLE_NAME_secondary_x, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertjob1(String profile, String organisation, String location, Integer status, String e_year, String s_year,
                           String description){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("organisation",organisation);
        values.put("s_year",s_year);
        values.put("e_year",e_year);
        values.put("profile",profile);
        values.put("location",location);
        values.put("description",description);

        // insert row
        long id = db.insert(TABLE_NAME_job1, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertjob2(String profile, String organisation, String location, Integer status, String e_year, String s_year,
                           String description){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("organisation",organisation);
        values.put("s_year",s_year);
        values.put("e_year",e_year);
        values.put("profile",profile);
        values.put("location",location);
        values.put("description",description);

        // insert row
        long id = db.insert(TABLE_NAME_job2, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertintern1(Integer type, String profile, String organisation, String location, Integer status, String e_year, String s_year,
                              String description){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type",type);
        values.put("status", status);
        values.put("organisation",organisation);
        values.put("s_year",s_year);
        values.put("e_year",e_year);
        values.put("profile",profile);
        values.put("location",location);
        values.put("description",description);

        // insert row
        long id = db.insert(TABLE_NAME_intern1, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertintern2(Integer type, String profile, String organisation, String location, Integer status, String e_year, String s_year,
                              String description){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type",type);
        values.put("status", status);
        values.put("organisation",organisation);
        values.put("s_year",s_year);
        values.put("e_year",e_year);
        values.put("profile",profile);
        values.put("location",location);
        values.put("description",description);

        // insert row
        long id = db.insert(TABLE_NAME_intern2, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertproject1(String title, String project_link, Integer status, String e_year, String s_year,
                               String description){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("project_link", project_link);
        values.put("status",status);
        values.put("s_year",s_year);
        values.put("e_year",e_year);
        values.put("description",description);

        // insert row
        long id = db.insert(TABLE_NAME_project1, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertproject2(String title, String project_link, Integer status, String e_year, String s_year,
                               String description){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("project_link", project_link);
        values.put("status",status);
        values.put("s_year",s_year);
        values.put("e_year",e_year);
        values.put("description",description);

        // insert row
        long id = db.insert(TABLE_NAME_project2, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertskills(Integer java, Integer c, Integer cpp, Integer html, Integer css, Integer javascript,
                             Integer jquery, Integer python, Integer android, Integer unity,
                             Integer coral, Integer r,Integer ai, Integer ml,Integer data){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("java",java);
        values.put("c", c);
        values.put("cpp",cpp);
        values.put("html",html);
        values.put("css",css);
        values.put("javascript",javascript);
        values.put("jquery",jquery);
        values.put("python", python);
        values.put("android",android);
        values.put("unity",unity);
        values.put("coral",coral);
        values.put("r",r);
        values.put("ai",ai);
        values.put("ml",ml);
        values.put("data",data);

        // insert row
        long id = db.insert(TABLE_NAME_skills, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertadditional(String description){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("description",description);


        // insert row
        long id = db.insert(TABLE_NAME_additional, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public Cursor cv_list_data()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM cv_list ",null);
        return res;
    }
    public Cursor status_list_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM status_list WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor profile_image_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM profile_image WHERE user_id = "+ user_id,null);
        return res;
    }

    public Cursor personal_details_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM personal_details WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor sequence_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_personal_details,null);
        return res;
    }
    public Cursor graduation_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM graduation WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor post_graduation_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM post_graduation WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor xii_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM xii WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor secondary_x_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM secondary_x WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor intern1_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM intern1 WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor intern2_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM intern2 WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor job1_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM job1 WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor job2_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM job2 WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor project1_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM project1 WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor project2_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM project2 WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor skills_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM skills WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor additional_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM additional WHERE user_id = "+ user_id,null);
        return res;
    }
    public Cursor cv_data(int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("select * from "+ TABLE1,null);
        Cursor res = db.rawQuery("SELECT * FROM cv_list WHERE user_id = "+ user_id,null);
        return res;
    }
    public void deleteEntry(int row) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_cv_list, "attend_id = " + row, null);

      /*if you just have key_name to select a row,you can ignore passing rowid(here-row) and use:

      db.delete(DATABASE_TABLE, KEY_NAME + "=" + key_name, null);
      */

    }
    public void update(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put("flag",1);

        // updating row
        //return db.update(submit_3, values,  "attendence_id = "+String.valueOf(id), null);
        String strSQL = "UPDATE submit_table3 SET flag = 1 WHERE attendence_id = "+ id;

        db.execSQL(strSQL);
    }

}
