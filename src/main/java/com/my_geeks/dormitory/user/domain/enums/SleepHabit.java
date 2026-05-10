package com.my_geeks.dormitory.user.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SleepHabit {
    YES("있음"),
    NO("없음");

    private final String description;
}
