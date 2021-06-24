package com.miggie.musicbyyourears.service;

import com.miggie.musicbyyourears.repo.entity.UserEntity;

/**
 * Service for handling Authorization related operations
 *
 * @author mdjukanovic
 */
public interface AuthorizationService {

    /**
     * Retrieves the currently authenticated user (user that is performing requests)
     * @return UserEntity for authenticated user
     */
    UserEntity getAuthenticatedUser();
}
