package com.model2.mvc.common.mybatis.typehandler;

import com.model2.mvc.product.domain.OrderBy;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderByTypeHandler implements TypeHandler<OrderBy> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, OrderBy orderBy, JdbcType jdbcType)
    throws SQLException {
        preparedStatement.setString(i, orderBy.getColumnName());
    }

    @Override
    public OrderBy getResult(ResultSet resultSet, String s) throws SQLException {
        return OrderBy.findByColumnName(resultSet.getString(s)).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public OrderBy getResult(ResultSet resultSet, int i) throws SQLException {
        return OrderBy.findByColumnName(resultSet.getString(i)).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public OrderBy getResult(CallableStatement callableStatement, int i) throws SQLException {
        return OrderBy.findByColumnName(callableStatement.getString(i)).orElseThrow(IllegalArgumentException::new);
    }
}
