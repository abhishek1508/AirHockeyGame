package com.software.game.airhockeyandroid.Entities;

/**
 * Created by shardendu on 11/2/15.
 */
public class PowerUp {

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int count;

    public PowerUp(int count, String type){
        this.count= count;
        this.type= type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

}
