package com.model2.mvc.purchase.service;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.ListData;
import com.model2.mvc.common.dto.Page;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.common.exception.RecordNotFoundException;
import com.model2.mvc.common.util.ListPageUtil;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.purchase.dao.PurchaseDAO;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PurchaseServiceImpl implements PurchaseService {
    private static final PurchaseService instance = new PurchaseServiceImpl();

    private PurchaseDAO purchaseDAO;
    private ProductDAO productDAO;

    private PurchaseServiceImpl() {
        this.purchaseDAO = PurchaseDAO.getInstance();
        this.productDAO = ProductDAO.getInstance();
    }

    public static PurchaseService getInstance() {
        return instance;
    }

    @Override
    public Purchase addPurchase(Purchase purchase) {
        this.purchaseDAO.insertPurchase(purchase.builder()
                                                .tranStatusCode(TranStatusCode.PURCHASE_DONE)
                                                .build());
        return purchase;
    }

    @Override
    public Purchase getPurchase(int tranNo) throws RecordNotFoundException {
        Optional<Purchase> purchase = this.purchaseDAO.findById(tranNo);
        return purchase.orElseThrow(() -> new RecordNotFoundException("No record with tranNo=" + tranNo));
    }

    @Override
    public ListPurchaseResponseDTO getPurchaseList(Search searchVO, User user) {
        ListData<Purchase> result = this.purchaseDAO.findPurchaseListByUserId(user.getUserId(),
                                                                    searchVO.getPage(),
                                                                    searchVO.getPageUnit());

        int currentPage = searchVO.getPage();
        List<Integer> pagesToDisplay = ListPageUtil.getPageSet(result.getCount(),
                                                               currentPage,
                                                               CommonConstants.PAGE_SIZE,
                                                               CommonConstants.PAGE_DISPLAY);
        boolean previousPageSetBtnVisible = ListPageUtil.isPreviousPageSetAvailable(result.getCount(),
                                                                                    currentPage,
                                                                                    CommonConstants.PAGE_SIZE,
                                                                                    CommonConstants.PAGE_DISPLAY);
        boolean nextPageSetBtnVisible = ListPageUtil.isNextPageSetAvailable(result.getCount(),
                                                                            currentPage,
                                                                            CommonConstants.PAGE_SIZE,
                                                                            CommonConstants.PAGE_DISPLAY);
        return new ListPurchaseResponseDTO().builder()
                                            .count(result.getCount())
                                            .purchaseList(result.getList())
                                            .pageInfo(new Page(previousPageSetBtnVisible,
                                                               nextPageSetBtnVisible,
                                                               ListPageUtil.getPreviousPageSetEntry(currentPage,
                                                                                                    CommonConstants.PAGE_DISPLAY),
                                                               ListPageUtil.getNextPageSetEntry(currentPage,
                                                                                                CommonConstants.PAGE_DISPLAY),
                                                               pagesToDisplay,
                                                               currentPage,
                                                               CommonConstants.PAGE_SIZE))
                                            .loginUser(user)
                                            .build();
    }

    @Override
    public Map<String, Object> getSaleList(Search searchVO) {
        return this.purchaseDAO.getSaleList(searchVO);
    }

    @Override
    public Purchase updatePurchase(Purchase purchaseVO) {
        this.purchaseDAO.updatePurchase(purchaseVO);
        return purchaseVO;
    }

    @Override
    public void updateTranCode(Purchase purchaseVO) {
        this.purchaseDAO.updateTranCode(purchaseVO);
    }
}
