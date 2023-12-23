package com.go.together.Mapper;

import com.go.together.Dto.LikeDto;
import com.go.together.Vo.LikeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {

    public int insertLike(LikeDto likeDto);

    public int unlike(LikeVo likeVo);

    public List<LikeVo> selectLikeStatus(LikeVo likeVo);

}
