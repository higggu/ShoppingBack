package com.junghyun.myownboard.Controller;

import com.junghyun.myownboard.Dto.BoardDto;
import com.junghyun.myownboard.Service.BoardService;
import com.junghyun.myownboard.Vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/list")
    public String showBoardList( Model model){
        List<BoardVo> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "board/board";
    }




    @GetMapping("/write")
    public String boardWrite(HttpServletRequest req){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        return userNumber == null ? "user/login" : "board/boardWrite";
    }



    @PostMapping("/write")
    public RedirectView boardWrite(BoardDto boardDto , HttpServletRequest req , RedirectAttributes redirectAttributes ){
        Long userNumber =(Long)req.getSession().getAttribute("userNumber");
                boardDto.setUserNumber(userNumber);
        boardService.register(boardDto);

//        리다이렉트를 사용할 때 데이터를 전송하는 2가지 방법

//        쿼리스트링으로 데이터를 전송한다.(url에 쿼리스트링이 생긴다.)
//        권장하는 사용 방법 : 컨트롤러에서 데이터를 사용하는 경우
//        redirectAttributes.addAttribute("boardNumber", boardDto.getBoardNumber());

//        플래쉬를 사용하여 데이터를 전송한다.
//        권장하는 사용 방법 : 화면에서 데이터를 사용하는 경우
        redirectAttributes.addFlashAttribute("boardNumber", boardDto.getBoardNumber());
        return new RedirectView("/board/list");

    }


    @GetMapping("/view")
    public String boardView(Long boardNumber, Model model){
        BoardVo boardVo = boardService.findOne(boardNumber);
        model.addAttribute("boardList", boardVo);
        return "board/boardView";
    }


}
