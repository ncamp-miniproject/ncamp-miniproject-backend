package com.model2.mvc.seller.repository;

import com.model2.mvc.seller.domain.Seller;

public interface SellerRepository {

    public boolean insert(Seller seller);

    public boolean updateAuthorized(String sellerId, boolean authorized);

    public Seller findById(String sellerId);

    public boolean deleteById(String sellerId);
}
