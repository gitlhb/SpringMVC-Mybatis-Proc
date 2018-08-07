package com.tt.entity;

/**
 * Created by liuhongbing on 2018/3/28.
 */
public class RequestObj {
    private  String id;
    private  String parent_dept_id;
    private  String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setParent_dept_id(String parent_dept_id) {
        this.parent_dept_id = parent_dept_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public String getParent_dept_id() {
        return parent_dept_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RequestObj{" +
                "id='" + id + '\'' +
                ", parent_dept_id='" + parent_dept_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
