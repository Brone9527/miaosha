package com.nicho.service;

import com.nicho.bean.Department;
import com.nicho.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/9 19:19
 */

@Service
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    public List<Department> getDepartmentById(String department_id){
        return departmentDao.getDepartmentById(department_id);
    }

    public Department getDepartmentByName(String department_name) {
        return departmentDao.getDepartmentByName(department_name);
    }

    public void add(Department department) {
         departmentDao.add(department);
    }

    public void update(Department department) {
        departmentDao.update(department);
    }

}
