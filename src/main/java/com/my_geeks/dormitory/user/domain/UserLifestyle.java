package com.my_geeks.dormitory.user.domain;

import com.my_geeks.dormitory.common.domain.BaseEntity;
import com.my_geeks.dormitory.user.domain.enums.ActivityTime;
import com.my_geeks.dormitory.user.domain.enums.CleaningStyle;
import com.my_geeks.dormitory.user.domain.enums.Personality;
import com.my_geeks.dormitory.user.domain.enums.SleepHabit;
import com.my_geeks.dormitory.user.domain.enums.SleepSensitivity;
import com.my_geeks.dormitory.user.domain.enums.Smoking;
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
@Table(name = "user_lifestyles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLifestyle extends BaseEntity implements Persistable<Long> {

    @Id
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Smoking smoking;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_habit", length = 10)
    private SleepHabit sleepHabit;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_sensitivity", length = 10)
    private SleepSensitivity sleepSensitivity;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_time", length = 20)
    private ActivityTime activityTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "cleaning_style", length = 20)
    private CleaningStyle cleaningStyle;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Personality personality;

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
