package com.daniel.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;

import java.io.IOException;
import java.text.ParseException;

/**
 * 添加组合兼容
 * Created by danielchang on 2018/7/18.
 */
public class DateTimeDeserializer extends JodaDateDeserializerBase<ReadableInstant> {
    private JacksonDateFormat jacksonDateFormat = new JacksonDateFormat(JacksonDateFormat.PATTERN_YYYYMMDDHHMMSSSSS);

    public DateTimeDeserializer(Class<?> cls, JacksonJodaDateFormat format) {
        super(cls, format);
    }

    @Override
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat format) {
        return new DateTimeDeserializer(_valueClass, format);
    }

    @Override
    public ReadableDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();

        if (t == JsonToken.VALUE_NUMBER_INT) {
            DateTimeZone tz = _format.isTimezoneExplicit() ? _format.getTimeZone() : DateTimeZone.forTimeZone(ctxt.getTimeZone());
            return new DateTime(p.getLongValue(), tz);
        }
        if (t == JsonToken.VALUE_STRING) {
            String str = p.getText().trim();
            if (str.length() == 0) {
                return null;
            }
            // 08-Jul-2015, tatu: as per [datatype-joda#44], optional TimeZone inclusion
            // NOTE: on/off feature only for serialization; on deser should accept both
            int ix = str.indexOf('[');
            if (ix > 0) {
                int ix2 = str.lastIndexOf(']');
                String tzId = (ix2 < ix)
                        ? str.substring(ix + 1)
                        : str.substring(ix + 1, ix2);
                DateTimeZone tz;
                try {
                    tz = DateTimeZone.forID(tzId);
                }
                catch (IllegalArgumentException e) {
                    throw ctxt.mappingException(String.format("Unknown DateTimeZone id '%s'", tzId));
                }
                str = str.substring(0, ix);

                /* 12-Jul-2015, tatu: Initially planned to support "timestamp[zone-id]"
				 *    format as well as textual, but since JSR-310 datatype (Java 8 datetime)
                 *    does not support it, was left out of 2.6.
                 */
				/*
				// One more thing; do we have plain timestamp?
                if (_allDigits(str)) {
                    return new DateTime(Long.parseLong(str), tz);
                }
                */
                return _format.createParser(ctxt)
                        .parseDateTime(str)
                        .withZone(tz);
            }
            // Not sure if it should use timezone or not...
            // 15-Sep-2015, tatu: impl of 'createParser()' SHOULD handle all timezone/locale setup
            try {
                return new DateTime(jacksonDateFormat.parse(str));
            }
            catch (ParseException e) {
                return _format.createParser(ctxt).parseDateTime(str);
            }
        }
        return (ReadableDateTime) ctxt.handleUnexpectedToken(handledType(), p);
    }
}
