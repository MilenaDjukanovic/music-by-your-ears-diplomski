package com.miggie.musicbyyourears.repo;

import com.miggie.musicbyyourears.repo.entity.IconsDto;
import com.miggie.musicbyyourears.repo.entity.IconsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Icon Repository
 *
 * @author mdjukanovic
 */
public interface IconRepository extends JpaRepository<IconsEntity, Long> {

    /**
     * Finds Icon by its ID
     * @param id ID of the icon
     * @return optional of Icon entity
     */
    Optional<IconsEntity> findById(Long id);

    /**
     * Finds Icons by its extension
     * @param extension extension of the icon
     * @return pageable of Icons Dto
     */
    Page<IconsEntity> findByExtension(String extension, Pageable pageable);
}
