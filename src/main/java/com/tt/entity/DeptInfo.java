package com.tt.entity;

import java.io.Serializable;

/**
 * Created by liuhongbing on 2018/3/28.
 */
public class DeptInfo implements Serializable {
    private String deptId;
    private String detpName;
    private String parentDeptId;
    private String desc;
    private String location;
    private String dtCreate;
    private String dtUpdate;

    public String getDeptId() {
        return deptId;
    }

    public String getDetpName() {
        return detpName;
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

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setDetpName(String detpName) {
        this.detpName = detpName;
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
        return "DeptInfo{" +
                "deptId='" + deptId + '\'' +
                ", detpName='" + detpName + '\'' +
                ", parentDeptId='" + parentDeptId + '\'' +
                ", desc='" + desc + '\'' +
                ", location='" + location + '\'' +
                ", dtCreate='" + dtCreate + '\'' +
                ", dtUpdate='" + dtUpdate + '\'' +
                '}';
    }
}
