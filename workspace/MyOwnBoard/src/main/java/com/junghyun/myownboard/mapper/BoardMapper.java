package com.junghyun.myownboard.mapper;

import com.junghyun.myownboard.Dto.BoardDto;
import com.junghyun.myownboard.Vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public void insert(BoardDto boardDto);

    public List<BoardVo> selectAll();

    public BoardVo selectOne(Long boardNumber);
}
