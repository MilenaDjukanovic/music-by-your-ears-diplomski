package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.SoundDto;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreateSoundRequest;
import com.miggie.musicbyyourears.service.AuthorizationService;
import com.miggie.musicbyyourears.service.SoundMixingService;
import com.miggie.musicbyyourears.service.SoundService;
import com.miggie.musicbyyourears.service.mappers.SoundViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController @RequestMapping("api/sounds")
@AllArgsConstructor
public class SoundController {

    /** Sound Service **/
    private final SoundService soundService;

    /** Authorization Service **/
    private final AuthorizationService authorizationService;

    private final SoundViewMapper soundViewMapper;

    private final SoundMixingService soundMixingService;

    @GetMapping()
    private Page<SoundDto> findByUserAndPublic() {
        return this.soundService.findByUserAndSoundPublic(this.authorizationService.getAuthenticatedUser().getId(), true);
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

    @PostMapping("mix-sounds")
    public SoundDto mixSounds(@RequestBody List<Long> soundId) {
        List<SoundDto> sounds = this.soundService.getSoundsForId(soundId);
        return this.soundMixingService.mixSounds(sounds);
    }
}
