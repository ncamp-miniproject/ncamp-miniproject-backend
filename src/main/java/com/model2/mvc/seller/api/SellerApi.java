package com.model2.mvc.seller.api;

import com.model2.mvc.seller.dto.request.SellerApplicationRequestDto;
import com.model2.mvc.seller.dto.response.SellerInfoResponseDto;
import com.model2.mvc.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class SellerApi {
    private final SellerService sellerService;
    private final ServletContext servletContext;

    @PostMapping
    public ResponseEntity<Void> submitApplicationForSeller(@RequestBody SellerApplicationRequestDto request) {
        this.sellerService.submitApplication(request, this.servletContext.getRealPath("/images/uploadFiles"));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{sellerId}")
    public ResponseEntity<Void> acceptApplicationForSeller(@PathVariable("sellerId") String sellerId) {
        this.sellerService.acceptApplication(sellerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerInfoResponseDto> getSellerInfo(@PathVariable("sellerId") String sellerId) {
        return new ResponseEntity<>(this.sellerService.getSellerInfo(sellerId), HttpStatus.OK);
    }

    @DeleteMapping("/{sellerId}")
    public ResponseEntity<Void> rejectApplication(@PathVariable("sellerId") String sellerId) {
        this.sellerService.rejectApplication(sellerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
