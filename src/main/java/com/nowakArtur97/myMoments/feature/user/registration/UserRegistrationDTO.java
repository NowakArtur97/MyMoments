package com.nowakArtur97.myMoments.feature.user.registration;

import com.nowakArtur97.myMoments.feature.user.resource.UserDTO;
import com.nowakArtur97.myMoments.feature.user.shared.BasicUserValidationConstraints;
import com.nowakArtur97.myMoments.feature.user.validation.PasswordsMatch;
import com.nowakArtur97.myMoments.feature.user.validation.UniqueEmail;
import com.nowakArtur97.myMoments.feature.user.validation.UniqueUserName;
import com.nowakArtur97.myMoments.feature.user.validation.ValidPasswords;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
@PasswordsMatch(message = "{user.password.notMatch}", groups = BasicUserValidationConstraints.class)
@ValidPasswords(groups = BasicUserValidationConstraints.class)
@ApiModel(description = "Model responsible for User validation during registration")
public class UserRegistrationDTO extends UserDTO {

    @UniqueUserName(message = "{user.name.unique}", groups = BasicUserValidationConstraints.class)
    @NotBlank(message = "{user.name.notBlank}")
    @Size(min = 4, max = 40, message = "{user.name.size}")
    @ApiModelProperty(notes = "The user's name", required = true)
    protected String username;

    @UniqueEmail(message = "{user.email.unique}", groups = BasicUserValidationConstraints.class)
    @Email(message = "{user.email.wrongFormat}")
    @NotBlank(message = "{user.email.notBlank}")
    @ApiModelProperty(notes = "The user's email", required = true)
    protected String email;

    @NotBlank(message = "{user.password.notBlank}")
    @ApiModelProperty(notes = "The user's password", required = true)
    protected String password;

    @NotBlank(message = "{user.matchingPassword.notBlank}")
    @ApiModelProperty(notes = "The user's password for confirmation", required = true)
    protected String matchingPassword;

    @Valid
    @ApiModelProperty(notes = "The user's profile")
    protected UserProfileDTO profile;

    public UserRegistrationDTO(String username, String email, String password, String matchingPassword, UserProfileDTO profile) {
        super(username, email, password, matchingPassword, profile);
        this.username = username;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.profile = profile;
    }
}