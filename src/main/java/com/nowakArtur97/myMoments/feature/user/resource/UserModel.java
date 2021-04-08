package com.nowakArtur97.myMoments.feature.user.resource;

import com.nowakArtur97.myMoments.feature.user.authentication.AuthenticationResponse;
import com.nowakArtur97.myMoments.feature.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Details about the User")
public class UserModel implements User {

    @ApiModelProperty(notes = "API key")
    private AuthenticationResponse authenticationResponse;

    @ApiModelProperty(notes = "The unique id of the User")
    private Long id;

    @ApiModelProperty(notes = "The user's name")
    private String username;

    @ApiModelProperty(notes = "The user's email")
    private String email;

    @ApiModelProperty(notes = "The user's profile")
    private UserProfileModel profile;

    @ApiModelProperty(notes = "The user's roles")
    private List<RoleModel> roles;
}
