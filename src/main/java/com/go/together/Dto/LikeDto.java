package com.go.together.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@Component
public class LikeDto {
    private long likeNumber;
    private long productNumber;
    private int userNumber;
    private int likeStatus;
}
