package com.go.together.Controller;


import com.go.together.Mapper.CartMapper;
import com.go.together.Service.CartService;
import com.go.together.Service.FileService;
import com.go.together.Vo.CartVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor


public class CartController {
    private final CartService cartService;
    private final FileService fileService;
    private final CartMapper cartMapper;



@PostMapping("/addCart")
    public int addCart( @RequestBody CartVo cartVo){
    int result =cartService.registerCart(cartVo);
    return result;
}

@PostMapping("/userCart")
    public List<CartVo> userCart(@RequestBody CartVo cartvo){
    Integer userNumber=cartvo.getUserNumber();

    List<CartVo> userCartInfo = cartService.userCartList(userNumber);
    return userCartInfo;

}

    @PostMapping("/getAllPrice")
    public CartVo getAllPrice(@RequestBody CartVo cartvo) {
        Integer userNumber=cartvo.getUserNumber();
    CartVo totalPrice =cartService.cartAllPrice(userNumber);
        return totalPrice;
    }


    @PostMapping("/plusCount")
    public int plusCount(@RequestBody CartVo cartVo) {
    Long cartNumber=cartVo.getCartNumber() ;
    int result=cartService.upCount(cartNumber);
    return result;
    }
//수량변경
    @PostMapping("/changeCount")
    public int changeCount(@RequestBody CartVo cartVo){
        int result=cartService.updateCount(cartVo);
        return result;
    }



//   장바구니 사이즈와 색상변경
    @PostMapping("/cartChangeOption")
    public int cartChangeOption(@RequestBody CartVo cartVo){
    int result = cartMapper.changeSizeColor(cartVo);
    return result;
}


//장바구니 카트 삭제
// 장바구니 카트 삭제
@PostMapping("deleteCart")
public int deleteCart(@RequestBody Map<String, List<Long>> ArrayCartNumber) {
    List<Long> cartNumbers = ArrayCartNumber.get("cartNumber");

    int result = 0; // 초기화

    for (Long cartNumber : cartNumbers) {
        result = cartMapper.deleteCart(cartNumber);
        // 만약 deleteCart 메서드가 실패할 경우, 이후 반복문은 계속 진행됩니다.
    }

    return result; // 마지막에 최종적으로 deleteCart 메서드의 결과 반환
}

}