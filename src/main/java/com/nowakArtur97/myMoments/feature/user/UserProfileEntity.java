package com.nowakArtur97.myMoments.feature.user;

import com.nowakArtur97.myMoments.common.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_profile", schema = "my_moments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
class UserProfileEntity extends AbstractEntity {

    @Column
    private String about;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String interests;

    @Column
    private String languages;

    @Column
    private String location;

    @Column(name = "image")
    @Type(type = "org.hibernate.type.BinaryType")
    @Lob
    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserEntity user;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof UserProfileEntity)) return false;

        UserProfileEntity that = (UserProfileEntity) o;

        return Objects.equals(getId(), that.getId()) &&
                getGender() == that.getGender() &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGender(), getUser());
    }
}