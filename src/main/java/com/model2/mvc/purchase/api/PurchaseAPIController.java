package com.model2.mvc.purchase.api;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.purchase.service.PurchaseService;
import com.model2.mvc.purchase.util.LocalDateEditor;
import com.model2.mvc.purchase.util.PaymentOptionEditor;
import com.model2.mvc.purchase.util.TranStatusCodeEditor;
import com.model2.mvc.purchase.util.TransactionProductionEditor;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseAPIController {
    private final PurchaseService purchaseService;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @InitBinder
    public void dateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, LocalDateEditor.getInstance());
        binder.registerCustomEditor(List.class, "tranProds", TransactionProductionEditor.getInstance());
        binder.registerCustomEditor(PaymentOption.class, "paymentOption", PaymentOptionEditor.getInstance());
        binder.registerCustomEditor(TranStatusCode.class, "tranStatusCode", TranStatusCodeEditor.getInstance());
        binder.registerCustomEditor(SearchCondition.class, SearchConditionEditor.getInstance());
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AddPurchaseResponseDTO> addPurchase(@RequestBody AddPurchaseRequestDTO requestDTO,
                                                              @SessionAttribute(value = "user",
                                                                                required = false) User loginUser) {
        if (loginUser == null || loginUser.getRole() != Role.USER) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(this.purchaseService.addPurchase(requestDTO), HttpStatus.CREATED);
    }
}
