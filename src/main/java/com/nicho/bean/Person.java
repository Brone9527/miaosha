package com.nicho.bean;


import java.io.Serializable;

public class Person implements Serializable {
    /*
    {id} 自增主键
    {name} 人员姓名
    {mobile} 人员电话
     */
    private String id;
    private String name;
    private String mobile;
    private String attr;
    private String level;
    private String department_id;
    private String work_time;
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", attr='" + attr + '\'' +
                ", level='" + level + '\'' +
                ", department_id='" + department_id + '\'' +
                ", work_time='" + work_time + '\'' +
                ", department=" + department +
                '}';
    }
}
