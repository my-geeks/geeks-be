package com.my_geeks.dormitory.user.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SleepSensitivity {
    LIGHT("귀 밝음"),
    DEEP("귀 어두움");

    private final String description;
}
