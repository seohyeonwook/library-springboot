package com.study.library.controller;

import com.study.library.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/book/option")
public class BookRegisterOptionController { //책 등록 옵션을 처리하는 컨트롤러

    @Autowired
    private OptionsService optionsService;

    @GetMapping("/types")
    public ResponseEntity<?> getBookType() {
        return ResponseEntity.ok(optionsService.getAllBookTypes());
        // 모든 도서 유형(book type) 옵션을 가져옵니다.
        // OptionsService를 통해 모든 도서 유형을 조회하고 ResponseEntity에 담아 반환합니다.
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategorise() {
        return ResponseEntity.ok(optionsService.getAllCategories());
        // 모든 카테고리(categories) 옵션을 가져옵니다. OptionsService를 통해
        // 모든 카테고리를 조회하고 ResponseEntity에 담아 반환합니다.
    }
    // 각 메서드는 해당하는 옵션(도서 유형 또는 카테고리)을 조회하여 클라이언트에게 반환하는 역할을 합니다.
}
