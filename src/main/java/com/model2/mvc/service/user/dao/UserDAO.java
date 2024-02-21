package com.model2.mvc.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DAOTemplate;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.user.domain.User;

public class UserDAO extends DAOTemplate {

    public UserDAO() {
    }

    public void insertUser(User userVO) throws Exception {

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

    public User findUser(String userId) throws Exception {

        Connection con = DBUtil.getConnection();

        String sql = "select * from USERS where USER_ID=?";
        
        System.out.println(sql);

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, userId);

        ResultSet rs = stmt.executeQuery();

        User userVO = null;
        while (rs.next()) {
            userVO = new User();
            userVO.setUserId(rs.getString("USER_ID"));
            userVO.setUserName(rs.getString("USER_NAME"));
            userVO.setPassword(rs.getString("PASSWORD"));
            userVO.setRole(rs.getString("ROLE"));
            userVO.setSsn(rs.getString("SSN"));
            userVO.setPhone(rs.getString("CELL_PHONE"));
            userVO.setAddr(rs.getString("ADDR"));
            userVO.setEmail(rs.getString("EMAIL"));
            userVO.setRegDate(rs.getDate("REG_DATE"));
        }

        con.close();

        return userVO;
    }

    public Map<String, Object> getUserList(Search searchVO)
            throws Exception {

        Connection con = DBUtil.getConnection();

        String sql = "SELECT\n" +
                     "    v.USER_ID \"user_id\",\n" +
                     "    v.USER_NAME \"user_name\",\n" +
                     "    v.PASSWORD \"password\",\n" +
                     "    v.ROLE \"role\",\n" +
                     "    v.SSN \"ssn\",\n" +
                     "    v.CELL_PHONE \"cell_phone\",\n" +
                     "    v.ADDR \"addr\",\n" +
                     "    v.EMAIL \"email\",\n" +
                     "    v.REG_DATE \"reg_date\"\n" +
                     "    v.COUNT \"count\"" +
                     "FROM (\n" +
                     "    SELECT\n" +
                     "        ROW_NUMBER() OVER (ORDER BY u.user_id ASC) ROW_NUM,\n" +
                     "        u.user_id USER_ID,\n" +
                     "        user_name USER_NAME,\n" +
                     "        u.password PASSWORD,\n" +
                     "        u.role ROLE,\n" +
                     "        u.ssn SSN,\n" +
                     "        u.cell_phone CELL_PHONE,\n" +
                     "        u.addr ADDR,\n" +
                     "        u.email EMAIL,\n" +
                     "        u.reg_date REG_DATE\n" +
                     "        COUNT(*) OVER () COUNT" +
                     "    FROM users u\n" +
                     "    %s\n" +
                     "    ORDER BY u.user_id ASC\n" +
                     ") v\n" +
                     "WHERE v.ROW_NUM BETWEEN ? AND ?";
        
        System.out.println(sql);
        
        if (searchVO.getSearchCondition() != null) {
            if (searchVO.getSearchCondition().equals("0")) {
                sql = String.format(sql, "WHERE u.user_id = '" + searchVO.getSearchKeyword() + "'");
            } else if (searchVO.getSearchCondition().equals("1")) {
                sql = String.format(sql, "WHERE u.user_name = '" + searchVO.getSearchKeyword() + "'");
            }
        } else {
            sql = String.format(sql, "");
        }

        super.prepareStatement(sql, stmt -> {
            int page = searchVO.getPage();
            int pageSize = searchVO.getPageUnit();
            stmt.setInt(1, (page - 1) * pageSize + 1);
            stmt.setInt(2, page * pageSize);
        });
        ResultSet rs = super.executeQuery();

        Map<String, Object> map = new HashMap<String, Object>();
        
        if (!rs.next()) {
            map.put("count", 0);
            map.put("list", new ArrayList<>());
            return map;
        }
        map.put("count", rs.getInt("count"));
        
        List<User> list = new ArrayList<>();
        do {
            User vo = new User();
            vo.setUserId(rs.getString("user_id"));
            vo.setUserName(rs.getString("user_name"));
            vo.setPassword(rs.getString("password"));
            vo.setRole(rs.getString("role"));
            vo.setSsn(rs.getString("ssn"));
            vo.setPhone(rs.getString("cell_phone"));
            vo.setAddr(rs.getString("addr"));
            vo.setEmail(rs.getString("email"));
            vo.setRegDate(rs.getDate("reg_date"));
            list.add(vo);
        } while (rs.next());
        map.put("list", list);
        
        super.close();
        
        return map;
    }

    public void updateUser(User userVO) throws Exception {

        Connection con = DBUtil.getConnection();

        String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
        
        System.out.println(sql);

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, userVO.getUserName());
        stmt.setString(2, userVO.getPhone());
        stmt.setString(3, userVO.getAddr());
        stmt.setString(4, userVO.getEmail());
        stmt.setString(5, userVO.getUserId());
        stmt.executeUpdate();

        con.close();
    }
}