package com.go.together.Service;

import com.go.together.Dto.UserDto;
import com.go.together.Mapper.UserMapper;
import com.go.together.Util.MailUtil;
import com.go.together.Util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    // 구글 이메일
    @Value("${google.id}")
    private String myEmail;
    // 구글 비번
    @Value("${google.pw}")
    private String myPw;



    //회원 등록
    public int register(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("회원정보누락!");
        }
//        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));

        String userPassword = Util.pwSha256(userDto.getUserPassword());
        userDto.setUserPassword(userPassword);



        return userMapper.insert(userDto);
    }

    /**
     * @return
     * @throws IllegalArgumentException 존재하지 않는 회원 id,pw로 조회하는 경우
     */

    //회원 번호 조회하기 (아이디.패스워드)
    @Transactional(readOnly = true)
    public UserDto findUserNumber(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("아이디 패스워드 누락");
        }

        // 사용자가 입력한 비밀번호를 암호화
        String userPassword = Util.pwSha256(userDto.getUserPassword());
        userDto.setUserPassword(userPassword);

        return Optional.ofNullable(userMapper.selectById(userDto))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 회원입니다.");
                });
    }



    public String searchUserId(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("이름 ,핸드폰번호 누락");
        }
        return Optional.ofNullable(userMapper.findUserId(userDto))
                .orElseThrow(() -> new IllegalArgumentException("없는 이름 "));
    }

    public int searchUserPassword(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("아이디 , 이름 ,핸드폰번호 누락");

        }
        return Optional.ofNullable(userMapper.findUserPassword(userDto))
                .orElseThrow(() -> new IllegalArgumentException("비밀번호를 변경하기위해 일치하는 회원정보가 없습니다."));
    }





    public int modify(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("수정할 회원정보 누락");
        }

        String userPassword = Util.pwSha256(userDto.getUserPassword());
        userDto.setUserPassword(userPassword);

        return Optional.ofNullable(userMapper.updatePw(userDto))
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회원 아이디가 없습니다"));
    }



//아이디 중복확인
    public int idCheck(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("userId 없음");
        }
        return Optional.ofNullable(userMapper.checkUserId(userDto))
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회원이름이 없습니다"));
    }


//    유저 모든 정보 리스트로 가져오기
public UserDto getUserList(UserDto userDto){
    if (userDto ==null){
        throw new IllegalArgumentException("유저 넘버 없음");
    }
    return Optional.ofNullable(userMapper.userListAll(userDto))
            .orElseThrow(() -> new IllegalArgumentException("회원번호가 없습니다"));
}


    public int checkEmail(UserDto userDto) {
        UserDto existingUser = userMapper.userListAll(userDto);
        if (existingUser == null) {
            throw new IllegalArgumentException("해당하는 회원 정보가 없습니다.");
        }

        if (!existingUser.getUserEmail().equals(userDto.getUserEmail())) {
            throw new IllegalArgumentException("입력한 이메일과 회원의 이메일 일치 X");
        }

        userDto.setUserEmail(existingUser.getUserEmail());
        String randomCode = Util.generateRandomString();
        userDto.setVerificationCode(randomCode);
        userMapper.saveVerificationCode(userDto);

        try {
            MailUtil.Send(userDto.getUserEmail(), randomCode, myEmail, myPw);
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 로직 추가
        }

        return 100;
    }

    public int verifyCode(UserDto userDto) {
        UserDto existingUser = userMapper.userListAll(userDto);
        if (!existingUser.getVerificationCode().equals(userDto.getVerificationCode())) {
            throw new IllegalArgumentException("코드 입력한게 서로달라요");
        }

        return 100;
    }

}







