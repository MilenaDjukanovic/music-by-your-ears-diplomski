package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.UserRepository;
import com.miggie.musicbyyourears.repo.entity.UserDto;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.requests.CreateUserRequest;
import com.miggie.musicbyyourears.service.UserService;
import com.miggie.musicbyyourears.service.mappers.UserCreateMapper;
import com.miggie.musicbyyourears.service.mappers.UserViewMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Objects;

/**
 * {@inheritDoc}
 *
 * Default implementation of the User Service
 * @author mdjukanovic
 */
@Service
@Transactional
@AllArgsConstructor
public class DefaultUserService implements UserService {

    /** User Repository **/
    private final UserRepository userRepository;

    /** Password Encoder **/
    private final PasswordEncoder passwordEncoder;

    /** User Create Mapper **/
    private final UserCreateMapper userCreateMapper;

    /** User View Mapper **/
    private final UserViewMapper userViewMapper;

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        Objects.requireNonNull(createUserRequest);

        if(this.userRepository.findByUsername(createUserRequest.getUsername()).isPresent()) {
            throw new ValidationException("Username exists");
        }

        if(!StringUtils.equals(createUserRequest.getPassword(), createUserRequest.getRePassword())) {
            throw new ValidationException("Passwords do not match");
        }

        UserEntity userEntity = this.userCreateMapper.toEntity(createUserRequest);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));

        userEntity = this.userRepository.save(userEntity);

        return this.userViewMapper.toDto(userEntity);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        Objects.requireNonNull(userEntity);
        Objects.requireNonNull(userEntity.getId());

        this.userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Could not find user with that id!"));

        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {
        Objects.requireNonNull(id);

        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find user with that id!"));
    }
}
