package com.model2.mvc.product.repository.impl;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.product.domain.OrderBy;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.repository.ProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("myBatisMapperProductRepository")
@Primary
public class MyBatisMapperProductRepository implements ProductRepository {

    private final SqlSession sqlSession;

    @Autowired
    public MyBatisMapperProductRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public int countAll(Integer categoryNo) {
        Map<String, Object> search = generateCommonOptionSearch(SearchCondition.NO_CONDITION, categoryNo);
        return this.sqlSession.selectOne("ProductMapper.count", search);
    }

    @Override
    public int countAll(Integer categoryNo, String seller) {
        Map<String, Object> search = generateCommonOptionSearch(SearchCondition.NO_CONDITION, categoryNo);
        search.put("seller", seller);
        return this.sqlSession.selectOne("ProductMapper.count", search);
    }

    @Override
    public int countByProdName(String prodName, boolean match, Integer categoryNo) {
        Map<String, Object> search = generateCommonOptionSearch(SearchCondition.BY_NAME, categoryNo);
        search.put("prodName", match ? prodName : "%" + prodName + "%");
        return this.sqlSession.selectOne("ProductMapper.count", search);
    }

    @Override
    public int countByProdName(String prodName, boolean match, Integer categoryNo, String seller) {
        Map<String, Object> search = generateCommonOptionSearch(SearchCondition.BY_NAME, categoryNo);
        search.put("prodName", match ? prodName : "%" + prodName + "%");
        search.put("seller", seller);
        return this.sqlSession.selectOne("ProductMapper.count", search);
    }

    @Override
    public int countByPriceRange(Integer lowerBound, Integer upperBound, Integer categoryNo) {
        Map<String, Object> search = generateCommonOptionSearch(SearchCondition.BY_INTEGER_RANGE, categoryNo);
        search.put("lowerBound", lowerBound);
        search.put("upperBound", upperBound);
        return this.sqlSession.selectOne("ProductMapper.count", search);
    }

    @Override
    public int countByPriceRange(Integer lowerBound, Integer upperBound, Integer categoryNo, String seller) {
        Map<String, Object> search = generateCommonOptionSearch(SearchCondition.BY_INTEGER_RANGE, categoryNo);
        search.put("lowerBound", lowerBound);
        search.put("upperBound", upperBound);
        search.put("seller", seller);
        return this.sqlSession.selectOne("ProductMapper.count", search);
    }

    @Override
    public Optional<Product> findById(int prodNo) {
        return Optional.ofNullable(this.sqlSession.selectOne("ProductMapper.findById", prodNo));
    }

    @Override
    public Map<Integer, Product> findProductsByIds(List<Integer> ids) {
        Map<String, Object> search = new HashMap<>();
        search.put("searchCondition", SearchCondition.BY_ID.getConditionCode());

        List<Product> productsFound = this.sqlSession.selectList("ProductMapper.findProductsByIds", ids);
        if (productsFound == null) {
            return new HashMap<>();
        }
        Map<Integer, Product> idProductMap = new HashMap<>();
        productsFound.forEach(p -> idProductMap.put(p.getProdNo(), p));
        return idProductMap;
    }

    @Override
    public List<Product> findListByProdName(String prodName,
                                            boolean match,
                                            int page,
                                            int pageSize,
                                            Integer categoryNo) {
        Map<String, Object> search = generateCommonOptionSearch(page, pageSize, SearchCondition.BY_NAME, categoryNo);
        search.put("prodName", match ? prodName : "%" + prodName + "%");
        return findList(search);
    }

    @Override
    public List<Product> findListByProdName(String prodName,
                                            boolean match,
                                            int page,
                                            int pageSize,
                                            Integer categoryNo,
                                            OrderBy orderBy,
                                            Boolean ascend) {
        Map<String, Object> search = generateCommonOptionSearch(page,
                                                                pageSize,
                                                                SearchCondition.BY_NAME,
                                                                categoryNo,
                                                                orderBy,
                                                                ascend);
        search.put("prodName", match ? prodName : "%" + prodName + "%");
        return findList(search);
    }

    @Override
    public List<Product> findListByProdName(String prodName,
                                            boolean match,
                                            int page,
                                            int pageSize,
                                            Integer categoryNo,
                                            OrderBy orderBy,
                                            Boolean ascend,
                                            String seller) {
        Map<String, Object> search = generateCommonOptionSearch(page,
                                                                pageSize,
                                                                SearchCondition.BY_NAME,
                                                                categoryNo,
                                                                orderBy,
                                                                ascend,
                                                                seller);
        search.put("prodName", match ? prodName : "%" + prodName + "%");
        return findList(search);
    }

    private Map<String, Object> generateCommonOptionSearch(SearchCondition searchCondition, Integer categoryNo) {
        Map<String, Object> search = new HashMap<>();
        search.put("searchCondition", searchCondition == null ? null : searchCondition.getConditionCode());
        search.put("categoryNo", categoryNo);
        return search;
    }

    private Map<String, Object> generateCommonOptionSearch(int page,
                                                           int pageSize,
                                                           SearchCondition searchCondition,
                                                           Integer categoryNo) {
        Map<String, Object> search = generateCommonOptionSearch(searchCondition, categoryNo);
        search.put("startRowNum", (page - 1) * pageSize + 1);
        search.put("endRowNum", page * pageSize);
        return search;
    }

    private Map<String, Object> generateCommonOptionSearch(int page,
                                                           int pageSize,
                                                           SearchCondition searchCondition,
                                                           Integer categoryNo,
                                                           OrderBy orderBy,
                                                           Boolean ascend) {
        Map<String, Object> search = generateCommonOptionSearch(page, pageSize, searchCondition, categoryNo);
        search.put("orderBy", orderBy);
        search.put("ascend", ascend);
        return search;
    }

    private Map<String, Object> generateCommonOptionSearch(int page,
                                                           int pageSize,
                                                           SearchCondition searchCondition,
                                                           Integer categoryNo,
                                                           OrderBy orderBy,
                                                           Boolean ascend,
                                                           String seller) {
        Map<String, Object> search = generateCommonOptionSearch(page, pageSize, searchCondition, categoryNo);
        search.put("orderBy", orderBy);
        search.put("ascend", ascend);
        search.put("seller", seller);
        return search;
    }

    private List<Product> findList(Map<String, Object> search) {
        List<Product> result = this.sqlSession.selectList("ProductMapper.findList", search);
        return result == null ? new ArrayList<>() : result;
    }

    @Override
    public List<Product> findListByPriceRange(Integer lowerBound,
                                              Integer upperBound,
                                              int page,
                                              int pageSize,
                                              Integer categoryNo) {
        Map<String, Object> search = generateCommonOptionSearch(page,
                                                                pageSize,
                                                                SearchCondition.BY_INTEGER_RANGE,
                                                                categoryNo);
        search.put("lowerBound", lowerBound);
        search.put("upperBound", upperBound);
        return findList(search);
    }

    @Override
    public List<Product> findListByPriceRange(Integer lowerBound,
                                              Integer upperBound,
                                              int page,
                                              int pageSize,
                                              Integer categoryNo,
                                              OrderBy orderBy,
                                              Boolean ascend) {
        Map<String, Object> search = generateCommonOptionSearch(page,
                                                                pageSize,
                                                                SearchCondition.BY_INTEGER_RANGE,
                                                                categoryNo,
                                                                orderBy,
                                                                ascend);
        search.put("lowerBound", lowerBound);
        search.put("upperBound", upperBound);
        return findList(search);
    }

    @Override
    public List<Product> findListByPriceRange(Integer lowerBound,
                                              Integer upperBound,
                                              int page,
                                              int pageSize,
                                              Integer categoryNo,
                                              OrderBy orderBy,
                                              Boolean ascend,
                                              String seller) {
        Map<String, Object> search = generateCommonOptionSearch(page,
                                                                pageSize,
                                                                SearchCondition.BY_INTEGER_RANGE,
                                                                categoryNo,
                                                                orderBy,
                                                                ascend,
                                                                seller);
        search.put("lowerBound", lowerBound);
        search.put("upperBound", upperBound);
        return findList(search);
    }

    @Override
    public List<Product> findAllInCategory(int page, int pageSize, Integer categoryNo) {
        return findList(generateCommonOptionSearch(page, pageSize, null, categoryNo));
    }

    @Override
    public List<Product> findAllInCategory(int page, int pageSize, Integer categoryNo, String seller) {
        Map<String, Object> search = generateCommonOptionSearch(page, pageSize, null, categoryNo);
        search.put("seller", seller);
        return findList(search);
    }

    @Override
    public List<Product> findAllInCategory(int page,
                                           int pageSize,
                                           Integer categoryNo,
                                           OrderBy orderBy,
                                           Boolean ascend) {
        return findList(generateCommonOptionSearch(page, pageSize, null, categoryNo, orderBy, ascend));
    }

    @Override
    public List<Product> findAllInCategory(int page,
                                           int pageSize,
                                           Integer categoryNo,
                                           OrderBy orderBy,
                                           Boolean ascend,
                                           String seller) {
        Map<String, Object> search = generateCommonOptionSearch(page, pageSize, null, categoryNo, orderBy, ascend);
        search.put("seller", seller);
        return findList(search);
    }

    @Override
    public boolean insertProduct(Product toInsert) {
        try {
            this.sqlSession.insert("ProductMapper.insert", toInsert);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product to) {
        try {
            this.sqlSession.update("ProductMapper.update", to);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
