package com.my_geeks.dormitory.university.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MajorCategory {
    ENGINEERING("공학계열"),
    SOCIAL("사회계열"),
    HUMANITIES("인문계열"),
    NATURAL("자연계열"),
    EDUCATION("교육계열"),
    ARTS_PE("예체능계열"),
    MEDICAL_PHARMACY("의약계열"),
    UNDECLARED("무전공");

    private final String description;
}
