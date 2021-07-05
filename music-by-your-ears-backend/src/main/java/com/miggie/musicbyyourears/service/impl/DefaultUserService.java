package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.IconRepository;
import com.miggie.musicbyyourears.repo.UserRepository;
import com.miggie.musicbyyourears.repo.entity.IconsDto;
import com.miggie.musicbyyourears.repo.entity.IconsEntity;
import com.miggie.musicbyyourears.repo.entity.UserDto;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreateUserRequest;
import com.miggie.musicbyyourears.service.UserService;
import com.miggie.musicbyyourears.service.mappers.IconCreateMapper;
import com.miggie.musicbyyourears.service.mappers.IconViewMapper;
import com.miggie.musicbyyourears.service.mappers.UserCreateMapper;
import com.miggie.musicbyyourears.service.mappers.UserViewMapper;
import com.miggie.musicbyyourears.service.util.FileCompressorUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.swing.text.IconView;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Objects;

/**
 * {@inheritDoc}
 * <p>
 * Default implementation of the User Service
 *
 * @author mdjukanovic
 */
@Service
@Transactional
@AllArgsConstructor
public class DefaultUserService implements UserService {

    /**
     * User Repository
     **/
    private final UserRepository userRepository;

    /**
     * Password Encoder
     **/
    private final PasswordEncoder passwordEncoder;

    /**
     * User Create Mapper
     **/
    private final UserCreateMapper userCreateMapper;

    /**
     * User View Mapper
     **/
    private final UserViewMapper userViewMapper;

    /**
     * Icon Repository
     **/
    private final IconRepository iconRepository;

    /**
     * Icon Create Mapper
     **/
    private final IconCreateMapper iconCreateMapper;

    /**
     * Icon View Mapper
     **/
    private final IconViewMapper iconViewMapper;

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
    public UserDto updateUser(CreateUserRequest createUserRequest, CreateIconRequest createIconRequest) {
        Objects.requireNonNull(createUserRequest);
        Objects.requireNonNull(createIconRequest);

        if (this.userRepository.findByUsername(createUserRequest.getUsername()).isPresent()) {
            throw new ValidationException("Username exists");
        }

        if (!StringUtils.equals(createUserRequest.getPassword(), createUserRequest.getRePassword())) {
            throw new ValidationException("Passwords do not match");
        }

        createIconRequest.setImage(FileCompressorUtil.compressBytes(createIconRequest.getImage()));
        IconsEntity iconsEntity = this.iconCreateMapper.toEntity(createIconRequest);
        iconsEntity = this.iconRepository.save(iconsEntity);

        IconsDto iconsDto = this.iconViewMapper.toDto(iconsEntity);
        createUserRequest.setProfileImage(iconsDto);

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
    public UserDto findById(Long id) {
        Objects.requireNonNull(id);

        UserEntity userEntity = this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find user with that id!"));
        userEntity.getProfileImage().setImage(FileCompressorUtil.decompressBytes(userEntity.getProfileImage().getImage()));

        return this.userViewMapper.toDto(userEntity);
    }
}
