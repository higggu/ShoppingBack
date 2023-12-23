package com.go.together.Controller;

import com.go.together.Dto.LikeDto;
import com.go.together.Service.LikeService;
import com.go.together.Vo.LikeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class LikeController {
    private final LikeService likeService;


    @PostMapping("/like")
    public int like(@RequestBody LikeDto likeDto) {
        Integer userNumber = likeDto.getUserNumber();
        Long productNumber = likeDto.getProductNumber();


        int result = likeService.registerLike(likeDto);

        return result;
    }


    @PostMapping("disLike")
    public int disLike(@RequestBody LikeVo likeVo) {
        Integer userNumber = likeVo.getUserNumber();
        Long productNumber = likeVo.getProductNumber();

        int result = likeService.deleteLike(likeVo);

        return result;
    }

    @PostMapping("likeList")
    public List<LikeVo> likeList(@RequestBody LikeVo likeVo) {
        Integer userNumber = likeVo.getUserNumber();

        List<LikeVo> LikeList =likeService.AllLike(likeVo);


        return LikeList;



    }

}
