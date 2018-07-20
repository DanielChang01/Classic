package com.daniel.utils.json;

import com.daniel.utils.pojo.AbstractFixedScaleDecimal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by danielchang on 2018/7/18.
 */
public abstract class AbstractFixedScaleDecimalSerializer<T extends AbstractFixedScaleDecimal<T>> extends JsonSerializer<T> {

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeRawValue(value != null ? value.toString() : "null");
    }

}