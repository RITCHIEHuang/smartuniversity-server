package com.njtech.smartuniversity.util;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ritchie on 6/25/18
 */
public class JsonResult<T> {

    @ApiModelProperty(value = "返回状态码", notes = "0成功 -1失败")
    private int code;

    @ApiModelProperty(value = "返回数据", notes = "\"\"为空")
    private T data;

    @ApiModelProperty(value = "返回信息", notes = "接口执行信息")
    private String message;


    private JsonResult(JsonResultBuilder<T> builder) {
        this.code = builder.code;
        this.data = builder.data;
        this.message = builder.message;
    }

    public static <T> JsonResult.JsonResultBuilder<T> builder() {
        return new JsonResultBuilder<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static final class JsonResultBuilder<T> {

        private int code;

        private T data;

        private String message;

        private JsonResultBuilder() {

        }

        public JsonResultBuilder message(String message) {
            this.message = message;
            return this;
        }

        public JsonResultBuilder data(T data) {
            this.data = data;
            return this;
        }

        public JsonResultBuilder code(int code) {
            this.code = code;
            return this;
        }

        public JsonResult build() {
            return new JsonResult<>(this);
        }
    }

    public static void main(String[] args) {
        System.out.print(JsonResult.<String>builder().data("asd").build().toString());
    }

}
