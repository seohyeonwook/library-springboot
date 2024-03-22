package com.study.library.controller;

import com.study.library.security.PrincipalUser;// 내장된거 사용하겠다
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/account")
// /principal 엔드포인트로 GET 요청이 들어왔을 때, 현재 사용자(principal) 정보를 반환하는 역할
public class AccountController {


    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();// new가 꼭없어도
        // 자료형만 선언하고  = 만 있어도 생성자는 없지만 생성은 된다
        return ResponseEntity.ok(principalUser);
    }
}


