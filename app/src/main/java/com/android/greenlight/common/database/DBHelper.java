package com.android.greenlight.common.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.greenlight.common.database.DatabaseContract.AreaTable;
import com.android.greenlight.common.database.DatabaseContract.EmployeeTable;
import com.android.greenlight.common.database.DatabaseContract.RegionTable;
import com.android.greenlight.common.database.DatabaseContract.ZoneTable;
import com.android.greenlight.common.utility.Utility;

import java.io.File;

import static com.android.greenlight.common.database.DatabaseContract.DATABASE_NAME;
import static com.android.greenlight.common.database.DatabaseContract.DATABASE_VERSION;
import static com.android.greenlight.common.database.DatabaseContract.DB_LOCATION;

/**
 * This class is used to create and update local database
 *
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

    public Cursor fetchRegion() {
        return utility.getDatabase().rawQuery("SELECT * From region", null);
    }

    public Cursor fetchAll() {
        return utility.getDatabase().rawQuery("SELECT * From zone", null);
    }

    public Cursor fetchArea() {
        return utility.getDatabase().rawQuery("SELECT * From area", null);
    }
}
