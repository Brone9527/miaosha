package com.nicho.exception;

import com.nicho.result.CodeMsg;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/30 16:43
 */
 
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private CodeMsg cm;

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm(){
        return cm;
    }
}
