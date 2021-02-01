package com.nowakArtur97.myMoments.feature.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
class UserProfileDTO {

    @Size(message = "{userProfile.about.size}{max}", max = 250)
    private String about;

    @NotNull(message = "{userProfile.gender.size}{max}")
    private Gender gender;

    @Size(message = "{userProfile.interests.size}{max}", max = 250)
    private String interests;

    @Size(message = "{userProfile.languages.size}{max}", max = 250)
    private String languages;

    @Size(message = "{userProfile.location.size}{max}", max = 50)
    private String location;

    private MultipartFile image;
}