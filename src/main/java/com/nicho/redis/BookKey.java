package com.nicho.redis;
/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/25 16:32
 */
 
public class BookKey extends BasePrefix {
    public BookKey(String prefix) {
        super(prefix);
    }
    public static  BookKey getById = new BookKey("id");
    public static  BookKey getByName = new BookKey("name");
}
