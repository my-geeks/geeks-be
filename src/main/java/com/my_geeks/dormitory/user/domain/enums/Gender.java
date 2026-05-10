package com.my_geeks.dormitory.user.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String description;
}
