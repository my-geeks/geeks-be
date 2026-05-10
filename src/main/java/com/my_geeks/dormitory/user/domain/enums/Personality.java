package com.my_geeks.dormitory.user.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Personality {
    INTROVERT("혼자 조용히"),
    SOCIAL("어울리기 좋아함"),
    MIXED("상황따라");

    private final String description;
}
