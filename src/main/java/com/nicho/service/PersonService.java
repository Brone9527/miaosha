package com.nicho.service;

import com.nicho.bean.Person;
import com.nicho.controller.PersonController;
import com.nicho.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonDao personDao;

    /*
        Service层介于controller和dao之间作为服务层进行一些逻辑处理，
        这里逻辑太简单所以知识单纯调用dao所以不做注释
     */
    public List<Person> getAll(){
        return personDao.getAll();
    }

    public Person getPersonByID(int id){
        return personDao.getPersonByID(id);
    }

    public List<Person> getPersonByAttr(String attr){
        return personDao.getPersonByAttr(attr);
    }

    public List<Person> getPersonByName(List name){
        return personDao.getPersonByName(name);
    }

    public void  delete(int id){
        personDao.delete(id);
    }

    public void update(Person p){
        personDao.update(p);
    }

    public void newp(Person p){
        personDao.newp(p);
    }

    public void write2Excel(List info){
        log.info("rtyuiopfghjkm--------------------------");
    }
}
