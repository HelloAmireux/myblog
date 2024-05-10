package com.amireux.authority.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//返回统一类型
public class Result {
    private String code;

    private String msg;

    private Object data;

    public static Result success(){
        return  new Result("200","执行成功",null);
    }
    public static Result success(Object data){
        return  new Result("200","执行成功",data);
    }
    public static Result error(){
        return  new Result("500","执行失败",null);
    }
    public static Result Result(String code,String msg){
        return  new Result(code,msg,null);
    }
}
