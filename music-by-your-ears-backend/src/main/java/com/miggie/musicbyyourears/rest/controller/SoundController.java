package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.SoundDto;
import com.miggie.musicbyyourears.requests.CreateSoundRequest;
import com.miggie.musicbyyourears.service.SoundService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("api/sounds")
@AllArgsConstructor
public class SoundController {

    /** Sound Service **/
    private final SoundService soundService;

    @GetMapping()
    private Page<SoundDto> findByUserAndPublic(@RequestBody Long userId, boolean soundPublic) {
        return this.soundService.findByUserAndSoundPublic(userId, soundPublic);
    }

    @PostMapping
    private SoundDto create(@RequestBody CreateSoundRequest createSoundRequest) {
        return this.soundService.create(createSoundRequest);
    }
}
