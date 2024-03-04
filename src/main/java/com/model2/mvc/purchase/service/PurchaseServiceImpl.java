package com.model2.mvc.purchase.service;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.ListData;
import com.model2.mvc.common.dto.Page;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.common.exception.RecordNotFoundException;
import com.model2.mvc.common.util.ListPageUtil;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.dao.PurchaseDAO;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.AddPurchaseViewResponseDTO;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdateTranCodeRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseDAO purchaseDAO;
    private final ProductDAO productDAO;

    @Autowired
    private PurchaseServiceImpl( PurchaseDAO purchaseDAO, ProductDAO productDAO) {
        this.purchaseDAO = purchaseDAO;
        this.productDAO = productDAO;
    }

    @Override
    public AddPurchaseResponseDTO addPurchase(AddPurchaseRequestDTO requestDTO) {
        Purchase purchase = new Purchase();
        purchase.setBuyer(new User(requestDTO.getBuyerId()));
        purchase.setPaymentOption(requestDTO.getPaymentOption());
        purchase.setReceiverName(requestDTO.getReceiverName());
        purchase.setReceiverPhone(requestDTO.getReceiverPhone());
        purchase.setDivyAddr(requestDTO.getDivyAddr());
        purchase.setDivyRequest(requestDTO.getDivyRequest());
        purchase.setTranStatusCode(TranStatusCode.PURCHASE_DONE);
        purchase.setOrderDate(new Date(System.currentTimeMillis()));
        purchase.setDivyDate(requestDTO.getDivyDate());
        this.purchaseDAO.insertPurchase(purchase);
        return AddPurchaseResponseDTO.from(purchase);
    }

    @Override
    public GetPurchaseResponseDTO getPurchase(int tranNo) throws RecordNotFoundException {
        Optional<Purchase> purchase = this.purchaseDAO.findById(tranNo);
        return GetPurchaseResponseDTO.from(purchase.orElseThrow(() -> new RecordNotFoundException(
                "No record with tranNo=" + tranNo)));
    }

    @Override
    public ListPurchaseResponseDTO getPurchaseList(Search searchVO, User user) {
        ListData<Purchase> result = this.purchaseDAO.findPurchasesByUserId(user.getUserId(),
                                                                           searchVO.getStartRowNum(),
                                                                           searchVO.getEndRowNum());
        return new ListPurchaseResponseDTO().builder()
                .count(result.getCount())
                .purchaseList(result.getList())
                .pageInfo(getPageInfo(result.getCount(), searchVO.getStartRowNum()))
                .loginUser(user)
                .build();
    }

    private Page getPageInfo(int count, int currentPage) {
        List<Integer> pagesToDisplay = ListPageUtil.getPageSet(count,
                                                               currentPage,
                                                               CommonConstants.PAGE_SIZE,
                                                               CommonConstants.PAGE_DISPLAY);
        boolean previousPageSetBtnVisible = ListPageUtil.isPreviousPageSetAvailable(count,
                                                                                    currentPage,
                                                                                    CommonConstants.PAGE_SIZE,
                                                                                    CommonConstants.PAGE_DISPLAY);
        boolean nextPageSetBtnVisible = ListPageUtil.isNextPageSetAvailable(count,
                                                                            currentPage,
                                                                            CommonConstants.PAGE_SIZE,
                                                                            CommonConstants.PAGE_DISPLAY);
        int previousPageSetEntry = ListPageUtil.getPreviousPageSetEntry(currentPage, CommonConstants.PAGE_DISPLAY);
        int nextPageSetEntry = ListPageUtil.getNextPageSetEntry(currentPage, CommonConstants.PAGE_DISPLAY);
        return new Page(previousPageSetBtnVisible,
                        nextPageSetBtnVisible,
                        previousPageSetEntry,
                        nextPageSetEntry,
                        pagesToDisplay,
                        currentPage,
                        CommonConstants.PAGE_SIZE);
    }

    @Override
    public AddPurchaseViewResponseDTO getProductsWithQuantity(Map<Integer, Integer> prodNoQuantityMap) {
        Map<Integer, Product> prodNoProductMap = this.productDAO.findProductsByIds(new ArrayList<>(prodNoQuantityMap.keySet()));
        List<Integer> prodNumbers = new ArrayList<>(prodNoProductMap.keySet());

        int priceSum = prodNumbers.stream().reduce(0, (i, p) -> i + prodNoProductMap.get(p).getPrice(), Integer::sum);
        int quantitySum = prodNumbers.stream().reduce(0, (i, p) -> i + prodNoQuantityMap.get(p), Integer::sum);
        int productCount = prodNumbers.size();

        Map<Product, Integer> productQuantityMap = new HashMap<>();
        prodNumbers.forEach(n -> productQuantityMap.put(prodNoProductMap.get(n), prodNoQuantityMap.get(n)));
        return new AddPurchaseViewResponseDTO(priceSum, quantitySum, productCount, productQuantityMap);
    }

    @Override
    public ListPurchaseResponseDTO getSaleList(int page, User loginUser) {
        ListData<Purchase> purchases = this.purchaseDAO.findAllInPageSize(page, CommonConstants.PAGE_SIZE);
        return new ListPurchaseResponseDTO().builder()
                .count(purchases.getCount())
                .purchaseList(purchases.getList())
                .pageInfo(getPageInfo(purchases.getCount(), page))
                .loginUser(loginUser)
                .build();
    }

    @Override
    public Purchase updatePurchase(UpdatePurchaseRequestDTO requestDTO) {
        Purchase purchase = new Purchase();
        purchase.setTranNo(requestDTO.getTranNo());
        purchase.setBuyer(new User(requestDTO.getBuyerId()));
        purchase.setPaymentOption(requestDTO.getPaymentOption());
        purchase.setReceiverName(requestDTO.getReceiverName());
        purchase.setReceiverPhone(requestDTO.getReceiverPhone());
        purchase.setDivyAddr(requestDTO.getDivyAddr());
        purchase.setDivyRequest(requestDTO.getDivyRequest());
        purchase.setOrderDate(new Date(System.currentTimeMillis()));
        purchase.setDivyDate(requestDTO.getDivyDate());
        this.purchaseDAO.insertPurchase(purchase);
        return purchase;
    }

    @Override
    public void updateTranCode(UpdateTranCodeRequestDTO requestDTO) {
        Purchase purchase = new Purchase();
        purchase.setTranNo(requestDTO.getTranNo());
        purchase.setTranStatusCode(requestDTO.getTranStatusCode());
        this.purchaseDAO.updateTranCode(purchase);
    }
}
