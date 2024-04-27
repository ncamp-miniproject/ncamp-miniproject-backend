package com.model2.mvc.seller.service;

import com.model2.mvc.common.dto.Base64ImageDto;
import com.model2.mvc.common.file.FileAccess;
import com.model2.mvc.seller.domain.Seller;
import com.model2.mvc.seller.dto.request.SellerApplicationRequestDto;
import com.model2.mvc.seller.dto.response.SellerInfoResponseDto;
import com.model2.mvc.seller.repository.SellerRepository;
import com.model2.mvc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final FileAccess fileAccess;

    @Override
    public void submitApplication(SellerApplicationRequestDto requestDto, String filePath) {
        Base64ImageDto profileImage = requestDto.getProfileImage();
        String filename = this.fileAccess.storeFile(profileImage.getBase64Data(), profileImage.getFileExtension(), filePath);
        executeUpdate(() -> this.sellerRepository.insert(new Seller(new User(requestDto.getSellerId()), filename, requestDto.getProfile(), false)));
    }

    @Override
    public void acceptApplication(String sellerId) {
        executeUpdate(() -> this.sellerRepository.updateAuthorized(sellerId, true));
    }

    @Override
    public SellerInfoResponseDto getSellerInfo(String sellerId) {
        Seller seller = this.sellerRepository.findById(sellerId);
        User sellerInfo = seller.getSeller();
        return SellerInfoResponseDto.builder()
                .sellerId(sellerInfo.getUserId())
                .nameOfUser(sellerInfo.getNameOfUser())
                .ssn(sellerInfo.getSsn())
                .phone(sellerInfo.getPhone())
                .addr(sellerInfo.getAddr())
                .email(sellerInfo.getEmail())
                .regDate(sellerInfo.getRegDate())
                .profileImageFile(seller.getProfileImageFile())
                .profile(seller.getProfile())
                .build();
    }

    @Override
    public void rejectApplication(String sellerId) {
        executeUpdate(() -> this.sellerRepository.deleteById(sellerId));
    }

    private void executeUpdate(Supplier<Boolean> process) {
        boolean success = process.get();
        if (!success) {
            throw new RuntimeException(); // TODO
        }
    }
}
