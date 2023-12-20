
package com.go.together.Controller;

import com.go.together.Dto.ProductDto;
import com.go.together.Service.FileService;
import com.go.together.Service.ProductService;
import com.go.together.Vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor



public class ProductController {
    private final ProductService productService;
    private final FileService fileService;

    @PostMapping("/registerProduct")
    public int registerProduct( ProductVo productVo, @RequestPart("productFile") List<MultipartFile> files) {
        int result = productService.registerProduct(productVo);
        System.out.println(productVo + "상품 입력 값 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        if (files != null) {
            try {
                fileService.registerAndSaveFiles(files, productVo.getProductNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    @PostMapping("/fileList")
    public List<ProductVo> fileList(@RequestBody ProductVo productVo) {
        Integer userNumber = productVo.getUserNumber();
        if (userNumber == null) {
            throw new IllegalArgumentException("유저아이디가 없습니다");
        }
        System.out.println("유저번호 !!!!!!!" + userNumber);
        List<ProductVo> productVoList = productService.findAllProduct(productVo);
        return productVoList;
    }


  @PostMapping("/divideCode")
    public List<ProductVo> divideCode(@RequestBody ProductVo productVo) {
        Long  categoryMajorCode= productVo.getCategoryMajorCode();
        Long categoryMinorCode=productVo.getCategoryMinorCode();


      System.out.println(categoryMajorCode + " MajorCode가 몇인가 ????????!!");
      System.out.println(categoryMinorCode + "   MinorCode 가 몇인가 !@!@!@!@!@");



        List<ProductVo> mnList = productService.sortCode(productVo);
        return mnList;
    }


    @PostMapping("/productView")
    public List<ProductVo> productView(@RequestBody ProductVo productVo){
        Long productNumber = productVo.getProductNumber();


        List<ProductVo> result =productService.findOneProduct(productNumber);
        System.out.println(productNumber + "상품 번호확인 !!!");


        System.out.println(result);
        return result;

    }



    @GetMapping("/productDelete")
    public int productDelete(@RequestParam Long productNumber) {


        int result = productService.remove(productNumber);
        System.out.println(result + "1이 나오면 성공");
        return result;
    }

    @PostMapping("/productDelete")
    public Map<Long, Integer> productDelete(@RequestBody Map<String, List<Long>> ArrayProductNumber) {
        List<Long> productNumbers = ArrayProductNumber.get("productNumber");
        Map<Long, Integer> results = new HashMap<>();

        for(Long productNumber : productNumbers){
            //    for(int i = 0; i < productNumbers.size(); i++){
//        Long productNumber = productNumbers.get(i); 위와 같은거임.,
            int result = productService.remove(productNumber);
            results.put(productNumber , result);
        }

        System.out.println(results + " 체크한 productNumber(상품번호)가 삭제되면 상품번호와 1이 출력 ");
        return results;
    }

   /*
    public List<Integer> : 이 부분은 메소드의 반환 타입을 나타냅니다. 여기서는 이 메소드가 List<Integer> 타입의 값을 반환함을 의미합니다.
    List<Integer>는 정수값들을 저장하는 리스트입니다.
    Map<String, List<Long>> ArrayProductNumber : 이 부분은 메소드의 매개변수를 나타냅니다.
    Map은 키-값 쌍을 저장하는 자료구조이며, 여기서는 키가 문자열(String)이고 값이 Long 타입의 리스트(List<Long>)인 맵을 입력으로 받습니다.
    ArrayProductNumber는 이 맵의 변수명입니다.
    List<Integer> results = new ArrayList<>(); : 이 부분은 Integer 타입의 값을 저장하는 리스트 results를 선언하고,
    new ArrayList<>()를 통해 이 리스트를 초기화하는 코드입니다. 이 results 리스트는 각 상품 번호에 대한 삭제 결과를 저장하기 위해 선언되었습니다.
    int result = productService.remove(productNumber); : 이 부분은 productService.remove(productNumber) 메소드를 호출하고
    그 반환값을 int 타입의 result 변수에 저장하는 코드입니다.
    productService.remove(productNumber)는 입력된 상품 번호에 해당하는 상품을 삭제하는 메소드라고 가정하겠습니다.
    이 메소드의 반환값은 삭제 작업의 성공 여부 또는 결과를 나타내는 정수값일 수 있습니다.
return results; : 이 부분은 results 리스트를 반환하는 코드입니다. 이 메소드가 종료될 때, results 리스트는 이 메소드의 반환값이 됩니다.
 results 리스트에는 각 상품 번호에 대한 삭제 결과가 순서대로 저장되어 있습니다. 따라서 이 return results; 코드를 통해,
 이 메소드를 호출한 곳으로 각 상품 번호의 삭제 결과를 반환하게 됩니다.



    Map<String, List<Long>>에서 String은 Map의 키(key)의 데이터 타입을 나타냅니다.
    이 경우, 클라이언트에서 보내는 JSON 데이터가 { "productNumbers": [/상품 번호 배열 ] } 형태이므로, 키는 "productNumbers"라는 문자열입니다.

    List<Long>은 Map의 값(value)의 데이터 타입을 나타냅니다. 여기서는 상품 번호의 배열을 나타내므로 Long 타입의 리스트입니다.

    따라서 Map<String, List<Long>>은 "문자열을 키로 하고, Long 타입의 리스트를 값으로 하는 Map"을 의미합니다.
    이 Map에서 "productNumbers"라는 키로 상품 번호의 리스트를 가져올 수 있습니다.

    그리고 Java에서는 정수형 데이터를 표현할 때 int와 long 두 가지 타입을 주로 사용합니다. `
*/


    @PostMapping("/productUpdate")
    public int productUpdate(ProductDto productDto, @RequestPart("productFile") List<MultipartFile> files) {
        Long productNumber=productDto.getProductNumber();
        if (productNumber == null) {
            throw new IllegalArgumentException("유저번호가 x");
        }
        try {
            productService.modify(productDto , files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }




    
    
    


}








