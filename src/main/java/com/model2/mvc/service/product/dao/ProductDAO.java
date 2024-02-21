package com.model2.mvc.service.product.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.SQLContainer;
import com.model2.mvc.common.util.DAOTemplate;
import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.purchase.TranCode;

public class ProductDAO extends DAOTemplate {
    private static ProductDAO instance = new ProductDAO();

    private ProductDAO() {
    }

    public static ProductDAO getInstance() {
        return instance;
    }

    public Product findProduct(int prodNo) {
        String sql = SQLContainer.get("findproduct").orElse("");
        
        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setString(1, TranCode.PURCHASEABLE.getCode());
                stmt.setInt(2, prodNo);
            });
            ResultSet rs = super.executeQuery();

            Product result = null;
            if (rs.next()) {
                System.out.println("here");
                result = Product.builder()
                        .prodNo(rs.getInt("prod_no"))
                        .prodName(rs.getString("prod_name"))
                        .prodDetail(rs.getString("prod_detail"))
                        .manuDate(rs.getString("manufacture_day"))
                        .price(rs.getInt("price"))
                        .fileName(rs.getString("image_file"))
                        .regDate(rs.getDate("reg_date"))
                        .proTranCode(rs.getString("tran_status_code").trim())
                        .build();
                System.out.println(result);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            super.close();
        }
    }

    public Map<String, Object> getProductList(Search searchVO) {
        String sql = SQLContainer.get("getproductlist").orElse("");
        
        System.out.println(sql);

        if (searchVO.getSearchCondition() == null) {
            sql = String.format(sql, "");
        } else {
            switch (searchVO.getSearchCondition()) {
            case "0":
                sql = String.format(sql,
                                    "WHERE p.prod_no = " +
                                         searchVO.getSearchKeyword() +
                                         "\n");
                break;
            case "1":
                sql = String.format(sql,
                                    "WHERE p.prod_name LIKE '%" +
                                         searchVO.getSearchKeyword() +
                                         "%'\n");
                break;
            case "2":
                sql = String.format(sql,
                                    "WHERE p.price = " +
                                         searchVO.getSearchKeyword() +
                                         "\n");
                break;
            default:
                sql = String.format(sql, "");
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        List<Product> list = new ArrayList<>();
        resultMap.put("count", 0);
        resultMap.put("productList", list);
        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setString(1, TranCode.PURCHASEABLE.getCode());
                int page = searchVO.getPage();
                int pageUnit = searchVO.getPageUnit();
                stmt.setInt(2, (page - 1) * pageUnit + 1);
                stmt.setInt(3, page * pageUnit);
            });
            ResultSet rs = super.executeQuery();

            if (!rs.next()) {
                return resultMap;
            }

            resultMap.put("count", rs.getInt("count"));

            do {
                Product singleResult = Product.builder()
                        .prodNo(rs.getInt("prod_no"))
                        .prodName(rs.getString("prod_name"))
                        .prodDetail(rs.getString("prod_detail"))
                        .manuDate(rs.getString("manufacture_day"))
                        .price(rs.getInt("price"))
                        .fileName(rs.getString("image_file"))
                        .regDate(rs.getDate("reg_date"))
                        .proTranCode(rs.getString("tran_status_code").trim())
                        .build();
                list.add(singleResult);
                System.out.println("--------------------------------------");
                System.out.println(singleResult);
                System.out.println("----------------------------------------");
            } while (rs.next());
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
        return resultMap;
    }

    public void insertProduct(Product toInsert) {
        String sql = SQLContainer.get("insertproduct").orElse("");
        
        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setString(1, toInsert.getProdName());
                stmt.setString(2, toInsert.getProdDetail());
                stmt.setString(3, toInsert.getManuDate());
                stmt.setInt(4, toInsert.getPrice());
                stmt.setString(5, toInsert.getFileName());
                stmt.setObject(6, toInsert.getRegDate());
            });
            super.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
    }

    public void updateProduct(Product to) {
        String sql = SQLContainer.get("updateproduct").orElse("");
        
        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setString(1, to.getProdName());
                stmt.setString(2, to.getProdDetail());
                stmt.setString(3, to.getManuDate());
                stmt.setInt(4, to.getPrice());
                stmt.setString(5, to.getFileName());
                stmt.setObject(6, to.getRegDate());
                stmt.setInt(7, to.getProdNo());
            });
            super.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
    }
}
