package com.study.library.repository;

import com.study.library.entity.OAuth2;
import com.study.library.entity.RoleRegister;
import com.study.library.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
// 데이터베이스와의 상호작용을 담당하는 리포지토리 클래스를 포함합니다. 데이터베이스에 접근하여 데이터를 조작합니다.
public interface UserMapper {//0314-4
    public User findUserByUsername(String username);
//    public int saveUser(User user);
//    public int saveRole(int userId);
    public int saveUser(User user);
    public RoleRegister findRoleRegisterByUserIdAndRoleId(@Param("userId") int userId, @Param("roleId") int roleId);
    public int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);
    public User findUserByOAuth2name(String oAuth2name);
    public int saveOAuth2(OAuth2 oAuth2);
    public int modifyPassword(User user);
}
