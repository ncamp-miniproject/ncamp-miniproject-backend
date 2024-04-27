package com.model2.mvc.seller.domain;

import com.model2.mvc.user.domain.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Seller {
    private User seller;
    private String profileImageFile;
    private String profile;
    private Boolean authorized;
}
