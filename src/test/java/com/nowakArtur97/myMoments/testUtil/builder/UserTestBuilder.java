package com.nowakArtur97.myMoments.testUtil.builder;

import com.nowakArtur97.myMoments.feature.user.authentication.AuthenticationRequest;
import com.nowakArtur97.myMoments.feature.user.registration.UserDTO;
import com.nowakArtur97.myMoments.feature.user.registration.UserProfileDTO;
import com.nowakArtur97.myMoments.feature.user.shared.*;
import com.nowakArtur97.myMoments.testUtil.enums.ObjectType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserTestBuilder {

    public static UserEntity DEFAULT_USER_ENTITY_WITHOUT_PROFILE = new UserEntity("username", "user@email.com",
            "SecretPassword123!@", new UserProfileEntity(),
            new HashSet<>(Collections.singletonList(RoleTestBuilder.DEFAULT_ROLE_ENTITY)));

    private String username = "user123";

    private String password = "SecretPassword123!@";

    private String matchingPassword = "SecretPassword123!@";

    private String email = "userEmail123@email.com";

    private UserProfile profile;

    private Set<RoleEntity> roles = new HashSet<>(Collections.singletonList(RoleTestBuilder.DEFAULT_ROLE_ENTITY));

    public UserTestBuilder withUsername(String username) {

        this.username = username;

        return this;
    }

    public UserTestBuilder withPassword(String password) {

        this.password = password;

        return this;
    }

    public UserTestBuilder withMatchingPassword(String matchingPassword) {

        this.matchingPassword = matchingPassword;

        return this;
    }

    public UserTestBuilder withEmail(String email) {

        this.email = email;

        return this;
    }

    public UserTestBuilder withRoles(Set<RoleEntity> roles) {

        this.roles = roles;

        return this;
    }

    public UserTestBuilder withProfile(UserProfile profile) {

        this.profile = profile;

        return this;
    }

    public User build(ObjectType type) {

        User user;

        switch (type) {

            case DTO:

                user = new UserDTO(username, email, password, matchingPassword, (UserProfileDTO) profile);

                break;

            case ENTITY:

                user = new UserEntity(username, email, password, (UserProfileEntity) profile, roles);

                break;

            case REQUEST:

                user = new AuthenticationRequest(username, password, email);

                break;

            default:
                throw new RuntimeException("The specified type does not exist");
        }

        resetProperties();

        return user;
    }

    private void resetProperties() {

        username = "user123";

        password = "SecretPassword123!@";

        matchingPassword = "SecretPassword123!@";

        email = "userEmail123@email.com";

        profile = null;

        roles = new HashSet<>();
    }
}
