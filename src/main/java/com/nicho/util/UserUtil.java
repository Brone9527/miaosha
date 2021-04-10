package com.nicho.util;

import com.nicho.bean.MiaoshaUser;
import com.nicho.bean.Person;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/4 16:30
 */
 
public class UserUtil {
    private static void createUser(int count) throws Exception{
        DBUtil dbUtil = new DBUtil();
        List<MiaoshaUser> users = new ArrayList<MiaoshaUser>(count);
        Person person = new Person();
        person.setWork_time("13");
        person.setId("4");
        person.setMobile("17607179762");
        person.setLevel("2");
        person.setDepartment_id("1");
        person.setAttr("男");
        person.setName("陈华");

        //生成用户
//        for(int i=0;i<count;i++) {
//            MiaoshaUser user = new MiaoshaUser();
//            user.setId(13000000000L+i);
//            user.setLoginCount(1);
//            user.setNickname("user"+i);
//            user.setRegisterDate(new Date());
//            user.setSalt("1a2b3c");
//            user.setPassword(MD5Util.inputPassToDBpass("123456", user.getSalt()));
//            users.add(user);
//        }
        System.out.println("create person");
		//插入数据库
//		Connection conn = dbUtil.getCon();
//        System.out.println(conn);
//		//String sql = "insert into miaosha_user(login_count, nickname, register_date, salt, password, id)values(?,?,?,?,?,?)";
//        String sql = "insert into person(id, name, mobile, attr, level, department_id,work_time)values(?,?,?,?,?,?,?)";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
////		for(int i=0;i<users.size();i++) {
////			MiaoshaUser user = users.get(i);
////			pstmt.setInt(1, user.getLoginCount());
////			pstmt.setString(2, user.getNickname());
////			pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
////			pstmt.setString(4, user.getSalt());
////			pstmt.setString(5, user.getPassword());
////			pstmt.setLong(6, user.getId());
////			pstmt.addBatch();
////		}
//        pstmt.setString(1, person.getId());
//        pstmt.setString(2, person.getName());
//        pstmt.setString(3, person.getMobile());
//        pstmt.setString(4, person.getAttr());
//        pstmt.setString(5, person.getLevel());
//        pstmt.setString(6, person.getDepartment_id());
//        pstmt.setString(7, person.getWork_time());
//		pstmt.executeBatch();
//		pstmt.close();
//		conn.close();
//		System.out.println("insert to db");
        //登录，生成token
//        String urlString = "http://localhost:8080/login/do_login";
//        File file = new File("C:/tokens.txt");
//        if(file.exists()) {
//            file.delete();
//        }
//        RandomAccessFile raf = new RandomAccessFile(file, "rw");
//        file.createNewFile();
//        raf.seek(0);
//        for(int i=0;i<users.size();i++) {
//            MiaoshaUser user = users.get(i);
//            URL url = new URL(urlString);
//            HttpURLConnection co = (HttpURLConnection)url.openConnection();
//            co.setRequestMethod("POST");
//            co.setDoOutput(true);
//            OutputStream out = co.getOutputStream();
//            String params = "mobile="+user.getId()+"&password="+MD5Util.inputPassToFormPass("123456");
//            out.write(params.getBytes());
//            out.flush();
//            InputStream inputStream = co.getInputStream();
//            ByteArrayOutputStream bout = new ByteArrayOutputStream();
//            byte buff[] = new byte[1024];
//            int len = 0;
//            while((len = inputStream.read(buff)) >= 0) {
//                bout.write(buff, 0 ,len);
//            }
//            inputStream.close();
//            bout.close();
//            String response = new String(bout.toByteArray());
//            JSONObject jo = JSON.parseObject(response);
//            String token = jo.getString("data");
//            System.out.println("create token : " + user.getId());
//
//            String row = user.getId()+","+token;
//            raf.seek(raf.length());
//            raf.write(row.getBytes());
//            raf.write("\r\n".getBytes());
//            System.out.println("write to file : " + user.getId());
//        }
//        raf.close();

        System.out.println("over");
    }

    public static void main(String[] args)throws Exception {
        //createUser(5000);
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));
        Date time = new Date();

        System.out.println(new Timestamp(time.getTime()));
    }
}
