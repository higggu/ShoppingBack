package com.go.together.Vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
@NoArgsConstructor
public class LikeVo {
    private Long likeNumber;
    private int likeStatus;
    private int userNumber;
    private String userName;
    private Long productNumber;
    private String productName;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
}
