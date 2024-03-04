package com.model2.mvc.purchase.dao.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.purchase.dao.PurchaseDAO;
import com.model2.mvc.purchase.domain.BuyerIdLimitationSearch;
import com.model2.mvc.purchase.domain.Purchase;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("myBatisMapperPurchaseDAO")
@Primary
public class MyBatisMapperPurchaseDAO implements PurchaseDAO {
    private SqlSession sqlSession;

    @Autowired
    public MyBatisMapperPurchaseDAO(@Qualifier("sqlSessionTemplate") SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Optional<Purchase> findById(int tranNo) {
        return Optional.ofNullable(this.sqlSession.selectOne("PurchaseMapper.findById", tranNo));
    }

    @Override
    public ListData<Purchase> findAllInPageSize(int startRowNum, int endRowNum) {
        BuyerIdLimitationSearch bs = new BuyerIdLimitationSearch();
        bs.setStartRowNum(startRowNum);
        bs.setEndRowNum(endRowNum);
        return doSelectList(bs);
    }

    @Override
    public ListData<Purchase> findPurchasesByUserId(BuyerIdLimitationSearch purchaseSearch) {
        return doSelectList(purchaseSearch);
    }

    private ListData<Purchase> doSelectList(BuyerIdLimitationSearch search) {
        ListData<Purchase> result = this.sqlSession.selectOne("PurchaseMapper.findList", search);
        return result == null ? new ListData<>(0, new ArrayList<>()) : result;
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
}
