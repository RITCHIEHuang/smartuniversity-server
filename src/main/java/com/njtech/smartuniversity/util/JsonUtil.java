package com.njtech.smartuniversity.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ritchie on 6/26/18
 */
public class JsonUtil<T> implements Serializable{

    public JsonResult wrongNoDataDefault() {
        return JsonResult.builder()
                .data("")
                .message("失败")
                .code(-1)
                .build();
    }


    public JsonResult wrongNoDataWithMessage(String message) {
        return JsonResult.builder()
                .data("")
                .message(message)
                .code(-1)
                .build();
    }


    public JsonResult successDataDefault(T data) {
        return JsonResult.builder()
                .data(data)
                .code(0)
                .message("成功").build();
    }


    public JsonResult successEmptyDataDefault(String message) {
        return JsonResult.builder()
                .data("")
                .code(0)
                .message(message).build();
    }

    public JsonResult successDataWithMessage(T data, String message) {
        return JsonResult.builder()
                .data(data)
                .code(0)
                .message(message).build();
    }


}
