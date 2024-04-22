package com.model2.mvc.purchase.repository.impl;

import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.repository.PurchaseRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("myBatisMapperPurchaseDAO")
@Primary
public class MyBatisMapperPurchaseRepository implements PurchaseRepository {
    private final SqlSession sqlSession;

    @Autowired
    public MyBatisMapperPurchaseRepository(@Qualifier("sqlSessionTemplate") SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Optional<Purchase> findById(int tranNo) {
        return Optional.ofNullable(this.sqlSession.selectOne("PurchaseMapper.findById", tranNo));
    }

    @Override
    public List<Purchase> findAllInPageSize(int page, int pageSize) {
        return doSelectList(getCommonSearchOptions(page, pageSize));
    }

    @Override
    public List<Purchase> findPurchasesByUserId(String buyerId, int page, int pageSize) {
        Map<String, Object> search = getCommonSearchOptions(page, pageSize);
        search.put("buyerId", buyerId);
        return doSelectList(search);
    }

    private Map<String, Object> getCommonSearchOptions(int page, int pageSize) {
        Map<String, Object> commonSearchOptions = new HashMap<>();
        commonSearchOptions.put("startRowNum", (page - 1) * pageSize + 1);
        commonSearchOptions.put("endRowNum", page * pageSize);
        return commonSearchOptions;
    }

    private List<Purchase> doSelectList(Map<String, Object> search) {
        List<Purchase> result = this.sqlSession.selectList("PurchaseMapper.findList", search);
        return result == null ? new ArrayList<>() : result;
    }

    @Override
    public int countAll() {
        return this.sqlSession.selectOne("PurchaseMapper.count", new HashMap<>());
    }

    @Override
    public int countByUserId(String buyerId) {
        HashMap<String, Object> search = new HashMap<>();
        search.put("buyerId", buyerId);
        return this.sqlSession.selectOne("PurchaseMapper.count", search);
    }

    @Override
    public boolean insertPurchase(Purchase purchase) {
        try {
            this.sqlSession.insert("PurchaseMapper.insertTransaction", purchase);
            final int tranNo = purchase.getTranNo();
            purchase.getTransactionProductions().forEach(tp -> {
                tp.setTranNo(tranNo);
                this.sqlSession.insert("PurchaseMapper.insertTranProd", tp);
            });
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePurchase(Purchase purchase) {
        try {
            this.sqlSession.update("PurchaseMapper.update", purchase);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTranCode(Purchase purchase) {
        return updatePurchase(purchase);
    }

    @Override
    public TranStatusCode findTranStatusCodeByTranNo(int tranNo) {
        String tranCode = this.sqlSession.selectOne("PurchaseMapper.findTranStatusCodeByTranNo", tranNo);
        return TranStatusCode.findTranCode(tranCode);
    }
}
