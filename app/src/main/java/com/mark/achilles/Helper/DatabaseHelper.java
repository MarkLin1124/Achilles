package com.mark.achilles.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mark.achilles.Constant.DatabaseConstant;
import com.mark.achilles.Module.Team;

import java.util.ArrayList;

import static com.mark.achilles.Constant.DatabaseConstant.*;

/**
 * Created by marklin on 2017/12/24.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHelper.class.getSimpleName();

    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        }

        return databaseHelper;
    }

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_TEAM + " (_id INTEGER PRIMARY KEY NOT NULL , " + TEAM_NAME + " TEXT NOT NULL , " + TEAM_CODE + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTeam(String teamName, int teamCode) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.TEAM_NAME, teamName);
        values.put(DatabaseConstant.TEAM_CODE, teamCode);
        getWritableDatabase().insert(DatabaseConstant.TABLE_TEAM, null, values);
    }

    public ArrayList<Team> getTeamList() {
        ArrayList<Team> list = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(TABLE_TEAM, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            list.add(new Team(cursor));
        }

        cursor.close();
        return list;
    }
}