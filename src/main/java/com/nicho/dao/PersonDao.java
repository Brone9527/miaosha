package com.nicho.dao;

import com.nicho.bean.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonDao {
    /*
    查所有
    return List<Person>
     */
    List<Person> getAll();

    /*
    根据ID查询
    {id} 要查询人员的 id
     */
    Person getPersonByID(int id);

    /*
根据属性查询
{attr} 要查询人员的 attr
 */
    List<Person> getPersonByAttr(String attr);

    /*
根据属性查询
{name} 要查询人员的 name
*/
    List<Person> getPersonByName(@Param("name") List name);

    /*
    删除
    {id} 要删除人员的 id
     */
    void delete(int id);

    /*
    更新
    {p} 要更新的Person实例
     */
    void update(Person p);

    /*
    增加
    {p} 要新增的Person实例
     */
    void newp(Person p);
}

