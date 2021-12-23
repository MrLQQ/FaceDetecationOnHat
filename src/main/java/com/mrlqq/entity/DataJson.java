package com.mrlqq.entity;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 作者: DL代先生
 * 日期: 2021/4/29
 * 时间: 11:15
 * 描述: layui要求的返回值类型！
 * 内容:
 */
public class DataJson {
    private Integer code;
    private String msg;
    private Map<String,String> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataJson{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
