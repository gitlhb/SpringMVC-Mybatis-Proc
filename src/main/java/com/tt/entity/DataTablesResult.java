package com.tt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuhongbing on 2018/3/28.
 */
public class DataTablesResult<T> implements Serializable {
/*
* draw:表示请求次数

recordsTotal:总记录数

recordsFiltered:过滤后的总记录数

data:具体的数据对象数组
* */

    private Boolean success;

    private int draw;

    private int recordsTotal;

    private int recordsFiltered;

    private String error;

    private List<T> data;

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}