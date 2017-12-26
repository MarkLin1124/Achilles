package com.mark.achilles.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mark.achilles.Constant.DatabaseConstant;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.Module.History;
import com.mark.achilles.Module.Player;
import com.mark.achilles.Module.Team;

import java.util.ArrayList;

import static com.mark.achilles.Constant.ActionConstant.*;
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
                + PLAYER_IS_LEADER + " INTEGER DEFAULT 0 , "
                + PLAYER_IS_DELETE + " INTEGER DEFAULT 0)");
        db.execSQL("CREATE TABLE " + TABLE_GAME_INFO
                + " (_id INTEGER PRIMARY KEY NOT NULL , "
                + GAME_INFO_DATE + " TEXT NOT NULL , "
                + GAME_INFO_NAME + " TEXT NOT NULL , "
                + GAME_INFO_TEAM + " INTEGER NOT NULL , "
                + GAME_INFO_COURT + " TEXT NOT NULL , "
                + GAME_INFO_ENEMY_NAME + " TEXT NOT NULL , "
                + GAME_INFO_USER_SCORE + " TEXT NOT NULL , "
                + GAME_INFO_USER_TEAM_FOUL + " TEXT NOT NULL , "
                + GAME_INFO_ENEMY_SCORE + " TEXT NOT NULL , "
                + GAME_INFO_ENEMY_TEAM_FOUL + " TEXT NOT NULL , "
                + GAME_INFO_IS_DELETE + " INTEGER DEFAULT 0)");
        db.execSQL("CREATE TABLE " + TABLE_BOX_SCORE
                + " (_id INTEGER PRIMARY KEY NOT NULL , "
                + BOX_PLAYER_ID + " INTEGER NOT NULL , "
                + BOX_GAME_ID + " INTEGER NOT NULL , "
                + BOX_TWO_POINT_MADE + " INTEGER NOT NULL , "
                + BOX_TWO_POINT_MISS + " INTEGER NOT NULL , "
                + BOX_THREE_POINT_MADE + " INTEGER NOT NULL , "
                + BOX_THREE_POINT_MISS + " INTEGER NOT NULL , "
                + BOX_FREE_THROW_MADE + " INTEGER NOT NULL , "
                + BOX_FREE_THROW_MISS + " INTEGER NOT NULL , "
                + BOX_OFF_REBOUND + " INTEGER NOT NULL , "
                + BOX_DEF_REBOUND + " INTEGER NOT NULL , "
                + BOX_ASSIST + " INTEGER NOT NULL , "
                + BOX_STEAL + " INTEGER NOT NULL , "
                + BOX_BLOCK + " INTEGER NOT NULL , "
                + BOX_TURNOVER + " INTEGER NOT NULL , "
                + BOX_PERSONAL_FOUL + " INTEGER NOT NULL , "
                + BOX_ON_COURT + " INTEGER DEFAULT 0)");
        db.execSQL("CREATE TABLE " + TABLE_HISTORY
                + " (_id INTEGER PRIMARY KEY NOT NULL , "
                + HISTORY_GAME_ID + " INTEGER NOT NULL , "
                + HISTORY_BOX_SCORE_ID + " INTEGER NOT NULL , "
                + HISTORY_ACTION + " INTEGER NOT NULL , "
                + HISTORY_IS_DELETE + " INTEGER DEFAULT 0)");
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

    public void createGameInfo(GameInfo gameInfo) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.GAME_INFO_DATE, gameInfo.gameDate);
        values.put(DatabaseConstant.GAME_INFO_NAME, gameInfo.gameName);
        values.put(DatabaseConstant.GAME_INFO_TEAM, gameInfo.gameTeam);
        values.put(DatabaseConstant.GAME_INFO_COURT, gameInfo.gameCourt);
        values.put(DatabaseConstant.GAME_INFO_ENEMY_NAME, gameInfo.enemyName);
        values.put(DatabaseConstant.GAME_INFO_USER_SCORE, gameInfo.userScore);
        values.put(DatabaseConstant.GAME_INFO_USER_TEAM_FOUL, gameInfo.userTeamFoul);
        values.put(DatabaseConstant.GAME_INFO_ENEMY_SCORE, gameInfo.enemyScore);
        values.put(DatabaseConstant.GAME_INFO_ENEMY_TEAM_FOUL, gameInfo.enemyTeamFoul);
        values.put(DatabaseConstant.GAME_INFO_IS_DELETE, gameInfo.isDelete ? 1 : 0);

        getWritableDatabase().insert(DatabaseConstant.TABLE_GAME_INFO, null, values);
    }

    public void createBoxScore(BoxScore boxScore) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.BOX_PLAYER_ID, boxScore.playerID);
        values.put(DatabaseConstant.BOX_GAME_ID, boxScore.gameID);
        values.put(DatabaseConstant.BOX_TWO_POINT_MADE, boxScore.twoPointMade);
        values.put(DatabaseConstant.BOX_TWO_POINT_MISS, boxScore.twoPointMiss);
        values.put(DatabaseConstant.BOX_THREE_POINT_MADE, boxScore.threePointMade);
        values.put(DatabaseConstant.BOX_THREE_POINT_MISS, boxScore.threePointMiss);
        values.put(DatabaseConstant.BOX_FREE_THROW_MADE, boxScore.freeThrowMade);
        values.put(DatabaseConstant.BOX_FREE_THROW_MISS, boxScore.freeThrowMiss);
        values.put(DatabaseConstant.BOX_OFF_REBOUND, boxScore.offRebound);
        values.put(DatabaseConstant.BOX_DEF_REBOUND, boxScore.defRebound);
        values.put(DatabaseConstant.BOX_ASSIST, boxScore.assist);
        values.put(DatabaseConstant.BOX_STEAL, boxScore.steal);
        values.put(DatabaseConstant.BOX_BLOCK, boxScore.block);
        values.put(DatabaseConstant.BOX_TURNOVER, boxScore.turnover);
        values.put(DatabaseConstant.BOX_PERSONAL_FOUL, boxScore.personalFoul);
        values.put(DatabaseConstant.BOX_ON_COURT, boxScore.onCourt ? 1 : 0);

        getWritableDatabase().insert(DatabaseConstant.TABLE_BOX_SCORE, null, values);
    }

    public void createHistory(History history) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.HISTORY_GAME_ID, history.gameID);
        values.put(DatabaseConstant.HISTORY_BOX_SCORE_ID, history.boxScoreId);
        values.put(DatabaseConstant.HISTORY_ACTION, history.action);
        values.put(DatabaseConstant.HISTORY_IS_DELETE, history.isDelete ? 1 : 0);

        getWritableDatabase().insert(DatabaseConstant.TABLE_HISTORY, null, values);
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

    public void updateGameInfo(GameInfo gameInfo) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.GAME_INFO_DATE, gameInfo.gameDate);
        values.put(DatabaseConstant.GAME_INFO_NAME, gameInfo.gameName);
        values.put(DatabaseConstant.GAME_INFO_TEAM, gameInfo.gameTeam);
        values.put(DatabaseConstant.GAME_INFO_COURT, gameInfo.gameCourt);
        values.put(DatabaseConstant.GAME_INFO_ENEMY_NAME, gameInfo.enemyName);
        values.put(DatabaseConstant.GAME_INFO_USER_SCORE, gameInfo.userScore);
        values.put(DatabaseConstant.GAME_INFO_USER_TEAM_FOUL, gameInfo.userTeamFoul);
        values.put(DatabaseConstant.GAME_INFO_ENEMY_SCORE, gameInfo.enemyScore);
        values.put(DatabaseConstant.GAME_INFO_ENEMY_TEAM_FOUL, gameInfo.enemyTeamFoul);
        values.put(DatabaseConstant.GAME_INFO_IS_DELETE, gameInfo.isDelete ? 1 : 0);

        getWritableDatabase().update(DatabaseConstant.TABLE_GAME_INFO, values, "_id=?", new String[]{Integer.toString(gameInfo._id)});
    }

    public void updateBoxScore(BoxScore boxScore, int action) {
        ContentValues values = new ContentValues();
        switch (action) {
            case TWO_POINT_MADE:
                values.put(DatabaseConstant.BOX_TWO_POINT_MADE, boxScore.twoPointMade);
                break;
            case TWO_POINT_MISS:
                values.put(DatabaseConstant.BOX_TWO_POINT_MISS, boxScore.twoPointMiss);
                break;
            case THREE_POINT_MADE:
                values.put(DatabaseConstant.BOX_THREE_POINT_MADE, boxScore.threePointMade);
                break;
            case THREE_POINT_MISS:
                values.put(DatabaseConstant.BOX_THREE_POINT_MISS, boxScore.threePointMiss);
                break;
            case FREE_THROW_MADE:
                values.put(DatabaseConstant.BOX_FREE_THROW_MADE, boxScore.freeThrowMade);
                break;
            case FREE_THROW_MISS:
                values.put(DatabaseConstant.BOX_FREE_THROW_MISS, boxScore.freeThrowMiss);
                break;
            case OFF_REBOUND:
                values.put(DatabaseConstant.BOX_OFF_REBOUND, boxScore.offRebound);
                break;
            case DEF_REBOUND:
                values.put(DatabaseConstant.BOX_DEF_REBOUND, boxScore.defRebound);
                break;
            case ASSIST:
                values.put(DatabaseConstant.BOX_ASSIST, boxScore.assist);
                break;
            case BLOCK:
                values.put(DatabaseConstant.BOX_BLOCK, boxScore.block);
                break;
            case STEAL:
                values.put(DatabaseConstant.BOX_STEAL, boxScore.steal);
                break;
            case FOUL:
                values.put(DatabaseConstant.BOX_PERSONAL_FOUL, boxScore.personalFoul);
                break;
            case TURNOVER:
                values.put(DatabaseConstant.BOX_TURNOVER, boxScore.turnover);
                break;
            case ON_COURT:
                values.put(DatabaseConstant.BOX_ON_COURT, boxScore.onCourt ? 1 : 0);
                break;
        }

        getWritableDatabase().update(DatabaseConstant.TABLE_BOX_SCORE, values, "_id=?", new String[]{Integer.toString(boxScore._id)});
    }

    public void deletePlayer(Player player) {
        player.isDelete = true;
        updatePlayer(player);
    }

    public void deleteGameInfo(GameInfo gameInfo) {
        gameInfo.isDelete = true;
        updateGameInfo(gameInfo);
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

    public ArrayList<GameInfo> getGameInfoList(int teamID) {
        ArrayList<GameInfo> list = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(TABLE_GAME_INFO, null, GAME_INFO_TEAM + "=?", new String[]{Integer.toString(teamID)}, null, null, "_id DESC", null);

        while (cursor.moveToNext()) {
            GameInfo gameInfo = new GameInfo(cursor);
            if (!gameInfo.isDelete) {
                list.add(gameInfo);
            }
        }

        cursor.close();
        return list;
    }

    public ArrayList<BoxScore> getBoxScoreList(int gameID) {
        ArrayList<BoxScore> list = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(TABLE_BOX_SCORE, null, BOX_GAME_ID + "=?", new String[]{Integer.toString(gameID)}, null, null, null, null);

        while (cursor.moveToNext()) {
            BoxScore boxScore = new BoxScore(cursor);
            list.add(boxScore);
        }

        cursor.close();
        return list;
    }

    public ArrayList<BoxScore> getOnCourtBoxScore(int gameID) {
        ArrayList<BoxScore> list = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(TABLE_BOX_SCORE, null, BOX_GAME_ID + "=? and " + BOX_ON_COURT + "=?", new String[]{Integer.toString(gameID), Integer.toString(1)}, null, null, null, null);

        while (cursor.moveToNext()) {
            BoxScore boxScore = new BoxScore(cursor);
            list.add(boxScore);
        }

        cursor.close();
        return list;
    }

    public ArrayList<History> getHistoryList(int gameID) {
        ArrayList<History> list = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(TABLE_HISTORY, null, HISTORY_GAME_ID + "=?", new String[]{Integer.toString(gameID)}, null, null, "_id DESC", null);

        while (cursor.moveToNext()) {
            History history = new History(cursor);
            if (!history.isDelete) {
                list.add(history);
            }
        }

        cursor.close();
        return list;
    }

    public Player getPlayer(int playerId) {
        Player player = null;
        Cursor cursor = getReadableDatabase().query(TABLE_PLAYER, null, "_id=?", new String[]{Integer.toString(playerId)}, null, null, null, null);
        while (cursor.moveToNext()) {
            player = new Player(cursor);
        }

        return player;
    }
}
