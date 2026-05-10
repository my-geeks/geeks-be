package com.my_geeks.dormitory.user.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ActivityTime {
    MORNING("아침형"),
    EVENING("저녁형"),
    NIGHT("밤형");

    private final String description;
}
