package com.nicho.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nicho.bean.MiaoshaUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/3 20:29
 */

@Component

public class DBUtil {
    @Value("${spring.datasource.name}")
    public   String conName;

    private static String name ;
    @PostConstruct
    public void init(){
        name = conName;
    }

    public static String conPassword;

    public static String conUrl;


    public static String userName;


    public static String driver;

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConPassword() {
        return conPassword;
    }
    @Value("${spring.datasource.password}")
    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }

    public String getConUrl() {
        return conUrl;
    }
    @Value("${spring.datasource.url}")
    public void setConUrl(String conUrl) {
        this.conUrl = conUrl;
    }

    public String getUserName() {
        return userName;
    }
    @Value("${spring.datasource.username}")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDriver() {
        return driver;
    }
    @Value("${spring.datasource.driver-class-name}")
    public void setDriver(String driver) {
        this.driver = driver;
    }


    public static void createUser(int count) throws Exception{
        List<MiaoshaUser> users = new ArrayList<MiaoshaUser>();

        Random random = new Random();
        for(int i = 0;i<count;++i){
            MiaoshaUser user = new MiaoshaUser();
            user.setId(13000000000L+i);
            user.setLoginCount(1);
            user.setNickname("user"+i);
            user.setRegisterDate(new Date());
            user.setSalt("1a2b3c");
            user.setHead(UUIDUtil.uuid());
            user.setPassword(MD5Util.inputPassToDBpass("123456", user.getSalt()));
            users.add(user);

        }
        Map<String,Object> obj =null;
        try {
            Yaml yaml = new Yaml();
            InputStream resourceAsStream = DBUtil.class.getClassLoader().getResourceAsStream("application.yml");

            obj = (Map) yaml.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(obj);
        Map<String,Object> spring=(Map<String,Object>)obj.get("spring");
        Map<String,Object> datasource=(Map<String,Object>)spring.get("datasource");
        String password= String.valueOf(datasource.get("password"));
        String url= String.valueOf(datasource.get("url"));
        String username= String.valueOf(datasource.get("username"));
        String driver= String.valueOf(datasource.get("driver-class-name"));
        String name= String.valueOf(datasource.get("name"));

//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(url,username,password);
//        String sql = "insert into miaosha_user(loginCount, nickname, registerDate, salt, password, id,head)values(?,?,?,?,?,?,?)";
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        for(int i =0;i<users.size();++i){
//            MiaoshaUser user = users.get(i);
//			pstmt.setInt(1, user.getLoginCount());
//			pstmt.setString(2, user.getNickname());
//			pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
//			pstmt.setString(4, user.getSalt());
//			pstmt.setString(5, user.getPassword());
//			pstmt.setLong(6, user.getId());
//            pstmt.setString(7, user.getHead());
//			pstmt.addBatch();
//        }
//        pstmt.executeBatch();
//        pstmt.close();
//        conn.close();
//        System.out.println("insert to db");


        //生成文件tokens
        String urlString = "http://192.168.137.3:8080/login/do_login";
        File file = new File("C:/tmp/tokens.txt");
        if(file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for(int i=0;i<users.size();i++) {
            MiaoshaUser user = users.get(i);
            URL uri = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection)uri.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile="+user.getId()+"&password="+MD5Util.inputPassToFormPass("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte buff[] = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buff)) >= 0) {
                bout.write(buff, 0 ,len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            JSONObject jo = JSON.parseObject(response);
            String token = jo.getString("data");
            System.out.println("create token : " + user.getId());

            String row = user.getId()+","+token;
            System.out.println(row);
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();
        System.out.println("over");

    }


    public static void main(String[] args) throws Exception{
        createUser(1000);
        //System.out.println("---"+DBUtil.class);
        //System.out.println(System.currentTimeMillis());

    }
}

