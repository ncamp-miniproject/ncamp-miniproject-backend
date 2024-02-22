package com.model2.mvc.purchase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.SQLContainer;
import com.model2.mvc.common.util.DAOTemplate;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranCode;
import com.model2.mvc.user.domain.User;

public class PurchaseDAO extends DAOTemplate {
    private static final PurchaseDAO instance = new PurchaseDAO();

    private PurchaseDAO() {
    }

    public static PurchaseDAO getInstance() {
        return instance;
    }

    public Purchase findPurchase(int tranNo) {
        String sql = SQLContainer.get("findpurchase").orElse("");
        
        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setInt(1, tranNo);
            });
            ResultSet rs = super.executeQuery();

            if (rs.next()) {
                Purchase purchaseVO = generateVO(rs);
                return purchaseVO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
        return null;
    }

    public Map<String, Object> getPurchaseList(Search searchVO, String userId) {
        String sql = SQLContainer.get("getpurchaselist").orElse("");
        sql = String.format(sql,
                            (userId == null || userId.isEmpty()) ? "IS NOT NULL" : "= '" + userId + "'");
        
        System.out.println(sql);

        return getList(sql, stmt -> {
            int page = searchVO.getPage();
            int pageUnit = searchVO.getPageUnit();
            stmt.setInt(1, (page - 1) * pageUnit + 1);
            stmt.setInt(2, page * pageUnit);
        }, searchVO.getPage(), searchVO.getPageUnit());
    }

    private Map<String, Object> getList(String sql,
                                        PreparedStatementSetter statementSetter,
                                        int page,
                                        int pageUnit) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Purchase> purchaseList = new ArrayList<>();
        resultMap.put("purchaseList", purchaseList);
        resultMap.put("count", 0);
        try {
            super.prepareStatement(sql, statementSetter);
            ResultSet rs = super.executeQuery();

            if (!rs.next()) {
                return resultMap;
            }

            resultMap.put("count", rs.getInt("count"));

            do {
                Purchase singleRecord = generateVO(rs);
                purchaseList.add(singleRecord);
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
        System.out.println(resultMap);
        return resultMap;
    }

    private Purchase generateVO(ResultSet rs) throws SQLException {
        Purchase purchaseVO = new Purchase();
        purchaseVO.setTranNo(rs.getInt("tran_no"));
        User buyer = new User();
        buyer.setUserId(rs.getString("buyer_id"));
        purchaseVO.setBuyer(buyer);
        purchaseVO.setPaymentOption(rs.getString("payment_option"));
        purchaseVO.setReceiverName(rs.getString("receiver_name"));
        purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
        purchaseVO.setDivyAddr(rs.getString("demailaddr"));
        purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
        purchaseVO.setTranCode(TranCode.getTranCode(rs.getString("tran_status_code").trim()));
        purchaseVO.setOrderDate(rs.getDate("order_date"));
        purchaseVO.setDivyDate(rs.getDate("dlvy_date").toString());
        return purchaseVO;
    }

    public Map<String, Object> getSaleList(Search searchVO) {
        String sql = SQLContainer.get("getsalelist").orElse("");
        
        System.out.println(sql);

        return getList(sql, stmt -> {
            stmt.setString(1, TranCode.PURCHASEABLE.getCode());
        }, -1, -1);
    }

    public void insertPurchase(Purchase data) {
        String sql = SQLContainer.get("insertpurchase").orElse("");
        
        System.out.println(sql);

        update(sql, stmt -> {
            stmt.setString(2, data.getBuyer().getUserId());
            stmt.setString(3, data.getPaymentOption());
            stmt.setString(4, data.getReceiverName());
            stmt.setString(5, data.getReceiverPhone());
            stmt.setString(6, data.getDivyAddr());
            stmt.setString(7, data.getDivyRequest());
            stmt.setString(8, data.getTranCode().getCode());
            stmt.setObject(9, data.getOrderDate());
            stmt.setDate(10, DBUtil.parseIntoSqlDate(data.getDivyDate()));
        });
        System.out.println("here");
    }

    private int update(String sql, PreparedStatementSetter setter) {
        try {
            super.prepareStatement(sql, setter);
            System.out.println("update here");
            return super.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            super.close();
        }
    }

    public void updatePurchase(Purchase data) {
        String sql = SQLContainer.get("updatepurchase").orElse("");
        
        System.out.println(sql);

        update(sql, stmt -> {
            stmt.setString(1, data.getBuyer().getUserId());
            stmt.setString(2, data.getPaymentOption());
            stmt.setString(3, data.getReceiverName());
            stmt.setString(4, data.getReceiverPhone());
            stmt.setString(5, data.getDivyAddr());
            stmt.setString(6, data.getDivyRequest());
            stmt.setObject(7, data.getOrderDate());
            stmt.setDate(8, DBUtil.parseIntoSqlDate(data.getDivyDate()));
            stmt.setInt(9, data.getTranNo());
        });
    }

    public void updateTranCode(Purchase data) {
        String sql = SQLContainer.get("updatetrancode").orElse("");
        
        System.out.println(sql);
        
        update(sql, stmt -> {
            stmt.setString(1, data.getTranCode().getCode());
            stmt.setInt(2, data.getTranNo());
        });
    }
}
