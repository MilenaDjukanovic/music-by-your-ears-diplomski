package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.IconsDto;
import com.miggie.musicbyyourears.repo.entity.PlaylistDto;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.service.IconService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Public API Endpoint for Playlists
 * @author mdjukanovic
 */
@RestController
@RequestMapping("api/icons")
@AllArgsConstructor
public class IconController {

    /** Icon Service **/
    private final IconService iconService;

    @GetMapping("all")
    public Page<IconsDto> findAllNoLimit() {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        return this.iconService.findAll(pageable);
    }

    @PostMapping()
    public IconsDto create(@RequestBody @Valid CreateIconRequest createIconRequest) {
        return this.iconService.create(createIconRequest);
    }

}
