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
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.AddPurchaseViewResponseDTO;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdateTranCodeRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseDAO purchaseDAO;
    private final ProductDAO productDAO;

    private PurchaseServiceImpl(PurchaseDAO purchaseDAO, ProductDAO productDAO) {
        this.purchaseDAO = purchaseDAO;
        this.productDAO = productDAO;
    }

    @Override
    public AddPurchaseResponseDTO addPurchase(AddPurchaseRequestDTO requestDTO) {
//        Purchase purchase = new Purchase().builder()
//                .buyer(new User().builder().userId(requestDTO.getBuyerId()).build())
//                .paymentOption(requestDTO.getPaymentOption())
//                .receiverName(requestDTO.getReceiverName())
//                .receiverPhone(requestDTO.getReceiverPhone())
//                .divyAddr(requestDTO.getDivyAddr())
//                .divyRequest(requestDTO.getDivyRequest())
//                .tranStatusCode(TranStatusCode.PURCHASE_DONE)
//                .orderDate(new Date(System.currentTimeMillis()))
//                .divyDate(requestDTO.getDivyDate())
//                .transactionProducts(requestDTO.getTransactionProductions())
//                .build();
//        this.purchaseDAO.insertPurchase(purchase.builder().tranStatusCode(TranStatusCode.PURCHASE_DONE).build());
//        return AddPurchaseResponseDTO.from(purchase);
        throw new UnsupportedOperationException();
    }

    @Override
    public GetPurchaseResponseDTO getPurchase(int tranNo) throws RecordNotFoundException {
        Optional<Purchase> purchase = this.purchaseDAO.findById(tranNo);
        return GetPurchaseResponseDTO.from(purchase.orElseThrow(() -> new RecordNotFoundException(
                "No record with tranNo=" + tranNo)));
    }

    @Override
    public ListPurchaseResponseDTO getPurchaseList(Search searchVO, User user) {
        ListData<Purchase> result = this.purchaseDAO.findPurchaseListByUserId(user.getUserId(),
                                                                              searchVO.getPage(),
                                                                              searchVO.getPageUnit());
        return new ListPurchaseResponseDTO().builder()
                .count(result.getCount())
                .purchaseList(result.getList())
                .pageInfo(getPageInfo(result.getCount(), searchVO.getPage()))
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
        Map<Integer, Product> prodNoProductMap = this.productDAO.findProductsByIds(prodNoQuantityMap.keySet()
                                                                                           .stream()
                                                                                           .collect(Collectors.toList()));
        List<Integer> prodNumbers = prodNoProductMap.keySet().stream().collect(Collectors.toList());

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
//        Purchase purchase = new Purchase().builder()
//                .tranNo(requestDTO.getTranNo())
//                .buyer(new User().builder().userId(requestDTO.getBuyerId()).build())
//                .paymentOption(requestDTO.getPaymentOption())
//                .receiverName(requestDTO.getReceiverName())
//                .receiverPhone(requestDTO.getReceiverPhone())
//                .divyAddr(requestDTO.getDivyAddr())
//                .divyRequest(requestDTO.getDivyRequest())
//                .orderDate(new Date(System.currentTimeMillis()))
//                .divyDate(requestDTO.getDivyDate())
//                .build();
//        this.purchaseDAO.updatePurchase(purchase);
//        return purchase;
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTranCode(UpdateTranCodeRequestDTO requestDTO) {
//        this.purchaseDAO.updateTranCode(new Purchase().builder()
//                                                .tranNo(requestDTO.getTranNo())
//                                                .tranStatusCode(requestDTO.getTranStatusCode())
//                                                .build());
        throw new UnsupportedOperationException();
    }
}
