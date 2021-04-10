<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table align='center' border='2' cellspacing='0'>
    <tr>
        <td>学生编号</td>
        <td>学生姓名</td>
        <td>出生日期</td>
        <td>性别</td>
        <td>年龄</td>
    </tr>
    <c:forEach items="${student}" var="s" varStatus="st">
        <tr>
            <td>${s.s_id}</td>
            <td>${s.s_name}</td>
            <td>${s.s_birth}</td>
            <td>${s.s_sex}</td>
            <td>${s.age}</td>
        </tr>
    </c:forEach>
</table>