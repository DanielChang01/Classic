package com.daniel.utils.common;

import com.daniel.exceptions.StatusCodeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by danielchang on 2018/6/24.
 */
public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = -2994566277483868870L;
    protected T data;
    protected int status;
    protected String msg;

    @JsonCreator
    protected JsonResult(@JsonProperty("status") int status, @JsonProperty("msg") String msg, @JsonProperty("data") T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResult<T> create(int status, String message, T data) {
        return new JsonResult(status, message, data);
    }

    public static <T> JsonResult<T> create(RpcResult<T> rpcResult) {
        Status statusCode = rpcResult.getStatusCode();
        return create(statusCode.getStatus(), statusCode.getReason(), rpcResult.getData());
    }

    public static <T> JsonResult<T> create(Status statusCode, T data) {
        return create(statusCode.getStatus(), statusCode.getReason(), data);
    }

    public static <T> JsonResult<T> success(T data) {
        Status statusCode = Status.success();
        return create(statusCode, data);
    }

    public static <T> JsonResult<T> error(String message) {
        Status statusCode = Status.error(message);
        return create(statusCode, (T) null);
    }

    public static <T> JsonResult<T> error(int status, String message) {
        Status statusCode = Status.error(status, message);
        return create(statusCode, (T) null);
    }

    public static <T> JsonResult<T> error(StatusCodeException e) {
        Status statusCode = e.getStatusCode();
        return create(statusCode, (T) null);
    }

    public int getStatus() {
        return this.status;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return Status.create(this.status, this.msg).isSuccess();
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof JsonResult)) {
            return false;
        } else {
            JsonResult<?> that = (JsonResult)o;
            if(this.status != that.status) {
                return false;
            } else {
                if(this.data != null) {
                    if(this.data.equals(that.data)) {
                        return this.msg != null?this.msg.equals(that.msg):that.msg == null;
                    }
                } else if(that.data == null) {
                    return this.msg != null?this.msg.equals(that.msg):that.msg == null;
                }

                return false;
            }
        }
    }

    public int hashCode() {
        int result = this.data != null?this.data.hashCode():0;
        result = 31 * result + this.status;
        result = 31 * result + (this.msg != null?this.msg.hashCode():0);
        return result;
    }

    public String toString() {
        return "JsonResult{data=" + this.data + ", status=" + this.status + ", msg='" + this.msg + '\'' + '}';
    }
}

