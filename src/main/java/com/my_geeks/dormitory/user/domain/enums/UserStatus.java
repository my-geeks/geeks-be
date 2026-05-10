package com.my_geeks.dormitory.user.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserStatus {
    ACTIVE("활성화"),
    INACTIVE("비활성화"),
    WITHDRAWN("탈퇴");

    private final String description;
}
