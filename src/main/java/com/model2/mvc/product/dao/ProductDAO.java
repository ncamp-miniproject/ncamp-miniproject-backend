package com.model2.mvc.product.dao;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.db.DAOTemplate;
import com.model2.mvc.common.db.SQLContainer;
import com.model2.mvc.common.db.SQLName;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.product.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ProductDAO extends DAOTemplate {
    private static ProductDAO instance = new ProductDAO();

    private ProductDAO() {
    }

    public static ProductDAO getInstance() {
        return instance;
    }

    public Optional<Product> findById(int prodNo) {
        String sql = SQLContainer.get(SQLName.FIND_PRODUCT.getName()).orElse("");

        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setInt(1, prodNo);
            });
            ResultSet rs = super.executeQuery();

            Product result = null;
            if (rs.next()) {
                System.out.println("here");
                result = new Product().builder()
                        .prodNo(rs.getInt("prod_no"))
                        .prodName(rs.getString("prod_name"))
                        .prodDetail(rs.getString("prod_detail"))
                        .manuDate(rs.getDate("manufacture_day"))
                        .price(rs.getInt("price"))
                        .fileName(rs.getString("image_file"))
                        .regDate(rs.getDate("reg_date"))
                        .stock(rs.getInt("stock"))
                        .build();
                System.out.println(result);
            }
            return Optional.ofNullable(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            super.close();
        }
    }

    public Map<Integer, Product> findProductsByIds(List<Integer> ids) {
        String sql = SQLContainer.get(SQLName.FIND_PRODUCTS_BY_IDS.getName()).orElseThrow();

        String[] qms = new String[ids.size()];
        Arrays.fill(qms, "?");
        String toInsertValue = String.join(",", qms);
        sql = String.format(sql, toInsertValue);

        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                for (int i = 0; i < ids.size(); i++) {
                    stmt.setInt(i + 1, ids.get(i));
                }
            });
            ResultSet rs = super.executeQuery();

            Map<Integer, Product> resultMap = new HashMap<>();
            while (rs.next()) {
                Product singleResult = new Product().builder()
                        .prodNo(rs.getInt("prod_no"))
                        .prodName(rs.getString("prod_name"))
                        .prodDetail(rs.getString("prod_detail"))
                        .manuDate(rs.getDate("manufacture_day"))
                        .price(rs.getInt("price"))
                        .fileName(rs.getString("image_file"))
                        .regDate(rs.getDate("reg_date"))
                        .stock(rs.getInt("stock"))
                        .build();
                resultMap.put(singleResult.getProdNo(), singleResult);
            }
            return resultMap;
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        } finally {
            super.close();
        }
    }

    public ListData<Product> findProductListByProdName(Search searchVO) {
        String sql = SQLContainer.get(SQLName.FIND_PRODUCT_LIST_BY_PROD_NAME.getName()).orElse("");

        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setString(1, "%" + searchVO.getSearchKeyword() + "%");
                int page = searchVO.getPage();
                int pageUnit = searchVO.getPageUnit();
                System.out.println("page=" + page);
                System.out.println("pageUnit=" + pageUnit);
                stmt.setInt(2, (page - 1) * pageUnit + 1);
                stmt.setInt(3, page * pageUnit);
            });
            ResultSet rs = super.executeQuery();

            if (!rs.next()) {
                return new ListData<>(0, new ArrayList<>());
            }

            int count = rs.getInt("count");

            List<Product> list = new ArrayList<>();
            do {
                Product singleResult = new Product().builder()
                        .prodNo(rs.getInt("prod_no"))
                        .prodName(rs.getString("prod_name"))
                        .prodDetail(rs.getString("prod_detail"))
                        .manuDate(rs.getDate("manufacture_day"))
                        .price(rs.getInt("price"))
                        .fileName(rs.getString("image_file"))
                        .regDate(rs.getDate("reg_date"))
                        .stock(rs.getInt("stock"))
                        .build();
                list.add(singleResult);
                System.out.println("--------------------------------------");
                System.out.println(singleResult);
                System.out.println("----------------------------------------");
            } while (rs.next());
            System.out.println(list);
            return new ListData<>(count, list);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ListData<>(0, new ArrayList<>());
        } finally {
            super.close();
        }
    }

    public void insertProduct(Product toInsert) {
        String sql = SQLContainer.get("insertproduct").orElse("");

        System.out.println(sql);

        try {
            super.prepareStatement(sql, stmt -> {
                stmt.setString(1, toInsert.getProdName());
                stmt.setString(2, toInsert.getProdDetail());
                stmt.setObject(3, toInsert.getManuDate());
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
                stmt.setObject(3, to.getManuDate());
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
