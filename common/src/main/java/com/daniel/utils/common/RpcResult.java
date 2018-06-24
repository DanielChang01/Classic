package com.daniel.utils.common;

import com.daniel.exceptions.StatusCodeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by danielchang on 2018/6/24.
 */
public class RpcResult<T> implements Serializable {
    private static final long serialVersionUID = -3169090975174656595L;
    private final Status statusCode;
    private final T data;

    @JsonCreator
    protected RpcResult(@JsonProperty("statusCode") Status statusCode, @JsonProperty("data") T data) {
        this.statusCode = statusCode == null?Status.success():statusCode;
        this.data = data;
    }

    public static <T> RpcResult<T> create(int status, String reason, T data) {
        return create(Status.create(status, reason), data);
    }

    public static <T> RpcResult<T> create(Status statusCode, T data) {
        return new RpcResult(statusCode, data);
    }

    public static <T> RpcResult<T> create(JsonResult<T> rpcResult) {
        return create(rpcResult.getStatus(), rpcResult.getMsg(), rpcResult.getData());
    }

    public static <T> RpcResult<T> success(T data) {
        return create(Status.success(), data);
    }

    public static <T> RpcResult<T> error(int status, String reason) {
        return create(Status.error(status, reason), (T) null);
    }

    public static <T> RpcResult<T> error(String reason) {
        return create(Status.error(reason), (T) null);
    }

    public static <T> RpcResult<T> error(StatusCodeException e) {
        Status statusCode = e.getStatusCode();
        return create(statusCode, (T) null);
    }

    public T checkAndGet() throws StatusCodeException {
        if(this.isSuccess()) {
            return this.getData();
        } else {
            throw this.statusCode.asError();
        }
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.statusCode.isSuccess();
    }

    public T getData() {
        return this.data;
    }

    public Status getStatusCode() {
        return this.statusCode;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof RpcResult)) {
            return false;
        } else {
            RpcResult<?> rpcResult = (RpcResult)o;
            return !this.statusCode.equals(rpcResult.statusCode)?false:(this.data != null?this.data.equals(rpcResult.data):rpcResult.data == null);
        }
    }

    public int hashCode() {
        int result = this.statusCode.hashCode();
        result = 31 * result + (this.data != null?this.data.hashCode():0);
        return result;
    }

    public String toString() {
        return "RpcResult{statusCode=" + this.statusCode + ", data=" + this.data + '}';
    }
}

