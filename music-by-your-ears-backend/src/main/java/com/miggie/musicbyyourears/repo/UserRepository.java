package com.miggie.musicbyyourears.repo;

import com.miggie.musicbyyourears.repo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User Repository
 *
 * @author mdjukanovic
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds user by username
     * @param username username of the user
     * @return optional of user entity
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * Finds user by its id
     * @param id id of the user to find
     * @return optional of User entity
     */
    Optional<UserEntity> findById(Long id);
}
