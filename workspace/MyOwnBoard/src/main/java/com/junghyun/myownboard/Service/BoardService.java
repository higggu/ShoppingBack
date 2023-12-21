package com.junghyun.myownboard.Service;

import com.junghyun.myownboard.Dto.BoardDto;
import com.junghyun.myownboard.Vo.BoardVo;
import com.junghyun.myownboard.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;


    public void register(BoardDto boardDto) {
        if (boardDto == null) {
            throw new IllegalArgumentException("게시물 정보가 없습니다.(null)");
        }
        boardMapper.insert(boardDto);
    }


    public List<BoardVo> findAll() {

        return boardMapper.selectAll();
    }



    public BoardVo findOne(Long boardNumber) {

        return boardMapper.selectOne(boardNumber);
    }


}