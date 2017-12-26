package com.mark.achilles.Module;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.mark.achilles.Constant.ActionConstant;

/**
 * Created by marklin on 2017/12/26.
 */

public class BoxScore implements Parcelable {
    public static final String TAG = BoxScore.class.getSimpleName();

    public int _id = -1;
    public int playerID = -1;
    public int gameID = -1;
    public int twoPointMade = 0;
    public int twoPointMiss = 0;
    public int threePointMade = 0;
    public int threePointMiss = 0;
    public int freeThrowMade = 0;
    public int freeThrowMiss = 0;
    public int offRebound = 0;
    public int defRebound = 0;
    public int assist = 0;
    public int steal = 0;
    public int block = 0;
    public int turnover = 0;
    public int personalFoul = 0;
    public boolean onCourt = false;

    public BoxScore() {
        _id = -1;
        playerID = -1;
        gameID = -1;
        twoPointMade = 0;
        twoPointMiss = 0;
        threePointMade = 0;
        threePointMiss = 0;
        freeThrowMade = 0;
        freeThrowMiss = 0;
        offRebound = 0;
        defRebound = 0;
        assist = 0;
        steal = 0;
        block = 0;
        turnover = 0;
        personalFoul = 0;
        onCourt = false;
    }

    public BoxScore(Cursor cursor) {
        _id = cursor.getInt(0);
        playerID = cursor.getInt(1);
        gameID = cursor.getInt(2);
        twoPointMade = cursor.getInt(3);
        twoPointMiss = cursor.getInt(4);
        threePointMade = cursor.getInt(5);
        threePointMiss = cursor.getInt(6);
        freeThrowMade = cursor.getInt(7);
        freeThrowMiss = cursor.getInt(8);
        offRebound = cursor.getInt(9);
        defRebound = cursor.getInt(10);
        assist = cursor.getInt(11);
        steal = cursor.getInt(12);
        block = cursor.getInt(13);
        turnover = cursor.getInt(14);
        personalFoul = cursor.getInt(15);
        onCourt = cursor.getInt(16) == 1 ? true : false;
    }

    protected BoxScore(Parcel in) {
        _id = in.readInt();
        playerID = in.readInt();
        gameID = in.readInt();
        twoPointMade = in.readInt();
        twoPointMiss = in.readInt();
        threePointMade = in.readInt();
        threePointMiss = in.readInt();
        freeThrowMade = in.readInt();
        freeThrowMiss = in.readInt();
        offRebound = in.readInt();
        defRebound = in.readInt();
        assist = in.readInt();
        steal = in.readInt();
        block = in.readInt();
        turnover = in.readInt();
        personalFoul = in.readInt();
        onCourt = in.readByte() != 0;
    }

    public BoxScore add(int action) {
        switch (action) {
            case ActionConstant.TWO_POINT_MADE:
                twoPointMade = twoPointMade + 1;
                break;
            case ActionConstant.TWO_POINT_MISS:
                twoPointMiss = twoPointMiss + 1;
                break;
            case ActionConstant.THREE_POINT_MADE:
                threePointMade = threePointMade + 1;
                break;
            case ActionConstant.THREE_POINT_MISS:
                threePointMiss = threePointMiss + 1;
                break;
            case ActionConstant.FREE_THROW_MADE:
                freeThrowMade = freeThrowMade + 1;
                break;
            case ActionConstant.FREE_THROW_MISS:
                freeThrowMiss = freeThrowMiss + 1;
                break;
            case ActionConstant.OFF_REBOUND:
                offRebound = offRebound + 1;
                break;
            case ActionConstant.DEF_REBOUND:
                defRebound = defRebound + 1;
                break;
            case ActionConstant.ASSIST:
                assist = assist + 1;
                break;
            case ActionConstant.BLOCK:
                block = block + 1;
                break;
            case ActionConstant.STEAL:
                steal = steal + 1;
                break;
            case ActionConstant.FOUL:
                personalFoul = personalFoul + 1;
                break;
            case ActionConstant.TURNOVER:
                turnover = turnover + 1;
                break;
        }
        return this;
    }

    public BoxScore remove(int action) {
        switch (action) {
            case ActionConstant.TWO_POINT_MADE:
                twoPointMade = twoPointMade--;
                break;
            case ActionConstant.TWO_POINT_MISS:
                twoPointMiss = twoPointMiss--;
                break;
            case ActionConstant.THREE_POINT_MADE:
                threePointMade = threePointMade--;
                break;
            case ActionConstant.THREE_POINT_MISS:
                threePointMiss = threePointMiss--;
                break;
            case ActionConstant.FREE_THROW_MADE:
                freeThrowMade = freeThrowMade--;
                break;
            case ActionConstant.FREE_THROW_MISS:
                freeThrowMiss = freeThrowMiss--;
                break;
            case ActionConstant.OFF_REBOUND:
                offRebound = offRebound--;
                break;
            case ActionConstant.DEF_REBOUND:
                defRebound = defRebound--;
                break;
            case ActionConstant.ASSIST:
                assist = assist--;
                break;
            case ActionConstant.BLOCK:
                block = block--;
                break;
            case ActionConstant.STEAL:
                steal = steal--;
                break;
            case ActionConstant.FOUL:
                personalFoul = personalFoul--;
                break;
            case ActionConstant.TURNOVER:
                turnover = turnover--;
                break;
        }
        return this;
    }

    public static final Creator<BoxScore> CREATOR = new Creator<BoxScore>() {
        @Override
        public BoxScore createFromParcel(Parcel in) {
            return new BoxScore(in);
        }

        @Override
        public BoxScore[] newArray(int size) {
            return new BoxScore[size];
        }
    };

    public int getTotalPoint() {
        int point = (twoPointMade * 2) + (threePointMade * 3) + freeThrowMade;
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoxScore) {
            BoxScore boxScore = (BoxScore) obj;
            if (boxScore._id == _id) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("{").append("_id: ").append(_id)
                .append(", playerID: ").append(playerID)
                .append(", gameID: ").append(gameID)
                .append(", twoPointMade: ").append(twoPointMade)
                .append(", twoPointMiss: ").append(twoPointMiss)
                .append(", threePointMade: ").append(threePointMade)
                .append(", threePointMiss: ").append(threePointMiss)
                .append(", freeThrowMade: ").append(freeThrowMade)
                .append(", freeThrowMiss: ").append(freeThrowMiss)
                .append(", offRebound: ").append(offRebound)
                .append(", defRebound: ").append(defRebound)
                .append(", assist: ").append(assist)
                .append(", steal: ").append(steal)
                .append(", block: ").append(block)
                .append(", turnover: ").append(turnover)
                .append(", personalFoul: ").append(personalFoul)
                .append(", onCourt: ").append(onCourt)
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
        dest.writeInt(playerID);
        dest.writeInt(gameID);
        dest.writeInt(twoPointMade);
        dest.writeInt(twoPointMiss);
        dest.writeInt(threePointMade);
        dest.writeInt(threePointMiss);
        dest.writeInt(freeThrowMade);
        dest.writeInt(freeThrowMiss);
        dest.writeInt(offRebound);
        dest.writeInt(defRebound);
        dest.writeInt(assist);
        dest.writeInt(steal);
        dest.writeInt(block);
        dest.writeInt(turnover);
        dest.writeInt(personalFoul);
        dest.writeByte((byte) (onCourt ? 1 : 0));
    }
}
