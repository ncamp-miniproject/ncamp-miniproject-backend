package com.model2.mvc.purchase.service.impl;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Pagination;
import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.exception.RecordNotFoundException;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.repository.ProductRepository;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDto;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDto;
import com.model2.mvc.purchase.dto.request.UpdateTranCodeRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDto;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDto;
import com.model2.mvc.purchase.dto.response.TranStatusCodeResponseDto;
import com.model2.mvc.purchase.repository.PurchaseRepository;
import com.model2.mvc.purchase.service.PurchaseService;
import com.model2.mvc.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("purchaseServiceImpl")
@Primary
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
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
        this.purchaseRepository.insertPurchase(purchase);
        purchase.getTransactionProductions().forEach(tp -> {
            Product product = this.productRepository.findById(tp.getProduct().getProdNo())
                    .orElseThrow(RuntimeException::new);
            product.decrementStock(tp.getQuantity());
            this.productRepository.updateProduct(product);
        });
        return AddPurchaseResponseDTO.from(purchase);
    }

    @Override
    public GetPurchaseResponseDto getPurchase(int tranNo) throws RecordNotFoundException {
        Optional<Purchase> purchase = this.purchaseRepository.findById(tranNo);
        return GetPurchaseResponseDto.from(purchase.orElseThrow(() -> new RecordNotFoundException(
                "No record with tranNo=" + tranNo)));
    }

    @Override
    public ListPurchaseResponseDto getPurchaseList(ListPurchaseRequestDto requestDTO) {
        Integer page = requestDTO.getPage();
        page = page == null ? 1 : page;
        Integer pageSize = requestDTO.getPageSize();
        pageSize = pageSize == null ? defaultPageSize : pageSize;
        String searchCondition = requestDTO.getSearchCondition() == null
                                 ? SearchCondition.NO_CONDITION.getConditionCode()
                                 : requestDTO.getSearchCondition().getConditionCode();
        String searchKeyword = StringUtil.null2nullStr(requestDTO.getSearchKeyword());

        Map<String, Object> search = new HashMap<>();
        search.put("buyerId", requestDTO.getBuyerId());
        search.put("startRowNum", (page - 1) * pageSize + 1);
        search.put("endRowNum", page * pageSize);
        search.put("searchCondition", searchCondition);
        search.put("searchKeyword", searchKeyword);
        ListData<Purchase> result = this.purchaseRepository.findPurchasesByUserId(search);
        return ListPurchaseResponseDto.builder()
                .count(result.getCount())
                .purchaseList(result.getList().stream().map(GetPurchaseResponseDto::from).collect(Collectors.toList()))
                .paginationInfo(getPageInfo(result.getCount(), page, pageSize))
                .build();
    }

    private Pagination getPageInfo(int count, int currentPage, int pageSize) {
        return Pagination.of(currentPage, count, pageSize, defaultPageDisplay);
    }

    @Override
    public ListPurchaseResponseDto getSaleList(Integer page, Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? defaultPageSize : pageSize;
        ListData<Purchase> purchases = this.purchaseRepository.findAllInPageSize((page - 1) * pageSize + 1,
                                                                                 page * pageSize);
        return ListPurchaseResponseDto.builder()
                .count(purchases.getCount())
                .purchaseList(purchases.getList()
                                      .stream()
                                      .map(GetPurchaseResponseDto::from)
                                      .collect(Collectors.toList()))
                .paginationInfo(getPageInfo(purchases.getCount(), page, pageSize))
                .build();
    }

    @Override
    public Purchase updatePurchase(int tranNo, UpdatePurchaseRequestDto requestDTO) {
        Purchase purchase = new Purchase();
        purchase.setTranNo(tranNo);
        purchase.setBuyer(new User(requestDTO.getBuyerId()));
        purchase.setPaymentOption(requestDTO.getPaymentOption());
        purchase.setReceiverName(requestDTO.getReceiverName());
        purchase.setReceiverPhone(requestDTO.getReceiverPhone());
        purchase.setDivyAddr(requestDTO.getDivyAddr());
        purchase.setDivyRequest(requestDTO.getDivyRequest());
        purchase.setOrderDate(LocalDate.now());
        purchase.setDivyDate(requestDTO.getDivyDate());
        this.purchaseRepository.insertPurchase(purchase);
        return purchase;
    }

    @Override
    public TranStatusCode updateTranCode(UpdateTranCodeRequestDTO requestDTO) {
        Purchase purchase = new Purchase();
        purchase.setTranNo(requestDTO.getTranNo());
        purchase.setTranStatusCode(requestDTO.getTranStatusCode());
        this.purchaseRepository.updateTranCode(purchase);
        return this.purchaseRepository.findTranStatusCodeByTranNo(requestDTO.getTranNo());
    }

    @Override
    public TranStatusCodeResponseDto getTranStatus(int tranNo) {
        TranStatusCode tranStatusCode = this.purchaseRepository.findTranStatusCodeByTranNo(tranNo);
        return new TranStatusCodeResponseDto(tranStatusCode.getCode(), tranStatusCode.getStatus());
    }
}
