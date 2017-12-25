package com.mark.achilles.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mark.achilles.Constant.DatabaseConstant;
import com.mark.achilles.Module.Player;
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
        db.execSQL("CREATE TABLE " + TABLE_PLAYER
                + " (_id INTEGER PRIMARY KEY NOT NULL , "
                + PLAYER_TEAM_ID + " INTEGER NOT NULL , "
                + PLAYER_NAME + " TEXT NOT NULL , "
                + PLAYER_NUMBER + " INTEGER NOT NULL , "
                + PLAYER_IS_LEADER + " INTEGER DEFAULT 0 ,"
                + PLAYER_IS_DELETE + " INTEGER DEFAULT 0)");
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

    public void createPlayer(Player player) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.PLAYER_TEAM_ID, player.teamID);
        values.put(DatabaseConstant.PLAYER_NAME, player.playerName);
        values.put(DatabaseConstant.PLAYER_NUMBER, player.playerNum);
        values.put(DatabaseConstant.PLAYER_IS_LEADER, player.isLeader ? 1 : 0);
        values.put(DatabaseConstant.PLAYER_IS_DELETE, player.isDelete ? 1 : 0);

        getWritableDatabase().insert(DatabaseConstant.TABLE_PLAYER, null, values);
    }

    public void updatePlayer(Player player) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.PLAYER_TEAM_ID, player.teamID);
        values.put(DatabaseConstant.PLAYER_NAME, player.playerName);
        values.put(DatabaseConstant.PLAYER_NUMBER, player.playerNum);
        values.put(DatabaseConstant.PLAYER_IS_LEADER, player.isLeader ? 1 : 0);
        values.put(DatabaseConstant.PLAYER_IS_DELETE, player.isDelete ? 1 : 0);

        getWritableDatabase().update(DatabaseConstant.TABLE_PLAYER, values, "_id=?", new String[]{Integer.toString(player._id)});
    }

    public void deletePlayer(Player player) {
        player.isDelete = true;
        updatePlayer(player);
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

    public ArrayList<Player> getPlayerList(int teamID) {
        ArrayList<Player> list = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(TABLE_PLAYER, null, PLAYER_TEAM_ID + "=?", new String[]{Integer.toString(teamID)}, null, null, null, null);

        while (cursor.moveToNext()) {
            Player player = new Player(cursor);
            if (!player.isDelete) {
                list.add(player);
            }
        }

        cursor.close();
        return list;
    }
}
