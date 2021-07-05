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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

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

    @GetMapping()
    public Page<IconsDto> findByExtension(@RequestParam String extension) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        return this.iconService.findByExtension(extension, pageable);
    }

    @PostMapping("/upload")
    public IconsDto create(@RequestParam("imageFile") MultipartFile file,
                           @RequestParam("testFile") MultipartFile test) throws IOException {
        CreateIconRequest createIconRequest = new CreateIconRequest();
        createIconRequest.setName(file.getOriginalFilename());
        createIconRequest.setImageFile(file.getContentType());
        createIconRequest.setImage(file.getBytes());
        return this.iconService.create(createIconRequest);
    }

}
