package com.wangyousong.app.growthbackend.common;

import lombok.Data;

@Data
public class R<T> {
    private T data;
    private boolean success;
    private String message;

    public R() {
    }

    public R(T data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public static <T> R<T> success(T data) {
        return new R<>(data, true, "ok");
    }

    public static R<Void> success() {
        return new R<>(null, true, "ok");
    }

}
