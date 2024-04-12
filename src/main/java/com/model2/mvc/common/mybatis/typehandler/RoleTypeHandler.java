package com.model2.mvc.common.mybatis.typehandler;

import com.model2.mvc.user.domain.Role;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleTypeHandler implements TypeHandler<Role> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Role role, JdbcType jdbcType)
    throws SQLException {
        preparedStatement.setString(i, role.name());
    }

    @Override
    public Role getResult(ResultSet resultSet, String s) throws SQLException {
        String roleCode = resultSet.getString(s).trim().toUpperCase();
        Optional<Role> role = Role.of(roleCode);
        return role.orElseThrow(() -> new RuntimeException("No such code: " + role));
    }

    @Override
    public Role getResult(ResultSet resultSet, int i) throws SQLException {
        String roleCode = resultSet.getString(i).trim().toUpperCase();
        Optional<Role> role = Role.of(roleCode);
        return role.orElseThrow(() -> new RuntimeException("No such code: " + role));
    }

    @Override
    public Role getResult(CallableStatement callableStatement, int i) throws SQLException {
        String roleCode = callableStatement.getString(i).trim().toUpperCase();
        Optional<Role> role = Role.of(roleCode);
        return role.orElseThrow(() -> new RuntimeException("No such code: " + role));
    }
}
