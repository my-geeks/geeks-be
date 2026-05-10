package com.my_geeks.dormitory.user.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Smoking {
    SMOKER("흡연자"),
    NON_SMOKER("비흡연자");

    private final String description;
}
