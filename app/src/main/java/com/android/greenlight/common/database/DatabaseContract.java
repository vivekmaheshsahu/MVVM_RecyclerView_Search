package com.android.greenlight.common.database;

import android.os.Environment;

/**
 * @author Vivek
 */

public final class DatabaseContract {


    public static final String DATABASE_NAME = "GreenDb.sr";
    public static final int DATABASE_VERSION = 1;
    public static final String DB_LOCATION = Environment.getExternalStorageDirectory() + "/GreenLight";


    public static final String TEXT_TYPE = " TEXT";
    public static final String BLOB_TYPE = " BLOB";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";
    public static final String NOT_NULL = " NOT NULL ";

    public static final class ZoneTable {
        public static final String TABLE_NAME = "zoneDetails";
        public static final String COLUMN_ZONE_ID = "zone_id";
        public static final String COLUMN_ZONE_NAME = "zone_name";
        public static final String COLUMN_ZONE_TERRITORY = "zone_territory";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "( " +
                COLUMN_ZONE_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_ZONE_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_ZONE_TERRITORY + TEXT_TYPE + ")";
    }

    public static final class RegionTable {
        public static final String TABLE_NAME = "region";
        public static final String COLUMN_REGION_ID = "region_id";
        public static final String COLUMN_REGION_NAME = "region_name";
        public static final String COLUMN_REGION_TERRITORY = "region_territory";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" +
                COLUMN_REGION_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_REGION_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_REGION_TERRITORY + TEXT_TYPE + ")";
    }

    public static final class AreaTable {
        public static final String TABLE_NAME = "area";
        public static final String COLUMN_AREA_ID = "area_id";
        public static final String COLUMN_AREA_NAME = "area_name";
        public static final String COLUMN_AREA_TERRITORY = "area_territory";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" +
                COLUMN_AREA_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_AREA_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_AREA_TERRITORY + TEXT_TYPE + ")";
    }

    public static final class EmployeeTable {
        public static final String TABLE_NAME = "employee";
        public static final String COLUMN_EMP_ID = "emp_id";
        public static final String COLUMN_EMP_AREA = "emp_area";
        public static final String COLUMN_EMP_NAME = "emp_name";
        public static final String COLUMN_EMP_TERRITORY = "emp_territory";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" +
                COLUMN_EMP_ID + INTEGER_TYPE + COMMA_SEP +
                COLUMN_EMP_AREA + INTEGER_TYPE + COMMA_SEP +
                COLUMN_EMP_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_TERRITORY + TEXT_TYPE +
                ")";
    }


}
