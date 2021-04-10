package com.nicho.controller;

import com.alibaba.fastjson.JSON;
import com.nicho.bean.Person;
import com.nicho.result.CodeMsg;
import com.nicho.result.Result;
import com.nicho.service.ExcelUtil;
import com.nicho.service.PersonService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    // 设置访问路由值为路径
    @RequestMapping(value = "/query",method= RequestMethod.POST)
    public List  index(@RequestBody Map<String,Object> params)throws IllegalAccessException{
        //List<Person> list = personService.write2Excel(（List）params.get("tradeList"));
        String sheetName = (String) params.get("sheetName");
        String fileName = (String) params.get("fileName");

        List list = (List) params.get("tradeList");
        HSSFWorkbook workbook = ExcelUtil.createExcel(sheetName, list );
        System.out.println(list);
        log.info(list.toString());
        File file = new File("C:\\11\\"+fileName+".xls");
        try {
            workbook.write(file);
        } catch(IOException e) {
            e.printStackTrace();
        }
        //Person list2 = personService.getPersonByID((int)params.get("id"));
        return list;
    }

    @RequestMapping(value = "/getByAttr",method= RequestMethod.POST)
    public Result<Map> getByAttr(@RequestBody Map<String,Object> params){
        List<Person> list = personService.getPersonByAttr(params.get("attr").toString());
        Object ob = JSON.toJSON(list);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("person", ob);
        log.info("result"+ob);
        return Result.error(CodeMsg.REQUEST_ILLEGAL);
    }

    @RequestMapping(value = "/getByName",method= RequestMethod.POST)
    public Object getByName(@RequestBody Map<String,Object> params){
        ArrayList namelist = (ArrayList)params.get("namelist");
        List<Person> listName = personService.getPersonByName(namelist);
        log.info(listName.toString());
        ArrayList mobileList = new ArrayList();
        for (int i = 0 ;i < listName.size() ;++i){
            //log.info("手机号列表"+listName.get(i).getMobile());
            mobileList.add(listName.get(i).getMobile());
        }
        Object ob = JSON.toJSON(listName);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("person", ob);
        res.put("mobileList", mobileList);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", "查询成功");
        result.put("code", "0000");
        result.put("Object",res);
        return result;
    }
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public void add(@RequestBody Map<String,Object> params){
        Person person = new Person();
        person.setAttr(String.valueOf(params.get("attr")));
        person.setName(String.valueOf(params.get("name")));
        person.setDepartment_id(String.valueOf(params.get("department_id")));
        person.setLevel(String.valueOf(params.get("level")));
        person.setMobile(String.valueOf(params.get("mobile")));
        person.setId(String.valueOf(params.get("id")));
        person.setWork_time(String.valueOf(params.get("work_time")));
        personService.newp(person);
        log.info("添加成功");


    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(@RequestBody Map<String,Object> params){
      Person person = new Person();
        person.setAttr(String.valueOf(params.get("attr")));
        person.setName(String.valueOf(params.get("name")));
        person.setDepartment_id(String.valueOf(params.get("department_id")));
        person.setLevel(String.valueOf(params.get("level")));
        person.setMobile(String.valueOf(params.get("mobile")));
        person.setId(String.valueOf(params.get("id")));
        person.setWork_time(String.valueOf(params.get("work_time")));
        personService.update(person);
    log.info("修改成功");
    }

    @RequestMapping("/delete")
    public void delete(){
        personService.delete(5);
        System.out.println("删除成功");
    }
}
