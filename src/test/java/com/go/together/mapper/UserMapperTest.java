package com.go.together.mapper;

import com.go.together.Dto.UserDto;
import com.go.together.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
@Configuration

class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    private UserDto userDto;


    @BeforeEach
    void setUp(){
        userDto=new UserDto();
        userDto.setUserId("hi");
        userDto.setUserName("정쿠키");
        userDto.setUserPassword("aaa");
        userDto.setUserEmail("qqq@naver.com");
        userDto.setUserPhoneNumber("01022222222");
        userDto.setUserAddress1("서울");
        userDto.setUserAddress2("용산구");
        userDto.setUserAddress3("리첸시아");
        userDto.setUserAddressNumber("11122");
    }


    @Test
    void insert() {
        userMapper.insert(userDto);
    }

    @Test
    void selectById() {
        // 먼저 사용자를 추가
        userMapper.insert(userDto);

        // 추가한 사용자를 ID로 검색
        UserDto selectedUser = userMapper.selectById(userDto);

        // 검색된 사용자의 정보가 올바른지 검증
        assertThat(selectedUser).isNotNull();
        assertThat(selectedUser.getUserId()).isEqualTo(userDto.getUserId());
    }

    @Test
    void findUserId() {
        userMapper.insert(userDto);

        String foundId = userMapper.findUserId(userDto);

        assertThat(foundId).isEqualTo(userDto.getUserId());

    }

    @Test
    void findUserPassword() {
        userMapper.insert(userDto);

        int SelectNumber =userMapper.findUserPassword(userDto);

        assertThat(SelectNumber).isEqualTo(userDto.getUserNumber());

    }

    @Test
    void updatePw() {

      userMapper.insert(userDto);

     String Upw="213";
     userDto.setUserPassword(Upw);
     userMapper.updatePw(userDto);

     UserDto CheckPw=userMapper.selectById(userDto);
     assertThat(CheckPw.getUserPassword()).isEqualTo(CheckPw);

    }

    @Test
    void checkUserId() {
        userMapper.insert(userDto);

        int Count =userMapper.checkUserId(userDto);

        assertThat(Count).isEqualTo(1);
    }

    @Test
    void userListAll() {

        userMapper.insert(userDto);

        UserDto NewDto=userMapper.userListAll(userDto);

        assertThat(NewDto).isEqualTo(userDto);




    }
}