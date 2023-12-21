package com.junghyun.myownboard.Service;

import com.junghyun.myownboard.Dto.UserDto;
import com.junghyun.myownboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserMapper userMapper;

    //회원등록
    public void register(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("회원정보 누락");
        }
        userMapper.insert(userDto);
    }

    /**
     * 사용자의 아이디와 비밀번호를 받아서 회원 번호를 조회하는 메소드입니다.
     *
     * @param userId       사용자 아이디
     * @param userPassword 사용자 비밀번호
     * @return 회원 번호. 만약 일치하는 회원이 없다면 예외를 발생시킵니다.
     * @throws IllegalArgumentException 아이디나 패스워드가 제공되지 않았거나, 일치하는 회원이 없을 경우 발생합니다.
     */
    public long findUserNumber(String userId, String userPassword) {
        // 먼저 입력값으로 받은 userId와 userPassword가 null인지 확인합니다.
        // 만약 하나라도 null 이라면 "아이디 , 패스워드 누락!" 이라는 메시지로 IllegalArgumentException 예외를 발생시킵니다.
        if (userId == null || userPassword == null) {
            throw new IllegalArgumentException("아이디 , 패스워드 누락!");
        }

        // 그 다음으로 userMapper의 selectUserNumber 메소드를 호출하여 데이터베이스에서 해당 아이디와 비밀번호로 회원 번호를 조회합니다.
        // Optional.ofNullable()는 selectUserNumber 메소드가 반환한 값(회원 번호)을 Optional 객체로 감싸줍니다.
        // 만약 selectUserNumber가 null을 반환한다면 (즉, 일치하는 회원 정보가 없다면), orElseThrow() 메소드에 의해 "존재하지 않는 회원입니다."라는 메시지로 IllegalArgumentException 예외가 발생합니다.
        return Optional.ofNullable(userMapper.selectUserNumber(userId, userPassword))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}