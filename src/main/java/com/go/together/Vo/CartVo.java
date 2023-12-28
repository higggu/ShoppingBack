package com.go.together.Vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Slf4j
@NoArgsConstructor
public class CartVo {

    private long cartNumber;
    private int userNumber;
    private String userId;
    private long productNumber;
    private int cartCount;
    private Double discountRate; // 추가: 할인율
    private Double discountPrice; // 추가: 할인가
    private Double totalDiscountPrice; // 추가: 할인가
    private List<String> productSizes;
    private List<String> productColors;
    private String productName;
    private String selectedColor;
    private String selectedSize;
    private int productQuantity;
    private Double productPrice;
    private Double totalPrice;
    private Double totalCount;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;

}
