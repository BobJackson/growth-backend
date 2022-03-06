package com.wangyousong.app.growthbackend.exception;

public class RecordNotFound extends RuntimeException {

    public RecordNotFound(String id) {
        super("author is not found by " + id);
    }

}
