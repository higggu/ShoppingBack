package com.junghyun.myownboard.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@Component

public class BoardDto {
    private Long boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private  Long userNumber;

}
