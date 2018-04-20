package com.jlhc.common.dto;

/**
 * 错误信息封装类
 *
 * @param <T>具体内容
 */
public class Msg<T> {

    /*错误码*/
    private String code;

    /*提示信息 */
    private String msg;

    /*具体内容*/
    private  T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public Msg(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Msg(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public Msg(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public Msg(String code) {
        this.code = code;
    }

    public Msg() {
    }

    @Override
    public String toString() {
        return "Msg{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
