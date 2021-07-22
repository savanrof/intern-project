package com.nt.rookie.post.controller.error;

public class ErrorJson {
    String uri;
    String code;
    String message;

    public ErrorJson() {}

    public ErrorJson(String code, String message, String uri) {
        this.code = code;
        this.message = message;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
