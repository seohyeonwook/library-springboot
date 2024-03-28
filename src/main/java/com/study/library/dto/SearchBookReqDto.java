package com.study.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SearchBookReqDto {
    private int page;// 페이지 - 정해진 갯수만큼만 보고싶어서
    private int count;
    private int bookTypeId;
    private int categoryId;
    private int searchTypeId;
    private String searchText;
}

// 테이블이 존재하지 않아서 안에 이것들을 가지고 있는 컬럼이 존재하지 않음 그래서 안만듬