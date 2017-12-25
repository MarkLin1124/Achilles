package com.mark.achilles.Constant;

/**
 * Created by marklin on 2017/12/24.
 */

public class DatabaseConstant {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "achilles";

    public static final String TABLE_TEAM = "team";
    public static final String TABLE_PLAYER = "player";
    public static final String TABLE_GAME_INFO = "gameInfo";

    //team module
    public static final String TEAM_NAME = "teamName";
    public static final String TEAM_CODE = "teamCode";

    //player module
    public static final String PLAYER_TEAM_ID = "playerTeamId";
    public static final String PLAYER_NAME = "playerName";
    public static final String PLAYER_NUMBER = "playerNumber";
    public static final String PLAYER_IS_LEADER = "playerIsLeader";
    public static final String PLAYER_IS_DELETE = "playerIsDelete";

    //game info module
    public static final String GAME_INFO_DATE = "gameInfoDate";
    public static final String GAME_INFO_NAME = "gameInfoName";
    public static final String GAME_INFO_TEAM = "gameInfoTeam";
    public static final String GAME_INFO_COURT = "gameInfoCourt";
    public static final String GAME_INFO_ENEMY_NAME = "gameInfoEnemyName";
    public static final String GAME_INFO_IS_DELETE = "gameInfoIsDelete";
}
