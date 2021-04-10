package com.nicho.controller;

import com.alibaba.fastjson.JSON;
import com.nicho.util.DBUtil;
import com.nicho.bean.Department;
import com.nicho.result.Result;
import com.nicho.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/9 18:36
 */

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;


    @RequestMapping(value = "/getDepartmentById",method= RequestMethod.POST)
    public Result<Map> getByAttr(@RequestBody Map<String,Object> params){
        List<Department> list = departmentService.getDepartmentById(params.get("department_id").toString());
        log.info(list.toString());
        Object ob = JSON.toJSON(list);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result", ob);
        log.info("result"+ob);
        log.info(DBUtil.conPassword+"-----------------------");
        return Result.success(result);
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Result<Department> add(@RequestBody Map<String,Object> params){
        Department department = new Department();
        department.setDepartment_name(String.valueOf(params.get("department_name")));
        department.setDepartment_num((int)params.get("department_num"));
        department.setDepartment_tel(String.valueOf(params.get("department_tel")));
        departmentService.add(department);
        log.info("添加部门成功");
        return Result.success(department);


    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(@RequestBody Map<String,Object> params){
        Department department = new Department();
        department.setDepartment_id((int)params.get("department_id"));
        department.setDepartment_name(String.valueOf(params.get("department_name")));
        department.setDepartment_num((int)params.get("department_num"));
        department.setDepartment_tel(String.valueOf(params.get("department_tel")));
        departmentService.update(department);
        log.info("更新部门成功");
    }
}
