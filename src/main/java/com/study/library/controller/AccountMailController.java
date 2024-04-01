package com.study.library.controller;


import com.study.library.service.AccountMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mail")
public class AccountMailController { //계정 이메일 관련 기능을 처리하는 컨트롤러입니다.

    @Autowired
    private AccountMailService accountMailService;

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> send() {

        return ResponseEntity.ok(accountMailService.sendAuthMail());
    }
    // 클라이언트로부터 요청이 들어오면 AccountMailService를 사용하여 이메일을 전송합니다.
    // 이메일 전송이 성공하면 ResponseEntity로 성공 메시지를 반환합니다.

    @GetMapping("/authenticate")
    public String resultPage(@RequestParam String authToken) {
        return "result_page"; //서버사이드로  서버사이드랜더링 하려면 타임리프라이브러리 있어야함
                //파일 경로로 보겠다

        // 클라이언트로부터 인증 토큰(authToken)을 받아서, "result_page"라는 문자열을 반환합니다.
        // 이는 서버 측에서 서버 사이드 렌더링을 수행하고 결과 페이지를 표시하기 위한 것으로 보입니다.
        // 하지만 현재 코드에서는 템플릿 엔진이나 뷰 리졸버가 없으므로 "result_page"를 반환할 때 정적 파일로 처리될 가능성이 높습니다.
    }
    //이렇게 요약할 수 있습니다. 첫 번째 메서드는 이메일을 보내는 기능을 처리하고, 두 번째 메서드는 인증 토큰을 받아 결과 페이지를 반환하는 기능을 처리합니다.
}
