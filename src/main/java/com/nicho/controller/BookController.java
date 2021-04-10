package com.nicho.controller;

import com.nicho.bean.Book;
import com.nicho.bean.MiaoshaUser;
import com.nicho.redis.BookKey;
import com.nicho.redis.RedisService;
import com.nicho.result.Result;
import com.nicho.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2020/8/19 11:30
 */

@RestController
@RequestMapping("/book")
public class BookController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookController.class);
    @Autowired
    RedisService redisService;
    @Autowired
    MiaoshaUserService miaoshaUserService;

    //添加
    @RequestMapping(value = "/redisAdd",method= RequestMethod.POST)
    public Result<Book> saveRedis(@RequestBody Map<String,Object> params){
        Book book = new Book();
        book.setTitle(params.get("title").toString());
        book.setAuthor(params.get("author").toString());
        book.setId(Long.parseLong(params.get("id").toString()));
        book.setDescription(params.get("description").toString());
        book.setIsbn(params.get("isbn").toString());
        book.setReader(params.get("reader").toString());

        redisService.set(BookKey.getById, ""+1, book);
        //String redisKeys = params.get("key").toString();
        //redisTemplate.opsForValue().set(redisKeys,book);
        return Result.success(book);

    }

    //获取
    @RequestMapping(value = "/redisGet",method= RequestMethod.POST)
    public Result<Book> getRedis(@RequestBody Map<String,Object> params){
        String redisKeys = params.get("redisKeys").toString();
        Book book = redisService.get(BookKey.getById , ""+redisKeys, Book.class);
        log.info(book+"查询成功");
        return Result.success(book);

    }
    @RequestMapping(value = "/dbGet" ,method = RequestMethod.GET)
    public Result<MiaoshaUser> dbGet(){
        MiaoshaUser user = miaoshaUserService.getById(Long.valueOf(12));
        return  Result.success(user);
    }
}

