package com.android.greenlight.common.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.greenlight.common.utility.Utility;
import java.io.File;

import com.android.greenlight.common.database.DatabaseContract.*;

import static com.android.greenlight.common.database.DatabaseContract.DB_LOCATION;
import static com.android.greenlight.common.database.DatabaseContract.DATABASE_NAME;
import static com.android.greenlight.common.database.DatabaseContract.DATABASE_VERSION;

/**
 * This class is used to create and update local database
 * @author Vivek
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    int project_Id;
    Utility utility = new Utility();
    public DBHelper(Context context) {
        super(context, DB_LOCATION
                + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ZoneTable.CREATE_TABLE);
        db.execSQL(RegionTable.CREATE_TABLE);
        db.execSQL(AreaTable.CREATE_TABLE);
        db.execSQL(EmployeeTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertDefaultData()
    {

        /**
         *  Inserting bydefault Data in Database file
         *  Project Details table and Login table to assign default project MUW for
         *  exisiting user without logout they can use normally after app update.
         */



        String status = null;

        String query = "SELECT COUNT(*) AS remaining FROM allProjectDetails";

        Cursor cursor = utility.getDatabase().rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            status = cursor.getString(cursor.getColumnIndex("remaining"));
            cursor.close();
        } else {
            status = "0";
        }
      //  if(status.equals(1))
      /*  {
            ContentValues values1 = new ContentValues();
            values1.put(DatabaseContract.allProjectDetails.COLUMN_ASSIGNED_PROJECT, PROJECT_ASSIGNED_FLAG);
            values1.put(DatabaseContract.allProjectDetails.COLUMN_PROJECT_ID, "1");
            values1.put(DatabaseContract.allProjectDetails.COLUMN_PROJECT_NAME, "MUW");
            utility.getDatabase().insert(DatabaseContract.allProjectDetails.TABLE_NAME, null, values1);

            ContentValues values = new ContentValues();
            values.put(DatabaseContract.LoginTable.COLUMN_PROJECT_ID, 1);
            utility.getDatabase().update(DatabaseContract.LoginTable.TABLE_NAME
                    , values,null,null);

        }*/

        /**
         *  Inserting bydefault Data in Database file
         *  Project Details table and registration table to assign default project MUW for
         *  exisiting user without logout they can use normally after app update.
         */

      /*  ContentValues values2 = new ContentValues();
        values2.put(DatabaseContract.RegistrationTable.COLUMN_PROJECT_ID, 1);
        Utility.getDatabase().update(RegistrationTable.TABLE_NAME
                , values2,null,null);*/

    }

    //returns list of all entries who's all forms are not filled(incomplete entries)
/*
    public Cursor getIncompleteFormListList() {
        String query ="SELECT " + LoginTable.COLUMN_PROJECT_ID + " FROM " + LoginTable.TABLE_NAME;
        Cursor cur = utility.getDatabase().rawQuery(query,null);
        if(cur!=null && cur.moveToFirst()){
            project_Id = cur.getInt(cur.getColumnIndex(LoginTable.COLUMN_PROJECT_ID));
        }

     */
/*String str= "select c.unique_id,c.form_id,r.name,c.form_completion_status " +
             "from filled_forms_status as c join registration as r  on c.unique_id=r.unique_id " +
             "and form_id>=2 and form_id<=10 and form_completion_status=0 " +
             "group by c.unique_id ";
        return utility.getDatabase().rawQuery(str,null);
     *//*
return utility.getDatabase().rawQuery("SELECT current.unique_id,max(current.form_id) as form_id,reg.name, current.form_completion_status " +
                "FROM filled_forms_status AS current JOIN registration AS reg on current.unique_id = reg.unique_id  " +
                "AND reg.project_id = 1 where current.form_id <=10 and current.unique_id not in (select unique_id from filled_forms_status " +
                "where form_id = 10 and form_completion_status = 1)and current.form_completion_status =1 GROUP BY current.unique_id", null);
    }
*/

    public void updateProjectId()
    {
        String update_query ="UPDATE registration SET project_id = 1 WHERE unique_id " +
                "IN(SELECT unique_id FROM registration WHERE project_id IS NULL OR project_id = '')";
        utility.getDatabase().execSQL(update_query);
    }

    public String fetchCount() {
        String status = null;

        String query = "SELECT COUNT(*) AS remaining FROM filled_forms_status WHERE form_sync_status = 0 AND form_completion_status = 1";

        Cursor cursor = utility.getDatabase().rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            status = cursor.getString(cursor.getColumnIndex("remaining"));
            cursor.close();
        } else {
            status = "0";
        }
        return status;
    }
/*

    public Cursor fetchUserDetails() {
        String query = "SELECT name,phone_no FROM " + LoginTable.TABLE_NAME;
        return utility.getDatabase().rawQuery(query, null);
    }

    public Cursor getcompleteFormListList() {
        String query ="SELECT " + LoginTable.COLUMN_PROJECT_ID + " FROM " + LoginTable.TABLE_NAME;
        Cursor cur = utility.getDatabase().rawQuery(query,null);
        if(cur!=null && cur.moveToFirst()){
            project_Id = cur.getInt(cur.getColumnIndex(LoginTable.COLUMN_PROJECT_ID));
        }
        return utility.getDatabase().rawQuery("SELECT name,unique_id from registration WHERE unique_id IN (SELECT unique_id FROM filled_forms_status WHERE form_id = 10 and form_completion_status = 1 ) AND project_id = "+project_Id, null);
       }

    public Cursor getChildIdFromMotherId(String motherId) {
        return utility.getDatabase().rawQuery("SELECT name,unique_id FROM " + RegistrationTable.TABLE_NAME + " WHERE mother_id ='" + motherId + "'", null);
    }
*/

  /*  public Cursor getuniqueIdFormId(String uniqueId) {
        return utility.getDatabase().rawQuery("SELECT max(form_id) as form_id FROM " + FilledFormStatusTable.TABLE_NAME + " WHERE unique_id = '" + uniqueId + "' AND form_completion_status = 1", null);
    }*/
    /*public Cursor getCompleteFormDetails(String unique_id, int form_id) {
        return utility.getDatabase().rawQuery("SELECT main_questions.question_label," +
                "question_options.option_label,question_answers.unique_id " +
                "FROM question_answers" +
                " JOIN main_questions" +
                " ON question_answers.question_keyword=main_questions.keyword " +
                "JOIN question_options" +
                " ON question_answers.answer_keyword=question_options.keyword " +
                "WHERE question_answers.unique_id='"+unique_id+"' " +
                "and question_answers.form_id="+form_id,null);
    }*/

    /**
     *form 6 contains question label but does'nt contain answer label so only
     main_questions and question_answers involved in query and not question_option
     * @param unique_id=child unique_id
     * @param form_id=child form_id
     * @return
     */
    public Cursor getForm6Details(String unique_id, int form_id){
        return utility.getDatabase().rawQuery("SELECT main_questions.question_label,question_answers.answer_keyword,question_answers.unique_id " +
                "FROM question_answers" +
                " JOIN main_questions ON question_answers.question_keyword=main_questions.keyword " +
                "WHERE question_answers.unique_id='"+unique_id+"' and question_answers.form_id="+form_id,null);
    }
    public Cursor getFormsList(String motherId){
        //String query = "select visit_name,form_id from form_details group by(form_id) order by cast(form_id as int) asc";
        String query ="select visit_name,form_id from form_details where form_id in(select form_id from filled_forms_status WHERE" +
                " form_completion_status = 1 and unique_id = '"+motherId+"') order by cast(form_id as int) asc";
        return utility.getDatabase().rawQuery(query,null);
    }
    public Cursor getIncompleteFormList(String unique_id){
        return utility.getDatabase().rawQuery("select filled_forms_status.form_id,visit_name" +
                " from filled_forms_status join form_details on" +
                " filled_forms_status.form_id=form_details.form_id" +
                " where unique_id='"+unique_id+"' and form_completion_status=1" +
                " group by(filled_forms_status.form_id)",null);
    }
    public Cursor getCompleteFormDetails(String unique_id, int Form_id)
    {
        return utility.getDatabase().rawQuery( "select r.unique_id ,mq.question_label,qo.option_label,qa.answer_keyword,qa.question_keyword" +
                " from question_answers as qa" +
                " left join registration as r on r.unique_id=qa.unique_id" +
                " left join main_questions as mq on mq.keyword = qa.question_keyword" +
                " left join question_options as qo on qo.keyword = qa.answer_keyword" +
                " where qa.unique_id='"+unique_id+"'" +
                " and qa.form_id="+Form_id+" group by(qa.question_keyword)",null);
    }

    public Cursor fetchRegion(){
        return utility.getDatabase().rawQuery("SELECT * From region", null);
    }

    public Cursor fetchAll(){
        return utility.getDatabase().rawQuery("SELECT * From zone", null);
    }

    public Cursor fetchArea(){
        return utility.getDatabase().rawQuery("SELECT * From area", null);
    }

    /*  *//**
     * this method returns the participant details of given 5 digit participant id for verification
     * @param participantId
     * @return
     *//*
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ArrayMap<String, String> participantDetails(String participantId){
        RegisteredData registeredData = new RegisteredData();

        String value,unique_id;
        Cursor cursor=  utility.getDatabase().rawQuery("SELECT registration_name,unique_id from all_registration_detail " +
                "WHERE user_id = "+participantId, null);

        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getString(cursor.getColumnIndex("registration_name"));
            unique_id = cursor.getString(cursor.getColumnIndex("unique_id"));
        } else {
            value = "NA";
            unique_id = "NA";
        }

      ArrayMap<String, String> mul_val = new ArrayMap<String, String>();
        mul_val.put("name",value);
        mul_val.put("unique_id",unique_id);
        return mul_val;
    }
*/
    public int checkAlreadyfilled(String participantId, String formId)
    {
        int flag = 0;
        Cursor cursor =  utility.getDatabase().rawQuery("SELECT count(unique_id) FROM filled_forms_status " +
                "WHERE form_id = "+ Integer.valueOf(formId)+" and form_completion_status = 1" +
                " AND unique_id IN (SELECT unique_id from all_registration_detail WHERE user_id = "+participantId +")", null);
        if (cursor != null && cursor.moveToFirst()) {
            flag = cursor.getInt(cursor.getColumnIndex("count(unique_id)"));
        } else {
            flag = 0;
        }
        return flag;
    }

  /*  public ArrayList<Integer> fetchMidlineCount(){
        ArrayList<Integer> data = new ArrayList<>();
        int count=0,length=0;
        Cursor cur = utility.getDatabase().rawQuery("select count(*) as count, max(length("+AllregistrationDetail.COLUMN_USER_ID+")) as length " +
                "from "+ AllregistrationDetail.TABLE_NAME,null);
        if (cur != null && cur.moveToFirst()) {
            count = cur.getInt(cur.getColumnIndex("count"));
            length = cur.getInt(cur.getColumnIndex("length"));
        }
        data.add(count);
        data.add(length);
        return data;
    }
    public int maxFormId(String uniqueId){
        int formID=0;
       String query ="SELECT max(" + FilledFormStatusTable.COLUMN_FORM_ID + ") as form_id FROM " + FilledFormStatusTable.TABLE_NAME + " WHERE unique_id = '" + uniqueId + "' AND form_completion_status = 1 and form_id NOT IN (11,12)";
        Cursor cur = utility.getDatabase().rawQuery(query,null);
        if(cur!=null && cur.moveToFirst()){
            formID = cur.getInt(cur.getColumnIndex(FilledFormStatusTable.COLUMN_FORM_ID));
        }
       return formID;
    }*/
}
