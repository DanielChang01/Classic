package com.daniel.utils.json;

import com.daniel.utils.pojo.AbstractFixedScaleDecimal;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;

/**
 * Created by danielchang on 2018/7/18.
 */
public abstract class AbstractFixedScaleDecimalDeserializer<T extends AbstractFixedScaleDecimal<T>> extends JsonDeserializer<T> {

    private Class<T> clazz;

    private Constructor<T> scaleCtor;

    protected AbstractFixedScaleDecimalDeserializer(Class<T> clazz) {
        this.clazz = clazz;
        this.scaleCtor = ClassUtils.getConstructorIfAvailable(this.clazz, BigDecimal.class);
        if (this.scaleCtor == null) {
            throw new RuntimeException("need constructor with BigDecimal argument");
        }
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (p.getCurrentTokenId()) {
            case JsonTokenId.ID_NUMBER_INT:
            case JsonTokenId.ID_NUMBER_FLOAT:
                return newFixScale(p.getDecimalValue());
            case JsonTokenId.ID_STRING:
                String text = p.getText().trim();
                if (text.length() == 0) {
                    return null;
                }
                return newFixScale(new BigDecimal(text));
            case JsonTokenId.ID_START_ARRAY:
                if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                    p.nextToken();
                    final T value = deserialize(p, ctxt);
                    if (p.nextToken() != JsonToken.END_ARRAY) {
                        throw ctxt.wrongTokenException(p, JsonToken.END_ARRAY,
                                "Attempted to unwrap single value array for single 'BigDecimal' value but there was more than a single value in the array"
                        );
                    }
                    return value;
                }
                break;
        }
        // Otherwise, no can do:
        throw ctxt.mappingException(clazz, p.getCurrentToken());
    }

    private T newFixScale(BigDecimal num) throws JsonProcessingException {
        return BeanUtils.instantiateClass(this.scaleCtor, num);
    }

}
