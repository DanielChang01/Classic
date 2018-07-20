package com.daniel.utils.json;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by danielchang on 2018/7/18.
 */
public class JsonException  extends NestedRuntimeException {

    private static final long serialVersionUID = 5655092610869579522L;

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
