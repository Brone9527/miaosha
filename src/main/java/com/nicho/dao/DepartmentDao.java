package com.nicho.dao;

import com.nicho.bean.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/9 18:39
 */

@Mapper
public interface DepartmentDao {
    void add(Department department);
    void update(Department department);
    List<Department> getDepartmentById(String department_id);
    Department getDepartmentByName(String department_name);
}
