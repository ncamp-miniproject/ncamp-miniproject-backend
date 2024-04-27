package com.model2.mvc.seller.repository;

import com.model2.mvc.seller.domain.Seller;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MybatisMapperSellerRepository implements SellerRepository {
    private final SqlSession sqlSession;

    @Override
    public boolean insert(Seller seller) {
        try {
            this.sqlSession.insert("SellerMapper.insert", seller);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAuthorized(String sellerId, boolean authorized) {
        try {
            this.sqlSession.update("SellerMapper.updateAuthorized", Map.of("sellerId", sellerId, "authorized", authorized));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Seller findById(String sellerId) {
        return this.sqlSession.selectOne("SellerMapper.findById", sellerId);
    }

    @Override
    public boolean deleteById(String sellerId) {
        try {
            this.sqlSession.delete("SellerMapper.deleteById", sellerId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
