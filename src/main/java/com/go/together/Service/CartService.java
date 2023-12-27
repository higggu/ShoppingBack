package com.go.together.Service;

import com.go.together.Mapper.CartMapper;
import com.go.together.Mapper.FileMapper;
import com.go.together.Mapper.ProductMapper;
import com.go.together.Vo.CartVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;


    public int registerCart(CartVo cartVo) {
        if (cartVo == null) {
            throw new IllegalArgumentException("등록한 cart정보 x");
        }

        // List<String> -> String 변환
        String selectedColor = String.join(",", cartVo.getSelectedColor());
        String selectedSize = String.join(",", cartVo.getSelectedSize());

        cartVo.setSelectedColor(selectedColor);
        cartVo.setSelectedSize(selectedSize);

        int res = cartMapper.insertCart(cartVo);

        return res;
    }

    public List<CartVo> userCartList(Integer userNumber){
        if(userNumber== null){
            throw new IllegalArgumentException("사용자 번호가없어요!");
        }
        return cartMapper.userCartList(userNumber);
    }


   public List<CartVo> pickCartAll(Long cartNumber){
        if(cartNumber== null){
            throw new IllegalArgumentException("사용자 번호가없어요!");
        }
        return cartMapper.selectOrderCart(cartNumber);
    }

//    유저의 모든 가격
    public CartVo cartAllPrice(Integer userNumber){
        if(userNumber== null){
            throw new IllegalArgumentException("사용자 번호가없어요!");
        }
        CartVo res=cartMapper.cartUserNumberTotalPrice(userNumber);

        return res;
    }



    public int upCount(Long cartNumber) {
        return cartMapper.addCart(cartNumber);
    }



    public int updateCount(CartVo cartVo) {
        return cartMapper.changeCartCount(cartVo);
    }





}
