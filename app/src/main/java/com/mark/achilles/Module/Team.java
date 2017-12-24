package com.mark.achilles.Module;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marklin on 2017/12/24.
 */

public class Team implements Parcelable {
    public static final String TAG = Team.class.getSimpleName();

    public int _id = 0;
    public String teamName = "";
    public int teamCode = 0;

    public Team() {
        _id = 0;
        teamName = "";
        teamCode = 0;
    }

    public Team(Cursor cursor) {
        _id = cursor.getInt(0);
        teamName = cursor.getString(1);
        teamCode = cursor.getInt(2);
    }

    protected Team(Parcel in) {
        _id = in.readInt();
        teamName = in.readString();
        teamCode = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(teamName);
        dest.writeInt(teamCode);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{").append("_id: ").append(_id).append(", teamName: ").append(teamName).append(", teamCode: ").append(teamCode).append("}");

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Team) {
            Team team = (Team) obj;
            if (team._id == _id) {
                return true;
            }
        }

        return false;
    }
}
