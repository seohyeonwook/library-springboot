package com.study.library.service;

import com.study.library.dto.*;
import com.study.library.entity.Book;
import com.study.library.repository.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveBook(RegisterBookReqDto registerBookReqDto) {
        bookMapper.saveBook(registerBookReqDto.toEntity());
    }

    public List<SearchBookRespDto> searchBooks(SearchBookReqDto searchBookReqDto) { //List< 자료형 >  리턴을 디티오로 해주겠다
        int startIndex = (searchBookReqDto.getPage() - 1) * searchBookReqDto.getCount(); // startIndex Dto안에 페이지 받아서

        List<Book> books = bookMapper.findBooks( //xml쪽에서 20개 가져오는 구간
                startIndex,
                searchBookReqDto.getCount(),
                searchBookReqDto.getBookTypeId(),
                searchBookReqDto.getCategoryId(),
                searchBookReqDto.getSearchTypeId(),
                searchBookReqDto.getSearchText() // 결과적으로 응답으로 화면에 보여주는 곳
        );
//                "bookId": 4,
//                "bookName": "불멸의 화가 100인의 명화 05 인간 1",
//                "authorName": "유니온아트",
//                "publisherName": "봄이아트북스",
//                "isbn": "D200320910",
//                "bookTypeId": 1,
//                "bookTypeName": "전자책",
//                "categoryId": 1,
//                "categoryName": "예술/대중문화",
//                "coverImgUrl": " http://ebook.seocholib.or.kr/upload/20553/content/ebook/480D200320910/L480D200320910.jpg"
        // 안들어가있는애들은 null로 잡혀있음

        return books.stream().map(Book::toSearchBookRespDto).collect(Collectors.toList());
    }

    public SearchBookCountRespDto getBookCount(SearchBookReqDto searchBookReqDto) {
        int bookCount = bookMapper.getBookCount(
                searchBookReqDto.getBookTypeId(),
                searchBookReqDto.getCategoryId(),
                searchBookReqDto.getSearchTypeId(),
                searchBookReqDto.getSearchText()
        );
        int maxPageNumber = (int) Math.ceil(((double) bookCount) / searchBookReqDto.getCount());

        return SearchBookCountRespDto.builder()
                .totalCount(bookCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBooks(List<Integer> bookIds) {
        bookMapper.deleteBooksByBookIds(bookIds);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBook(UpdateBookReqDto updateBookReqDto) {
        bookMapper.updateBookByBookId(updateBookReqDto.toEntity());
    }

}

// 문법에서 List set Map = 프레임워크