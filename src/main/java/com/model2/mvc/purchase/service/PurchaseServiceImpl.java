package com.model2.mvc.purchase.service;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.exception.RecordNotFoundException;
import com.model2.mvc.common.util.ListPageUtil;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.purchase.dao.PurchaseDAO;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranCode;

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
    public Purchase addPurchase(Purchase purchaseVO) {
        purchaseVO.setTranCode(TranCode.PURCHASE_DONE);
        this.purchaseDAO.insertPurchase(purchaseVO);
        return purchaseVO;
    }

    @Override
    public Purchase getPurchase(int tranNo) throws RecordNotFoundException {
        Purchase purchase = this.purchaseDAO.findById(tranNo);
        if (purchase == null) {
            throw new RecordNotFoundException("No record with tranNo=" +
                                              tranNo);
        }

        purchase.setTranStatus(purchase.getTranCode().getStatus());

        return purchase;
    }

    @Override
    public Map<String, Object> getPurchaseList(Search searchVO, String userId) {
        Map<String, Object> resultMap = this.purchaseDAO
                .findPurchaseListWithRange(searchVO, userId);

        ((List<Purchase>)resultMap.get("purchaseList"))
                .forEach(p -> p.setTranStatus(p.getTranCode().getStatus()));

        int currentPage = searchVO.getPage();
        List<Integer> pagesToDisplay = ListPageUtil
                .getPageSet((int)resultMap.get("count"),
                            currentPage,
                            CommonConstants.PAGE_SIZE,
                            CommonConstants.PAGE_DISPLAY);
        boolean previousPageSetBtnVisible = ListPageUtil
                .isPreviousPageSetAvailable((int)resultMap.get("count"),
                                            currentPage,
                                            CommonConstants.PAGE_SIZE,
                                            CommonConstants.PAGE_DISPLAY);
        boolean nextPageSetBtnVisible = ListPageUtil
                .isNextPageSetAvailable((int)resultMap.get("count"),
                                        currentPage,
                                        CommonConstants.PAGE_SIZE,
                                        CommonConstants.PAGE_DISPLAY);
        resultMap
                .put("page",
                     new Page(previousPageSetBtnVisible,
                              nextPageSetBtnVisible,
                              ListPageUtil
                                      .getPreviousPageSetEntry(currentPage,
                                                               CommonConstants.PAGE_DISPLAY),
                              ListPageUtil
                                      .getNextPageSetEntry(currentPage,
                                                           CommonConstants.PAGE_DISPLAY),
                              pagesToDisplay,
                              currentPage,
                              CommonConstants.PAGE_SIZE));
        return resultMap;
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
