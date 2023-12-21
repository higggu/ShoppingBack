package com.junghyun.myownboard.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserDto {
    private  Long userNumber;
    private   String userId;
    private String userPassword;
    private String userName;
}
