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
// 패키지 중요도 = 컨트롤러- 레파지토리 - 서비스  역할만 알면 된다
// 엔티티 - 데이터베이스 테이블 과 연관있다 클래스 이름도  = 테이블 이름
// 엔티티 안에 붙어있는 이유 - 데이터 베이스에서 정규화 떄문에 나눠진 데이터를 합쳐서 데이터를 봐야하기때문에 (조인) xml에서 결정됨
// 조인을하면 테이블이 합쳐진다 이결과를 다담을수있어야한다 조인되면 다른테이블이 들어오기때문에  어소세이션 은 조인했을떄 붙을수있는 녀석들
// 근데 리절트맵으로 붙임 리절트맵보면 각각의 테이블 형태로 생겼다 - 데이터베이스 이해 필요 위에서 생성하고 최종적으로 북이라는 객체 생성
/** 조인은 1대 1이 아닐수도있다 하나의 테이블에 여러개의 테이블 정보가 붙을수가있다 결과값이 있다 어소세이션은 일대일에만 쓰고 컬렉션은 일대 다에 사용한다 무조건 리스트 타입
 * 예를들어서 한학생의 1,.2,3 성적을 을 붙인다고생각해보자 그럼 1번학생의 1학년일떄 성적 1번의 2학년 1번의3학년 3개의 객체를 리스트에 담아서 하나의 학생에 연결시킨다
 * 그래서 엔티티 보면 리스트는 컬렉션 하나의 객체는 어소세이션 결과적으로 쿼리문을 알아야한다 어려우면 그림그려보자
 *
 * 야물 파일-  스프링부트 세팅떄문에 사용 구분지어놓는이유는 배포때문에 서버실행시 꼭 필요한 세팅 공통적인건 커먼에 작성
 */

