package com.model2.mvc.purchase.service;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.exception.RecordNotFoundException;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.dao.PurchaseDAO;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.AddPurchaseViewResponseDTO;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdateTranCodeRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("purchaseServiceImpl")
@Primary
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseDAO purchaseDAO;
    private final ProductDAO productDAO;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @Autowired
    private PurchaseServiceImpl(PurchaseDAO purchaseDAO, ProductDAO productDAO) {
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
        purchase.setOrderDate(LocalDate.now());
        purchase.setDivyDate(requestDTO.getDivyDate());
        purchase.setTransactionProductions(requestDTO.getTranProds());
        this.purchaseDAO.insertPurchase(purchase);
        purchase.getTransactionProductions().forEach(tp -> {
            Product product = this.productDAO.findById(tp.getProduct().getProdNo()).orElseThrow(RuntimeException::new);
            product.decrementStock(tp.getQuantity());
            this.productDAO.updateProduct(product);
        });
        return AddPurchaseResponseDTO.from(purchase);
    }

    @Override
    public GetPurchaseResponseDTO getPurchase(int tranNo) throws RecordNotFoundException {
        Optional<Purchase> purchase = this.purchaseDAO.findById(tranNo);
        return GetPurchaseResponseDTO.from(purchase.orElseThrow(() -> new RecordNotFoundException(
                "No record with tranNo=" + tranNo)));
    }

    @Override
    public ListPurchaseResponseDTO getPurchaseList(ListPurchaseRequestDTO requestDTO, String loginUserId) {
        int page = requestDTO.getPage();
        page = page == 0 ? 1 : page;
        int pageSize = requestDTO.getPageSize();
        pageSize = pageSize == 0 ? defaultPageSize : pageSize;
        String searchCondition = StringUtil.null2nullStr(requestDTO.getSearchCondition());
        String searchKeyword = StringUtil.null2nullStr(requestDTO.getSearchKeyword());

        Map<String, Object> search = new HashMap<>();
        search.put("buyerId", loginUserId);
        search.put("startRowNum", (page - 1) * pageSize + 1);
        search.put("endRowNum", page * pageSize);
        ListData<Purchase> result = this.purchaseDAO.findPurchasesByUserId(search);
        return new ListPurchaseResponseDTO().builder()
                .count(result.getCount())
                .purchaseList(result.getList())
                .pageInfo(getPageInfo(result.getCount(), page, pageSize))
                .loginUser(new User(loginUserId))
                .build();
    }

    private Page getPageInfo(int count, int currentPage, int pageSize) {
        return Page.of(currentPage, count, pageSize, defaultPageDisplay);
    }

    @Override
    public AddPurchaseViewResponseDTO getProductsWithQuantity(Map<Integer, Integer> prodNoQuantityMap) {
        Map<Integer, Product>
                prodNoProductMap
                = this.productDAO.findProductsByIds(new ArrayList<>(prodNoQuantityMap.keySet()));
        List<Integer> prodNumbers = new ArrayList<>(prodNoProductMap.keySet());

        int priceSum = prodNumbers.stream().reduce(0, (i, p) -> i + prodNoProductMap.get(p).getPrice(), Integer::sum);
        int quantitySum = prodNumbers.stream().reduce(0, (i, p) -> i + prodNoQuantityMap.get(p), Integer::sum);
        int productCount = prodNumbers.size();

        Map<Product, Integer> productQuantityMap = new HashMap<>();
        prodNumbers.forEach(n -> productQuantityMap.put(prodNoProductMap.get(n), prodNoQuantityMap.get(n)));
        return new AddPurchaseViewResponseDTO(priceSum, quantitySum, productCount, productQuantityMap);
    }

    @Override
    public ListPurchaseResponseDTO getSaleList(Integer page, Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? defaultPageSize : pageSize;
        ListData<Purchase> purchases = this.purchaseDAO.findAllInPageSize((page - 1) * pageSize + 1, page * pageSize);
        return new ListPurchaseResponseDTO().builder()
                .count(purchases.getCount())
                .purchaseList(purchases.getList())
                .pageInfo(getPageInfo(purchases.getCount(), page, pageSize))
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
        purchase.setOrderDate(LocalDate.now());
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
