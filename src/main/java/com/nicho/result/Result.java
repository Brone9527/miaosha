package com.nicho.result;
/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/22 12:04
 */
 
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(T data) {
        this.data = data;
        this.msg = "success";
        this.code = 0;
    }


    /**
     * 成功时的调用
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    private Result(int code ,String msg){
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMsg codeMsg){
        if (codeMsg == null){
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
