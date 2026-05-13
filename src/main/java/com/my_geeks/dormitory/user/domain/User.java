package com.my_geeks.dormitory.user.domain;

import com.my_geeks.dormitory.common.domain.BaseEntity;
import com.my_geeks.dormitory.common.util.snowflake.SnowflakeId;
import com.my_geeks.dormitory.user.domain.enums.Gender;
import com.my_geeks.dormitory.user.domain.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity implements Persistable<Long> {

    @Id
    @SnowflakeId
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(name = "profile_image_url", columnDefinition = "TEXT")
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(name = "student_number")
    private int studentNumber;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "department_id")
    private Long departmentId;

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
