package com.tt.entity;

/**
 * Created by liuhongbing on 2018/3/28.
 */
public class ParIdRequestObj {
    private  String parId;

    public void setParId(String parId) {
        this.parId = parId;
    }

    public String getParId() {

        return parId;
    }

    @Override
    public String toString() {
        return "ParIdRequestObj{" +
                "parId='" + parId + '\'' +
                '}';
    }
}
