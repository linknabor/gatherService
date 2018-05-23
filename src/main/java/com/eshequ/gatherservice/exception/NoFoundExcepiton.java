package com.eshequ.gatherservice.exception;

/**
 * 自定义No Found异常
 * Created by Jackie on 2018.05.16
 */
public class NoFoundExcepiton extends Exception{

    public NoFoundExcepiton(String errorInfo) {
        super(errorInfo);
    }
}
