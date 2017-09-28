package com.example.carikado.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class Response<T> implements Serializable {

    private HttpStatus status;
    private String message;
    private T data;

    public Response() {

    }

    public Response(HttpStatus status, String message) {
        this(status, message, null);
    }

    public Response(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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
