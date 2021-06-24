package com.miggie.musicbyyourears.service;

import com.miggie.musicbyyourears.repo.entity.UserDto;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.requests.CreateUserRequest;

/**
 * Service for User related operations
 *
 * @author mdjukanovic
 */
public interface UserService {

    /**
     * Creates user from createUserRequest
     * @param createUserRequest request for creation containing user detai=
     * @return User object for viewing if successful
     */
    UserDto createUser(CreateUserRequest createUserRequest);

    /**
     * Updates the passed userEntity
     * @param userEntity userEntity
     * @return Updated UserEntity
     */
    UserEntity save(UserEntity userEntity);

    /**
     * Finds the User for the passed id
     * @param id id of the user to find
     * @return UserEntity
     */
    UserEntity findById(Long id);

}