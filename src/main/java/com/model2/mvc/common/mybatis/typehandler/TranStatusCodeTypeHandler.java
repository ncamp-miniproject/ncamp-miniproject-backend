package com.model2.mvc.common.mybatis.typehandler;

import com.model2.mvc.purchase.domain.TranStatusCode;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TranStatusCodeTypeHandler implements TypeHandler<TranStatusCode> {

    @Override
    public void setParameter(PreparedStatement preparedStatement,
                             int i,
                             TranStatusCode tranStatusCode,
                             JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, tranStatusCode.getCode());
    }

    @Override
    public TranStatusCode getResult(ResultSet resultSet, String s) throws SQLException {
        return TranStatusCode.findTranCode(resultSet.getString(s).trim());
    }

    @Override
    public TranStatusCode getResult(ResultSet resultSet, int i) throws SQLException {
        return TranStatusCode.findTranCode(resultSet.getString(i).trim());
    }

    @Override
    public TranStatusCode getResult(CallableStatement callableStatement, int i) throws SQLException {
        return TranStatusCode.findTranCode(callableStatement.getString(i).trim());
    }
}
