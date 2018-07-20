package com.daniel.utils.json;

import com.daniel.utils.pojo.Money;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by danielchang on 2018/7/18.
 */
public class MoneySerializer extends JsonSerializer<Money> {

    public static MoneySerializer INSTANCE = new MoneySerializer();

    private MoneySerializer() {
    }


    @Override
    public void serialize(Money money, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeRawValue(money != null ? money.toScaleMoneyString() : "null");
    }
}