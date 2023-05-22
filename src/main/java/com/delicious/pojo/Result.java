package com.delicious.pojo;


import lombok.Getter;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-13 22:51
 **/
@Getter
public class Result<T> {

    private Integer code;//状态码
    private String message;//状态信息
    private T data;

    private Result() {
    }

    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = null;
    }

    private Result(T data, ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    //提供一个修改message的方法
    public Result setMessage(String message) {
        this.message = message;
        return this;
    }
    //提供一个修改code的方法
    public Result setCode(Integer Code) {
        this.code = Code;
        return this;
    }

    public static <T> Result<T> ok() {
        return new Result<>(ResultEnum.SUCCESS);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data, ResultEnum.SUCCESS);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultEnum.FAIL);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(data, ResultEnum.FAIL);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResultEnum.ERROR);
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(data, ResultEnum.ERROR);
    }

}
