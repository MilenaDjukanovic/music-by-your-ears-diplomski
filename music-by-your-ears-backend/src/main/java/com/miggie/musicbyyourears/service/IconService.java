package com.miggie.musicbyyourears.service;

import com.miggie.musicbyyourears.repo.entity.IconsDto;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for icons related operations
 *
 * @author mdjukanovic
 */
public interface IconService {

    /**
     * Creates icons from the createIconsRequest
     * @param createIconRequest request for icon creation containing icon details
     * @return Icon object for viewing if successful
     */
    IconsDto create(CreateIconRequest createIconRequest);

    /**
     * Finds all icons
     * @param pageable pageable
     * @return pageable of icons
     */
    Page<IconsDto> findAll(Pageable pageable);

    /**
     * Finds icon by id
     * @param id id of the icon
     * @return IconDto if successfully found
     */
    IconsDto findById(Long id);

    /**
     * Finds all icons for a certain extension
     * @param extension extension of the icon
     * @return pageable of icons
     */
    Page<IconsDto> findByExtension(String extension, Pageable pageable);
}
