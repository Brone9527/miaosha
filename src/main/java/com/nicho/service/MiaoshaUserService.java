package com.nicho.service;

import com.nicho.util.MD5Util;
import com.nicho.util.UUIDUtil;
import com.nicho.bean.MiaoshaUser;
import com.nicho.dao.MiaoshaUserDao;
import com.nicho.exception.GlobalException;
import com.nicho.redis.MiaoshaUserKey;
import com.nicho.redis.RedisService;
import com.nicho.result.CodeMsg;
import com.nicho.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/28 10:00
 */
@Service
public class MiaoshaUserService {

    public static final String COOKI_NAME_TOKEN= "token";
    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    public MiaoshaUser getById(Long id){
        //取缓存
        MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, ""+id,MiaoshaUser.class );
        if(user!=null){
            return user;
        }
        //去数据库
        user = miaoshaUserDao.getById(id);
        if(user!=null){
            redisService.set(MiaoshaUserKey.getById, ""+id, user);
        }
        return user;
    }

    public boolean updatePassword(long id,String formPass,String token){
        //取user
        MiaoshaUser user = getById(id);
        if(user==null){
           throw  new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        miaoshaUserDao.update(toBeUpdate);
        //处理缓存
        redisService.delete(MiaoshaUserKey.getById,""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(MiaoshaUserKey.token, token, user);
        return  true;
    }

    public MiaoshaUser getByToken(HttpServletResponse response , String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token,MiaoshaUser.class );
        //延长有效期
        if(user != null){
            addCookie(response,token ,user );
        }
        return user;
    }

    public String login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        MiaoshaUser user = getById(Long.valueOf(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(!dbPass.equals(calcPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
