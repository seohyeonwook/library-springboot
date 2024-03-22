package com.study.library.dto;

import lombok.Data;

@Data
public class OAut2MergeReqDto {
    private String username;
    private String password;
    private String oauth2Name;
    private String providerName;
}
