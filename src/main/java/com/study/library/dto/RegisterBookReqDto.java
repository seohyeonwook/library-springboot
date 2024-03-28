package com.study.library.dto;

import com.study.library.entity.Book;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterBookReqDto {
    private String isbn;

    @Min(value = 1, message = "숫자만 입력 가능합니다.") //min 자료형이 인트형일때만 쓴다
    private int bookTypeId;

//    @Pattern(regexp = "^[0-9]*$", message = "숫자만 입력 가능합니다.") //pattern 대신 min사용
    @Min(value = 1, message = "숫자만 입력 가능합니다.")
    private int categoryId;

    @NotBlank(message = "도서명은 빈 값일 수 없습니다.")  // 공백 ,null 사용 안됨
//    @NotNull null 이 아니어야하고
//    @Null 무조건 null 이어야하고
//    @Empty 공백만 체크 null (x)
    private String bookName;

    @NotBlank(message = "저자명은 빈 값일 수 없습니다.")
    private String authorName;

    @NotBlank(message = "출판사는 빈 값일 수 없습니다.")
    private String publisherName;

    @NotBlank(message = "커버 이미지는 빈 값일 수 없습니다.")
    private String coverImgUrl;

    public Book toEntity() {
        return Book.builder()
                .bookName(bookName)
                .authorName(authorName)
                .publisherName(publisherName)
                .isbn(isbn)
                .bookTypeId(bookTypeId)
                .categoryId(categoryId)
                .coverImgUrl(coverImgUrl)
                .build();
    }
}
