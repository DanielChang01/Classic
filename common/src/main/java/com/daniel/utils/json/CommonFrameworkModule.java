package com.daniel.utils.json;

import com.daniel.utils.pojo.Money;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by danielchang on 2018/7/18.
 */
public class CommonFrameworkModule extends SimpleModule {

    public CommonFrameworkModule() {
        super("CommonFrameworkModule", Version.unknownVersion());

        addSerializer(Money.class, MoneySerializer.INSTANCE);
        addDeserializer(Money.class, MoneyDeserializer.INSTANCE);
//
//        addSerializer(Scale6Decimal.class, Scale6DecimalSerializer.INSTANCE);
//        addDeserializer(Scale6Decimal.class, Scale6DecimalDeserializer.INSTANCE);
//
//        addDeserializer(ShortDate.class, new ShortDateJsonDeserializer());
//        addSerializer(ShortDate.class, new ShortDateJsonSerializer());
//        addDeserializer(DateRange.class, new DateRangeJsonDeserializer());
//        addSerializer(DateRange.class, new DateRangeJsonSerializer());
//        addDeserializer(ShortTime.class, new ShortTimeJsonDeserializer());
//        addSerializer(ShortTime.class, new ShortTimeJsonSerializer());
//        // sql
//        addSerializer(java.sql.Date.class, new SqlDateJsonSerializer());
//        addDeserializer(java.sql.Date.class, new SqlDateJsonDeserializer());
//        addSerializer(java.sql.Time.class, new SqlTimeJsonSerializer());
//        addDeserializer(java.sql.Time.class, new SqlTimeJsonDeserializer());
//        addSerializer(Timestamp.class, new TimestampJsonSerializer());
//        addDeserializer(Timestamp.class, new TimestampJsonDeserializer());
//        // 兼容 jackson 2.5 以下的版本, 对 Map.Entry 序列化做特殊处理
//        addSerializer(Map.Entry.class, new JsonSerializer<Map.Entry>() {
//            @Override
//            public void serialize(Map.Entry entry, JsonGenerator gen, SerializerProvider serializers)
//                    throws IOException {
//                gen.writeObject(new KeyValue(entry.getKey(), entry.getValue()));
//            }
//        });
    }
}
