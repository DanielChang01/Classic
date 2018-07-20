package com.daniel.utils.json;

import com.daniel.utils.pojo.Currency;
import com.daniel.utils.pojo.Money;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by danielchang on 2018/7/18.
 */
public class MoneyDeserializer extends JsonDeserializer<Money> {

    public static MoneyDeserializer INSTANCE = new MoneyDeserializer();

    private MoneyDeserializer() {
    }

    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        BigDecimal amount = node.get("amount").decimalValue();
        String currency = node.get("currency").asText();
        return Money.of(amount, Currency.valueOf(currency));
    }
}
