package com.daniel.utils.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by danielchang on 2018/6/24.
 */
public class JsonDateFormatDeserialize extends JsonDeserializer<Date> {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public JsonDateFormatDeserialize() {
    }

    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        if (date != null && !date.isEmpty()) {
            try {
                return DateUtils.parseDate(date, new String[] {DATE_PATTERN});
            } catch (ParseException var5) {
                throw new JsonParseException(jsonParser, "cannot parse date string: " + date,
                        jsonParser.getCurrentLocation(), var5);
            }
        } else {
            return null;
        }
    }
}
