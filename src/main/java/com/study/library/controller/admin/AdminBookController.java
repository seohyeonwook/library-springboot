package com.study.library.controller.admin;

import com.study.library.aop.annotation.ParamsPrintAspect;
import com.study.library.aop.annotation.ValidAspect;
import com.study.library.dto.RegisterBookReqDto;
import com.study.library.dto.SearchBookReqDto;
import com.study.library.dto.UpdateBookReqDto;
import com.study.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminBookController {

    @Autowired
    private BookService bookService;

    @ValidAspect
    @PostMapping("/book") // 도서추가- 어드민 권한 안에서
    public ResponseEntity<?> saveBook(
            @Valid @RequestBody RegisterBookReqDto registerBookReqDto,
            BindingResult bindingResult) {

        bookService.saveBook(registerBookReqDto);

        return ResponseEntity.created(null).body(true);
    }

//    @ParamsPrintAspect
    @GetMapping("/books") // 도서검색
    public ResponseEntity<?> searchBooks(SearchBookReqDto searchBookReqDto) {
        return ResponseEntity.ok(bookService.searchBooks(searchBookReqDto));
    }

    @GetMapping("/books/count") //페이지네이션
    public ResponseEntity<?> getCount(SearchBookReqDto searchBookReqDto) {
        return ResponseEntity.ok(bookService.getBookCount(searchBookReqDto));
    }

    @DeleteMapping("/book/{bookId}")// 한권이 bookId라는 뜻
    public ResponseEntity<?> deleteBook(@PathVariable int bookId) {// 한개만 지울거다 단건
        return ResponseEntity.ok(null);
    }

    @ParamsPrintAspect
    @DeleteMapping("/books")
    public ResponseEntity<?> deleteBooks(@RequestBody List<Integer> bookIds) {//List 배열로 받을거다 = 여러개 삭제
        bookService.deleteBooks(bookIds);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/book/{bookId}")//삭제 @PathVariable int bookId는 별의미없다 ?? 어차피 디티오 안에 있어서
    public ResponseEntity<?> updateBook(@PathVariable int bookId, @RequestBody UpdateBookReqDto updateBookReqDto) {
        bookService.updateBook(updateBookReqDto);
        return ResponseEntity.ok(true);
    }

}
