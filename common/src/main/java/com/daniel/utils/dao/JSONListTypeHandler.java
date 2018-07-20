package com.daniel.utils.dao;

import com.daniel.utils.json.JsonMapper;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by danielchang on 2018/7/18.
 */
public class JSONListTypeHandler<T> extends BaseTypeHandler<List<T>> {
    private JavaType javaType;

    private JsonMapper jsonMapper = new JsonMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public JSONListTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        javaType = jsonMapper.getTypeFactory().constructParametrizedType(
                List.class, List.class, type);

    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, jsonMapper.writeValueAsString(parameter));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String i = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return valueOf(i);
        }
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String i = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return valueOf(i);
        }
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String i = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return valueOf(i);
        }
    }

    protected List<T> valueOf(String value) {

        return jsonMapper.readValue(value, javaType);
    }

}
