package com.daniel.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.lang.annotation.*;

/**
 * Created by danielchang on 2018/8/20.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonResponseBody {

    String callback() default "callback";

    JsonGenerator.Feature[] enabledGens() default {};

    JsonGenerator.Feature[] disabledGens() default {};

    SerializationFeature[] enabledSers() default {};

    SerializationFeature[] disabledSers() default {};
}
