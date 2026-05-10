package com.my_geeks.dormitory.user.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CleaningStyle {
    OFTEN("자주"),
    REGULARLY("주기적"),
    WHEN_NEEDED("필요할 때");

    private final String description;
}
