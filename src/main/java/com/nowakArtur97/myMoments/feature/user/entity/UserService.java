package com.nowakArtur97.myMoments.feature.user.entity;

import com.nowakArtur97.myMoments.common.exception.NotAuthorizedException;
import com.nowakArtur97.myMoments.feature.user.resource.UserRegistrationDTO;
import com.nowakArtur97.myMoments.feature.user.resource.UserUpdateDTO;
import com.nowakArtur97.myMoments.feature.user.validation.UserValidationGroupSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.RoleNotFoundException;
import javax.persistence.Basic;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated({Basic.class, UserValidationGroupSequence.class})
public class UserService {

    @Value("${my-moments.default-user-role:USER_ROLE}")
    private String defaultUserRole;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleService roleService;

    public boolean isUsernameAlreadyInUse(String username) {

        return userRepository.existsUserByUsername(username);
    }

    public boolean isEmailAlreadyInUse(String email) {

        return userRepository.existsUserByEmail(email);
    }

    public Optional<UserEntity> findById(Long id) {

        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public UserEntity registerUser(@Valid UserRegistrationDTO userRegistrationDTO, MultipartFile image)
            throws RoleNotFoundException, IOException {

        RoleEntity roleEntity = roleService.findByName(defaultUserRole)
                .orElseThrow(() -> new RoleNotFoundException("Role with name: '" + defaultUserRole + "' not found."));

        UserEntity newUserEntity = userMapper.convertDTOToEntity(userRegistrationDTO, image, roleEntity);

        return userRepository.save(newUserEntity);
    }

    public UserEntity updateUser(Long id, @Valid UserUpdateDTO userUpdateDTO, MultipartFile image)
            throws IOException {

        UserEntity userEntity = findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id: '" + id + "' not found."));

        if (!isUserChangingOwnData(userEntity.getUsername())) {
            throw new NotAuthorizedException("User can only update his own account.");
        }

        userUpdateDTO.setId(id);

        userMapper.convertDTOToEntity(userEntity, userUpdateDTO, image);

        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> deleteUser(Long id) {

        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {

            UserEntity userEntity = userOptional.get();

            if (isUserChangingOwnData(userEntity.getUsername())) {
                userRepository.delete(userEntity);
            } else {
                throw new NotAuthorizedException("User can only delete his own account.");
            }
        }

        return userOptional;
    }

    public boolean isUserChangingOwnData(String username) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String usernameInContext = auth != null ? auth.getName() : "";

        return username.equals(usernameInContext);
    }
}
