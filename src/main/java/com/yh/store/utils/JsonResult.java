package com.yh.store.utils;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.yh.store.pojo.Address;
import com.yh.store.pojo.District;
import com.yh.store.pojo.User;

import java.io.Serializable;
import java.util.List;

/**
 * JSon格式的数据进行响应
 * @param <T>
 */
public class JsonResult<T> implements Serializable {

    public static final int OK = 100;
    //状态码
    private Integer state;
    //描述信息
    private String message;
    //数据
    private T data;

    public static JsonResult<Void> success(){
        return new JsonResult<>(OK);
    }

    /**
     * 成功返回的数据
     * @param message 返回的消息
     * @return
     */
    public static JsonResult<Void> success(String message){
        return new JsonResult<>(OK,message);
    }

    /**
     * 成功返回的数据
     * @param data 返回的用户数据
     * @return
     */
    public static JsonResult<User> success(User data){
        return new JsonResult<>(OK,data);
    }

    /**
     * 成功返回的数据
     * @param data 返回的用户数据
     * @return
     */
    public static JsonResult<Address> success(Address data){
        return new JsonResult<>(OK,data);
    }

    public static JsonResult<Void> error(String message){
        return new JsonResult<>(OK,message);
    }

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
