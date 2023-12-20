package com.go.together.Mapper;

import com.go.together.Dto.ProductDto;
import com.go.together.Vo.CartVo;
import com.go.together.Vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

//    게시글 등록
    public int insertProduct(ProductVo productVo);


//    사이즈 등록
    public int insertSize(Map<String , Object> paraMap);

    public int insertColor(Map<String , Object> colorParamMap);


//    게시글 VIEW
    public List<ProductVo> selectProduct(Long productNumber);

//    게시글 리스트
    public List<ProductVo> selectAllProduct(ProductVo productVo);


// MAJOR, MINOR 구분
    public List<ProductVo> classifyMajorMinor(ProductVo productVo);


    public int delete(Long productNumber);

    public int sizeDelete(Long productNumber);

    public int colorDelete(Long productNumber);

    public void updateProduct(ProductDto productDto);




////    장바구니
//
////    장바구니 등록
//    public int insertCart(CartVo cartVo);
//
////    장바구니 조회
//    public List<CartVo> UserCart(CartVo cartVo);
//
////    장바구니 수량 증가
//    public int addCart(CartVo cartVo);
//
////    장바구니 수량 변경
//    public int updateCartPlus(CartVo cartVo);
//
////    장바구니 수량 감소
//    public int minusCart(CartVo cartVo);
//
////    장바구니 상품 삭제
//    public int deleteCart(Long cartNumber);





}
