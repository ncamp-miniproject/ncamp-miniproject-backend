package com.model2.mvc.user.dao.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.db.DAOTemplate;
import com.model2.mvc.common.db.DBUtil;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.user.dao.UserDAO;
import com.model2.mvc.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

@Deprecated
public class PlainJDBCUserDAO extends DAOTemplate implements UserDAO {

    public PlainJDBCUserDAO() {
    }

    @Override
    public void insertUser(User userVO) throws SQLException {

        Connection con = DBUtil.getConnection();

        String sql = "insert into USERS values (?,?,?,'user',?,?,?,?,sysdate)";

        System.out.println(sql);

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, userVO.getUserId());
        stmt.setString(2, userVO.getUserName());
        stmt.setString(3, userVO.getPassword());
        stmt.setString(4, userVO.getSsn());
        stmt.setString(5, userVO.getPhone());
        stmt.setString(6, userVO.getAddr());
        stmt.setString(7, userVO.getEmail());
        stmt.executeUpdate();

        con.close();
    }

    @Override
    public User findByUserId(String userId) throws SQLException {
//
//        Connection con = DBUtil.getConnection();
//
//        String sql = "select * from USERS where USER_ID=?";
//
//        System.out.println(sql);
//
//        PreparedStatement stmt = con.prepareStatement(sql);
//        stmt.setString(1, userId);
//
//        ResultSet rs = stmt.executeQuery();
//
//        User userVO = null;
//        while (rs.next()) {
//            userVO = new User().builder()
//                    .userId(rs.getString("USER_ID"))
//                    .userName(rs.getString("USER_NAME"))
//                    .password(rs.getString("PASSWORD"))
//                    .role(rs.getString("ROLE"))
//                    .ssn(rs.getString("SSN"))
//                    .phone(rs.getString("CELL_PHONE"))
//                    .addr(rs.getString("ADDR"))
//                    .email(rs.getString("EMAIL"))
//                    .regDate(rs.getDate("REG_DATE"))
//                    .build();
//        }
//
//        con.close();
//
//        return userVO;
        throw new UnsupportedOperationException();
    }

    @Override
    public ListData<User> findByUserName(String userName) throws SQLException {
//
//        Connection con = DBUtil.getConnection();
//
//        String sql = "SELECT\n" +
//                     "    v.USER_ID \"user_id\",\n" +
//                     "    v.USER_NAME \"user_name\",\n" +
//                     "    v.PASSWORD \"password\",\n" +
//                     "    v.ROLE \"role\",\n" +
//                     "    v.SSN \"ssn\",\n" +
//                     "    v.CELL_PHONE \"cell_phone\",\n" +
//                     "    v.ADDR \"addr\",\n" +
//                     "    v.EMAIL \"email\",\n" +
//                     "    v.REG_DATE \"reg_date\"\n" +
//                     "    v.COUNT \"count\"" +
//                     "FROM (\n" +
//                     "    SELECT\n" +
//                     "        ROW_NUMBER() OVER (ORDER BY u.user_id ASC) ROW_NUM,\n" +
//                     "        u.user_id USER_ID,\n" +
//                     "        user_name USER_NAME,\n" +
//                     "        u.password PASSWORD,\n" +
//                     "        u.role ROLE,\n" +
//                     "        u.ssn SSN,\n" +
//                     "        u.cell_phone CELL_PHONE,\n" +
//                     "        u.addr ADDR,\n" +
//                     "        u.email EMAIL,\n" +
//                     "        u.reg_date REG_DATE\n" +
//                     "        COUNT(*) OVER () COUNT" +
//                     "    FROM users u\n" +
//                     "    %s\n" +
//                     "    ORDER BY u.user_id ASC\n" +
//                     ") v\n" +
//                     "WHERE v.ROW_NUM BETWEEN ? AND ?";
//
//        System.out.println(sql);
//
//        if (searchVO.getSearchCondition() != null) {
//            if (searchVO.getSearchCondition().equals("0")) {
//                sql = String.format(sql, "WHERE u.user_id = '" + searchVO.getSearchKeyword() + "'");
//            } else if (searchVO.getSearchCondition().equals("1")) {
//                sql = String.format(sql, "WHERE u.user_name = '" + searchVO.getSearchKeyword() + "'");
//            }
//        } else {
//            sql = String.format(sql, "");
//        }
//
//        super.prepareStatement(sql, stmt -> {
//            int page = searchVO.getPage();
//            int pageSize = searchVO.getPageUnit();
//            stmt.setInt(1, (page - 1) * pageSize + 1);
//            stmt.setInt(2, page * pageSize);
//        });
//        ResultSet rs = super.executeQuery();
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        if (!rs.next()) {
//            map.put("count", 0);
//            map.put("list", new ArrayList<>());
//            return map;
//        }
//        map.put("count", rs.getInt("count"));
//
//        List<User> list = new ArrayList<>();
//        do {
//            User vo = new User().builder()
//                    .userId(rs.getString("user_id"))
//                    .userName(rs.getString("user_name"))
//                    .password(rs.getString("password"))
//                    .role(rs.getString("role"))
//                    .ssn(rs.getString("ssn"))
//                    .phone(rs.getString("cell_phone"))
//                    .addr(rs.getString("addr"))
//                    .email(rs.getString("email"))
//                    .regDate(rs.getDate("reg_date"))
//                    .build();
//            list.add(vo);
//        } while (rs.next());
//        map.put("list", list);
//
//        super.close();
//
//        return map;
        throw new UnsupportedOperationException();
    }

    @Override
    public User updateUser(User userVO) throws SQLException {
//
//        Connection con = DBUtil.getConnection();
//
//        String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
//
//        System.out.println(sql);
//
//        PreparedStatement stmt = con.prepareStatement(sql);
//        stmt.setString(1, userVO.getUserName());
//        stmt.setString(2, userVO.getPhone());
//        stmt.setString(3, userVO.getAddr());
//        stmt.setString(4, userVO.getEmail());
//        stmt.setString(5, userVO.getUserId());
//        stmt.executeUpdate();
//
//        con.close();
        throw new UnsupportedOperationException();
    }
}