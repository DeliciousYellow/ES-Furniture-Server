package com.delicious.pojo;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200,"成功"),
    FAIL(300,"失败"),
    ERROR(500,"发生错误");
    private final Integer code;
    private final String message;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
