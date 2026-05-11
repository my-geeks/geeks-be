package com.my_geeks.dormitory.university.domain;

import com.my_geeks.dormitory.university.domain.enums.MajorCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "departments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id
    private Long id;

    @Column(name = "university_id", nullable = false)
    private Long universityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "major_category", nullable = false, length = 20)
    private MajorCategory majorCategory;

    @Column(nullable = false, length = 100)
    private String name;
}
