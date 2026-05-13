package com.my_geeks.dormitory.user.domain;

import com.my_geeks.dormitory.common.domain.BaseEntity;
import com.my_geeks.dormitory.common.util.snowflake.SnowflakeId;
import com.my_geeks.dormitory.user.domain.enums.OAuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Getter
@Entity
@Table(
        name = "user_social_accounts",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_user_social_accounts_provider_provider_user_id",
                columnNames = {"provider", "provider_user_id"}
        )
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSocialAccount extends BaseEntity implements Persistable<Long> {

    @Id
    @SnowflakeId
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OAuthProvider provider;

    @Column(name = "provider_user_id", nullable = false, length = 255)
    private String providerUserId;

    @Column(name = "access_token", columnDefinition = "TEXT")
    private String accessToken;

    @Column(name = "refresh_token", columnDefinition = "TEXT")
    private String refreshToken;

    @Column(name = "token_expires_at")
    private LocalDateTime tokenExpiresAt;

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
