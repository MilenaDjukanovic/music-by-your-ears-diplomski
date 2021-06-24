package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Default implementation for Authorization Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
public class DefaultAuthorizationService implements AuthorizationService {

    @Override
    public UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return (UserEntity) authentication.getPrincipal();
    }
}
