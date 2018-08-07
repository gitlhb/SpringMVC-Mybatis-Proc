package com.tt.entity;

import java.io.Serializable;

/**
 * Created by liuhongbing on 2018/4/4.
 */
public class AddDept implements Serializable {
    private  Integer deptId;
    private  String deptName;
    private  String parentDeptId;
    private  String desc;
    private  String location;
    private  String dtCreate;
    private  String dtUpdate;

    public Integer getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getParentDeptId() {
        return parentDeptId;
    }

    public String getDesc() {
        return desc;
    }

    public String getLocation() {
        return location;
    }

    public String getDtCreate() {
        return dtCreate;
    }

    public String getDtUpdate() {
        return dtUpdate;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setParentDeptId(String parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDtCreate(String dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(String dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public String toString() {
        return "AddDept{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", parentDeptId='" + parentDeptId + '\'' +
                ", desc='" + desc + '\'' +
                ", location='" + location + '\'' +
                ", dtCreate='" + dtCreate + '\'' +
                ", dtUpdate='" + dtUpdate + '\'' +
                '}';
    }
}
