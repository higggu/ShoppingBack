package com.junghyun.myownboard.Vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardVo {
    private Long boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private  Long userNumber;
    private String userId;

}
