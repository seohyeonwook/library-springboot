package com.study.library.controller;

import com.study.library.aop.annotation.ValidAspect;
import com.study.library.dto.EditPasswordReqDto;
import com.study.library.security.PrincipalUser;// 내장된거 사용하겠다
import com.study.library.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/account")
// /principal 엔드포인트로 GET 요청이 들어왔을 때, 현재 사용자(principal) 정보를 반환하는 역할
public class AccountController { //사용자 계정 관련 API를 처리하는 컨트롤러

    @Autowired
    private AccountService accountService;

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
        // 현재 인증된 사용자(principal)의 정보를 반환합니다. 이는 SecurityContextHolder를 사용하여 현재 인증된 사용자의
        // 인증 객체(Authentication)를 가져와서 사용자 정보를 추출합니다. 반환되는 ResponseEntity는 사용자 정보를 포함하고 있습니다.
    }

    @ValidAspect
    @PutMapping("/password")
    public ResponseEntity<?> editPassword(@Valid @RequestBody EditPasswordReqDto editPasswordReqDto, BindingResult bindingResult) {
        accountService.editPassword(editPasswordReqDto);
        return ResponseEntity.ok(true);
        // 사용자의 비밀번호를 수정하는 엔드포인트입니다. 클라이언트로부터 전송된 요청의 Request Body에서 EditPasswordReqDto를
        // 유효성 검사하고(bindingResult를 통해), 유효한 경우 AccountService를 통해 비밀번호를 수정합니다.
        // ResponseEntity는 수정이 성공적으로 이루어졌음을 나타냅니다.
    }
    // 두 메서드 모두 계정 관련 기능을 처리하며, 각각 현재 사용자 정보 조회와 비밀번호 수정을 담당합니다.
}


