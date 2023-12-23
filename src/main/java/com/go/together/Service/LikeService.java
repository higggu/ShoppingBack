package com.go.together.Service;


import com.go.together.Dto.LikeDto;
import com.go.together.Mapper.LikeMapper;
import com.go.together.Vo.LikeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeMapper likeMapper;
    
    public int registerLike(LikeDto likeDto){
        if(likeDto == null ){
            throw new IllegalArgumentException("정보가없습니다");
        }
        return likeMapper.insertLike(likeDto);
    }
    
    
    public int deleteLike(LikeVo likeVo){
        if(likeVo == null ){
        throw new IllegalArgumentException("유저번호와 , 상품 번호가 없음");
    }
        return likeMapper.unlike(likeVo);
}
    
    public List<LikeVo> AllLike(LikeVo likeVo){
        if(likeVo == null){
            throw new IllegalArgumentException("유저번호가없습니다");
        }
        return likeMapper.selectLikeStatus(likeVo);
    }
    
    
}
