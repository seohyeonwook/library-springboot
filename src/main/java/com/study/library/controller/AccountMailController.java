package com.study.library.controller;


import com.study.library.service.AccountMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mail")
public class AccountMailController {

    @Autowired
    private AccountMailService accountMailService;

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> send() {

        return ResponseEntity.ok(accountMailService.sendAuthMail());
    }

    @GetMapping("/authenticate")
    public String resultPage(@RequestParam String authToken) {
        return "result_page"; //서버사이드로  서버사이드랜더링 하려면 타임리프라이브러리 있어야함
                //파일 경로로 보겠다
    }
}
