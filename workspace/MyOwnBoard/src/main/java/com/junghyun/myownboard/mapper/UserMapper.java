package com.junghyun.myownboard.mapper;

import com.junghyun.myownboard.Dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public void insert(UserDto userDto);

    public Long selectUserNumber(@Param("userId")String userId , @Param("userPassword")String userPassword);


}