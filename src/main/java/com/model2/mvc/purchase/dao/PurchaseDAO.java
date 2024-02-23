package com.model2.mvc.purchase.dao;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.db.DAOTemplate;
import com.model2.mvc.common.db.DBUtil;
import com.model2.mvc.common.db.SQLContainer;
import com.model2.mvc.common.db.SQLName;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.PurchaseList;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.domain.TransactionProduction;
import com.model2.mvc.user.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PurchaseDAO extends DAOTemplate {
    private static final PurchaseDAO instance = new PurchaseDAO();

    private PurchaseDAO() {
    }

    public static PurchaseDAO getInstance() {
        return instance;
    }

    public Optional<Purchase> findById(int tranNo) {
        String sql = SQLContainer.get(SQLName.FIND_PURCHASE.getName())
                                 .orElseThrow(() -> new IllegalArgumentException(
                                         "No sql is found: " + SQLName.FIND_PURCHASE));

        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setInt(1, tranNo);
            });
            ResultSet rs = super.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }

            Purchase purchase = generatePurchase(rs);

            do {
                addTransactionProduction(rs, purchase);
            } while (rs.next());

            return Optional.of(purchase);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            super.close();
        }
    }

    public PurchaseList findPurchaseListByUserId(String userId, int page, int pageSize) {
        String sql = SQLContainer.get(SQLName.GET_PURCHASE_LIST.getName())
                                 .orElseThrow(() -> new IllegalArgumentException(
                                         "No sql is found: " + SQLName.GET_PURCHASE_LIST));

        System.out.println(sql);

        return generatePurchaseList(sql, stmt -> {
            stmt.setString(1, userId);
            stmt.setInt(2, (page - 1) * pageSize + 1);
            stmt.setInt(3, page * pageSize);
        });
    }

    private PurchaseList generatePurchaseList(String sql, PreparedStatementSetter statementSetter) {
        try {
            super.prepareStatement(sql, statementSetter);
            ResultSet rs = super.executeQuery();

            if (!rs.next()) {
                return new PurchaseList(0, new ArrayList<>());
            }

            int count = rs.getInt("count");

            Map<Integer, Purchase> purchaseMap = new HashMap<>();
            do {
                forOneRow(rs, purchaseMap);
            } while (rs.next());
            List<Purchase> list = purchaseMap.values()
                                             .stream()
                                             .toList();
            return new PurchaseList(count, list);
        } catch (SQLException e) {
            e.printStackTrace();
            return new PurchaseList(0, new ArrayList<>());
        } finally {
            super.close();
        }
    }

    private void forOneRow(ResultSet rs, Map<Integer, Purchase> resultMap) throws SQLException {
        int tranNo = rs.getInt("tran_no");
        if (!resultMap.containsKey(tranNo)) {
            resultMap.put(tranNo, generatePurchase(rs));
        }
        Purchase purchase = resultMap.get(tranNo);
        addTransactionProduction(rs, purchase);
    }

    private Purchase generatePurchase(ResultSet rs) throws SQLException {

        User buyer = new User();
        buyer.setUserId(rs.getString("buyer_id"));
        return Purchase.builder()
                       .tranNo(rs.getInt("tran_no"))
                       .buyer(buyer)
                       .paymentOption(rs.getString("payment_option"))
                       .receiverName(rs.getString("receiver_name"))
                       .receiverPhone(rs.getString("receiver_phone"))
                       .divyAddr(rs.getString("demailaddr"))
                       .divyRequest(rs.getString("dlvy_request"))
                       .tranStatusCode(TranStatusCode.getTranCode(rs.getString("tran_status_code")
                                                                    .trim()))
                       .orderDate(rs.getDate("order_date"))
                       .divyDate(rs.getDate("dlvy_date")
                                   .toString())
                       .build();
    }

    private void addTransactionProduction(ResultSet rs, Purchase purchase) throws SQLException {
        purchase.addTransactionProduction(new TransactionProduction(Product.builder()
                                                                           .prodNo(rs.getInt("prod_no"))
                                                                           .prodName(rs.getString("prod_name"))
                                                                           .prodDetail(rs.getString("prod_detail"))
                                                                           .manuDate(rs.getDate("manufacture_day"))
                                                                           .price(rs.getInt("price"))
                                                                           .fileName(rs.getString("image_file"))
                                                                           .regDate(rs.getDate("reg_date"))
                                                                           .stock(rs.getInt("stock"))
                                                                           .build(), rs.getInt("quantity")));
    }

    public Map<String, Object> getSaleList(Search searchVO) {
        //        String sql = SQLContainer.get("getsalelist")
        //                                 .orElse("");
        //
        //        System.out.println(sql);
        //
        //        return generatePurchaseList(sql, stmt -> {
        //            stmt.setString(1, TranCode.PURCHASEABLE.getCode());
        //        });
        throw new UnsupportedOperationException("Not implemented");
    }

    public void insertPurchase(Purchase data) {
        String sql = SQLContainer.get(SQLName.INSERT_PURCHASE.getName())
                                 .orElseThrow(() -> new IllegalArgumentException(
                                         "No sql is found: " + SQLName.INSERT_PURCHASE));

        System.out.println(sql);

        update(sql, stmt -> {
            stmt.setString(1,
                           data.getBuyer()
                               .getUserId());
            stmt.setString(2, data.getPaymentOption());
            stmt.setString(3, data.getReceiverName());
            stmt.setString(4, data.getReceiverPhone());
            stmt.setString(5, data.getDivyAddr());
            stmt.setString(6, data.getDivyRequest());
            stmt.setString(7,
                           data.getTranStatusCode()
                               .getCode());
            stmt.setObject(8, data.getOrderDate());
            stmt.setDate(9, DBUtil.parseIntoSqlDate(data.getDivyDate()));
        });
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
        String sql = SQLContainer.get("updatepurchase")
                                 .orElse("");

        System.out.println(sql);

        update(sql, stmt -> {
            stmt.setString(1,
                           data.getBuyer()
                               .getUserId());
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
        String sql = SQLContainer.get("updatetrancode")
                                 .orElse("");

        System.out.println(sql);

        update(sql, stmt -> {
            stmt.setString(1,
                           data.getTranStatusCode()
                               .getCode());
            stmt.setInt(2, data.getTranNo());
        });
    }
}
