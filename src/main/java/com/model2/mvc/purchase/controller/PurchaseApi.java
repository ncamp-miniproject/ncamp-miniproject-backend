package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.purchase.controller.editor.LocalDateEditor;
import com.model2.mvc.purchase.controller.editor.PaymentOptionEditor;
import com.model2.mvc.purchase.controller.editor.TranStatusCodeEditor;
import com.model2.mvc.purchase.controller.editor.TransactionProductionEditor;
import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDto;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDto;
import com.model2.mvc.purchase.dto.request.UpdateTranCodeRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDto;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDto;
import com.model2.mvc.purchase.dto.response.TranStatusCodeResponseDto;
import com.model2.mvc.purchase.service.PurchaseService;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseApi {
    private final PurchaseService purchaseService;

    @InitBinder
    public void dateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, LocalDateEditor.getInstance());
        binder.registerCustomEditor(List.class, "tranProds", TransactionProductionEditor.getInstance());
        binder.registerCustomEditor(PaymentOption.class, "paymentOption", PaymentOptionEditor.getInstance());
        binder.registerCustomEditor(TranStatusCode.class, "tranStatusCode", TranStatusCodeEditor.getInstance());
        binder.registerCustomEditor(SearchCondition.class, SearchConditionEditor.getInstance());
    }

    @PostMapping
    public ResponseEntity<AddPurchaseResponseDTO> addPurchase(@RequestBody AddPurchaseRequestDTO requestDTO,
                                                              @SessionAttribute(value = "user",
                                                                                required = false) User loginUser) {
//        if (signIn == null || signIn.getRole() != Role.USER) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(this.purchaseService.addPurchase(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListPurchaseResponseDto> listPurchases(@ModelAttribute ListPurchaseRequestDto requestDto) {
        return new ResponseEntity<>(this.purchaseService.getPurchaseList(requestDto), HttpStatus.OK);
    }

    @GetMapping("/sale")
    public ResponseEntity<ListPurchaseResponseDto> getSales(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        ListPurchaseResponseDto saleList = this.purchaseService.getSaleList(page, pageSize);
        saleList.setMenu("manage");
        return new ResponseEntity<>(saleList, HttpStatus.OK);
    }

    @PatchMapping("/{tranNo}")
    public ResponseEntity<Void> updatePurchase(@PathVariable("tranNo") int tranNo,
                                               @ModelAttribute UpdatePurchaseRequestDto requestDto) {
        this.purchaseService.updatePurchase(tranNo, requestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{tranNo}/tran-code")
    public ResponseEntity<TranStatusCodeResponseDto> updateTranCode(@PathVariable("tranNo") int tranNo,
                                                                    @RequestParam("tranCode") String tranCode) {
        TranStatusCode tranStatusCode = this.purchaseService.updateTranCode(new UpdateTranCodeRequestDTO(tranNo,
                                                                                                         TranStatusCode.findTranCode(
                                                                                                                 tranCode)));
        return new ResponseEntity<>(new TranStatusCodeResponseDto(tranStatusCode.getCode(), tranStatusCode.getStatus()),
                                    HttpStatus.OK);
    }

    @GetMapping("/{tranNo}")
    public ResponseEntity<GetPurchaseResponseDto> getPurchase(@PathVariable("tranNo") int tranNo) {
        return new ResponseEntity<>(this.purchaseService.getPurchase(tranNo), HttpStatus.OK);
    }

    @GetMapping("/{tranNo}/tran-code")
    public ResponseEntity<TranStatusCodeResponseDto> getTranStatusCode(@PathVariable("tranNo") int tranNo) {
        return new ResponseEntity<>(this.purchaseService.getTranStatus(tranNo), HttpStatus.OK);
    }
}
