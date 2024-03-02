package com.model2.mvc.common.mybatis.typehandler;

import com.model2.mvc.purchase.domain.PaymentOption;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentOptionTypeHandler implements TypeHandler<PaymentOption> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, PaymentOption paymentOption, JdbcType jdbcType)
    throws SQLException {
        preparedStatement.setString(i, paymentOption.getCode());
    }

    @Override
    public PaymentOption getResult(ResultSet resultSet, String s) throws SQLException {
        String code = resultSet.getString(s);
        return PaymentOption.get(code.trim());
    }

    @Override
    public PaymentOption getResult(ResultSet resultSet, int i) throws SQLException {
        String code = resultSet.getString(i);
        return PaymentOption.get(code.trim());
    }

    @Override
    public PaymentOption getResult(CallableStatement callableStatement, int i) throws SQLException {
        String code = callableStatement.getString(i);
        return PaymentOption.get(code.trim());
    }
}
