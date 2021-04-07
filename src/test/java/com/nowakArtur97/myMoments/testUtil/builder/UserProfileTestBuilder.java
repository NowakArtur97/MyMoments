package com.nowakArtur97.myMoments.testUtil.builder;

import com.nowakArtur97.myMoments.feature.user.registration.UserProfileDTO;
import com.nowakArtur97.myMoments.feature.user.shared.Gender;
import com.nowakArtur97.myMoments.feature.user.shared.UserEntity;
import com.nowakArtur97.myMoments.feature.user.shared.UserProfile;
import com.nowakArtur97.myMoments.feature.user.shared.UserProfileEntity;
import com.nowakArtur97.myMoments.testUtil.enums.ObjectType;
import org.springframework.mock.web.MockMultipartFile;

public class UserProfileTestBuilder {

    public static UserProfileDTO DEFAULT_USER_PROFILE_DTO = new UserProfileDTO("about", "unspecified",
            "interests", "languages", "location");

    private String about = "about";

    private Gender gender = Gender.UNSPECIFIED;

    private String genderString = "UNSPECIFIED";

    private String interests = "interests";

    private String languages = "languages";

    private String location = "location";

    private byte[] image = "image".getBytes();

    private MockMultipartFile imageFile = new MockMultipartFile("data", "filename.txt",
            "text/plain", image);

    private UserEntity user;

    public UserProfileTestBuilder withAbout(String about) {

        this.about = about;

        return this;
    }

    public UserProfileTestBuilder withGender(Gender gender) {

        this.gender = gender;

        return this;
    }

    public UserProfileTestBuilder withGender(String gender) {

        this.genderString = gender;

        return this;
    }

    public UserProfileTestBuilder withInterests(String interests) {

        this.interests = interests;

        return this;
    }

    public UserProfileTestBuilder withLanguages(String languages) {

        this.languages = languages;

        return this;
    }

    public UserProfileTestBuilder withLocation(String location) {

        this.location = location;

        return this;
    }

    public UserProfileTestBuilder withImage(byte[] image) {

        this.image = image;

        return this;
    }

    public UserProfileTestBuilder withImageFile(MockMultipartFile imageFile) {

        this.imageFile = imageFile;

        return this;
    }

    public UserProfileTestBuilder withUserEntity(UserEntity user) {

        this.user = user;

        return this;
    }

    public UserProfile build(ObjectType type) {

        UserProfile userProfile;

        switch (type) {

            case CREATE_DTO:

                userProfile = new UserProfileDTO(about, genderString, interests, languages, location);

                break;

            case ENTITY:

                userProfile = new UserProfileEntity(about, gender, interests, languages, location, image, user);

                break;

            default:
                throw new RuntimeException("The specified type does not exist");
        }

        resetProperties();

        return userProfile;
    }

    private void resetProperties() {

        about = "about";

        gender = Gender.UNSPECIFIED;

        genderString = "UNSPECIFIED";

        interests = "interests";

        languages = "languages";

        location = "location";

        image = "image".getBytes();

        imageFile = new MockMultipartFile("data", "filename.txt", "text/plain", image);
    }
}
