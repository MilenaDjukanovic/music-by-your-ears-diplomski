package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.SoundDto;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreateSoundRequest;
import com.miggie.musicbyyourears.service.SoundService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController @RequestMapping("api/sounds")
@AllArgsConstructor
public class SoundController {

    /** Sound Service **/
    private final SoundService soundService;

    @GetMapping()
    private Page<SoundDto> findByUserAndPublic() {
        Long userId = 1L;
        return this.soundService.findByUserAndSoundPublic(userId, true);
    }

    @PostMapping
    private SoundDto create(@RequestParam("imageFile") MultipartFile imageFile,
                            @RequestParam("audioFile") MultipartFile audioFile,
                            @RequestParam("soundPublic") boolean soundPublic,
                            @RequestParam("nameToShow") String nameToShow,
                            @RequestParam("extension") String extension) throws IOException {
        CreateIconRequest createIconRequest = new CreateIconRequest();
        createIconRequest.setName(imageFile.getOriginalFilename());
        createIconRequest.setImageFile(imageFile.getContentType());
        createIconRequest.setImage(imageFile.getBytes());
        createIconRequest.setExtension(extension);

        CreateSoundRequest createSoundRequest = new CreateSoundRequest();
        createSoundRequest.setName(audioFile.getOriginalFilename());
        createSoundRequest.setAudioFile(audioFile.getContentType());
        createSoundRequest.setAudio(audioFile.getBytes());
        createSoundRequest.setNameToShow(nameToShow);

        createSoundRequest.setSoundPublic(soundPublic);
        return this.soundService.create(createSoundRequest, createIconRequest);
    }
}
