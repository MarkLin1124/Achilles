package com.mark.achilles.Module;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marklin on 2017/12/25.
 */

public class GameInfo implements Parcelable{
    public static final String TAG = GameInfo.class.getSimpleName();

    public int _id = -1;
    public String gameDate = "";
    public String gameName = "";
    public int gameTeam = -1;
    public String gameCourt = "";
    public String enemyName = "";
    public boolean isDelete = false;

    public GameInfo() {
        _id = -1;
        gameDate = "";
        gameName = "";
        gameTeam = -1;
        gameCourt = "";
        enemyName = "";
        isDelete = false;
    }

    public GameInfo(Cursor cursor) {
        _id = cursor.getInt(0);
        gameDate = cursor.getString(1);
        gameName = cursor.getString(2);
        gameTeam = cursor.getInt(3);
        gameCourt = cursor.getString(4);
        enemyName = cursor.getString(5);
        isDelete = cursor.getInt(6) == 1 ? true : false;
    }

    protected GameInfo(Parcel in) {
        _id = in.readInt();
        gameDate = in.readString();
        gameName = in.readString();
        gameTeam = in.readInt();
        gameCourt = in.readString();
        enemyName = in.readString();
        isDelete = in.readByte() != 0;
    }

    public static final Creator<GameInfo> CREATOR = new Creator<GameInfo>() {
        @Override
        public GameInfo createFromParcel(Parcel in) {
            return new GameInfo(in);
        }

        @Override
        public GameInfo[] newArray(int size) {
            return new GameInfo[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameInfo) {
            GameInfo gameInfo = (GameInfo) obj;
            if (gameInfo._id == _id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("{").append("_id: ").append(_id)
                .append(", gameDate: ").append(gameDate)
                .append(", gameName: ").append(gameName)
                .append(", gameTeam: ").append(gameTeam)
                .append(", gameCourt: ").append(gameCourt)
                .append(", enemyName: ").append(enemyName)
                .append(", isDelete: ").append(isDelete)
                .append("}");

        return builder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(gameDate);
        dest.writeString(gameName);
        dest.writeInt(gameTeam);
        dest.writeString(gameCourt);
        dest.writeString(enemyName);
        dest.writeByte((byte) (isDelete ? 1 : 0));
    }
}
