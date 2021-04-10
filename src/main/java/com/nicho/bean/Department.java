package com.nicho.bean;

import java.io.Serializable;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/9 18:31
 */
 
public class Department implements Serializable {
    private int department_id;
    private int department_num;
    private String department_name;
    private String department_tel;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getDepartment_num() {
        return department_num;
    }

    public void setDepartment_num(int department_num) {
        this.department_num = department_num;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartment_tel() {
        return department_tel;
    }

    public void setDepartment_tel(String department_tel) {
        this.department_tel = department_tel;
    }

    @Override
    public String toString() {
        return "Department{" +
                "department_id=" + department_id +
                ", department_num=" + department_num +
                ", department_name='" + department_name + '\'' +
                ", department_tel='" + department_tel + '\'' +
                ", person=" + person +
                '}';
    }
}
