package com.daniel.utils.json;

import com.daniel.utils.pojo.Scale6Decimal;

/**
 * Created by danielchang on 2018/7/18.
 */
public class Scale6DecimalDeserializer extends AbstractFixedScaleDecimalDeserializer<Scale6Decimal> {

    public static final Scale6DecimalDeserializer INSTANCE = new Scale6DecimalDeserializer();

    public Scale6DecimalDeserializer() {
        super(Scale6Decimal.class);
    }
}