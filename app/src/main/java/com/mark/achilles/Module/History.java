package com.mark.achilles.Module;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marklin on 2017/12/26.
 */

public class History implements Parcelable {
    public static final String TAG = History.class.getSimpleName();

    public int _id = -1;
    public int gameID = -1;
    public int boxScoreId = -1;
    public int action = 0;
    public boolean isDelete = false;

    public History() {
        _id = -1;
        gameID = -1;
        boxScoreId = -1;
        action = 0;
        isDelete = false;
    }

    public History(Cursor cursor) {
        _id = cursor.getInt(0);
        gameID = cursor.getInt(1);
        boxScoreId = cursor.getInt(2);
        action = cursor.getInt(3);
        isDelete = cursor.getInt(4) == 1 ? true : false;
    }

    protected History(Parcel in) {
        _id = in.readInt();
        gameID = in.readInt();
        boxScoreId = in.readInt();
        action = in.readInt();
        isDelete = in.readByte() != 0;
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeInt(gameID);
        dest.writeInt(boxScoreId);
        dest.writeInt(action);
        dest.writeByte((byte) (isDelete ? 1 : 0));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof History) {
            History history = (History) obj;
            if (history._id == _id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("{").append("_id: ").append(_id)
                .append(", gameID: ").append(gameID)
                .append(", boxScoreId: ").append(boxScoreId)
                .append(", action: ").append(action)
                .append(", isDelete: ").append(isDelete)
                .append("}");

        return builder.toString();
    }
}
