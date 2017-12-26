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
    public static final String TABLE_BOX_SCORE = "boxScore";
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

    //box score module
    public static final String BOX_PLAYER_ID = "boxPlayerId";
    public static final String BOX_GAME_ID = "boxGameId";
    public static final String BOX_TWO_POINT_MADE = "boxTwoPointMade";
    public static final String BOX_TWO_POINT_MISS = "boxTwoPointMiss";
    public static final String BOX_THREE_POINT_MADE = "boxThreePointMade";
    public static final String BOX_THREE_POINT_MISS = "boxThreePointMiss";
    public static final String BOX_FREE_THROW_MADE = "boxFreeThrowMade";
    public static final String BOX_FREE_THROW_MISS = "boxFreeThrowMiss";
    public static final String BOX_OFF_REBOUND = "boxOffRebound";
    public static final String BOX_DEF_REBOUND = "boxDefRebound";
    public static final String BOX_ASSIST = "boxAssist";
    public static final String BOX_STEAL = "boxSteal";
    public static final String BOX_BLOCK = "boxBlock";
    public static final String BOX_TURNOVER = "boxTurnOver";
    public static final String BOX_PERSONAL_FOUL = "boxPersonalFoul";
    public static final String BOX_ON_COURT = "boxOnCourt";
}
