package com.mark.achilles.Module;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marklin on 2017/12/25.
 */

public class Player implements Parcelable {
    public static final String TAG = Player.class.getSimpleName();

    public int _id = -1;
    public int teamID = 0;
    public String playerName = "";
    public int playerNum = 0;
    public boolean isLeader = false;
    public boolean isDelete = false;

    public Player() {
        _id = -1;
        teamID = 0;
        playerName = "";
        playerNum = 0;
        isLeader = false;
        isDelete = false;
    }

    public Player(Cursor cursor) {
        _id = cursor.getInt(0);
        teamID = cursor.getInt(1);
        playerName = cursor.getString(2);
        playerNum = cursor.getInt(3);
        isLeader = cursor.getInt(4) == 1 ? true : false;
        isDelete = cursor.getInt(5) == 1 ? true : false;
    }

    protected Player(Parcel in) {
        _id = in.readInt();
        teamID = in.readInt();
        playerName = in.readString();
        playerNum = in.readInt();
        isLeader = in.readByte() != 0;
        isDelete = in.readByte() != 0;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeInt(teamID);
        dest.writeString(playerName);
        dest.writeInt(playerNum);
        dest.writeByte((byte) (isLeader ? 1 : 0));
        dest.writeByte((byte) (isDelete ? 1 : 0));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player player = (Player) obj;
            if (player._id == _id) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("{").append("_id: ").append(_id)
                .append(", teamID: ").append(teamID)
                .append(", playerName: ").append(playerName)
                .append(", playerNum: ").append(playerNum)
                .append(", isLeader: ").append(isLeader)
                .append(", isDelete: ").append(isDelete)
                .append("}");

        return builder.toString();
    }
}
