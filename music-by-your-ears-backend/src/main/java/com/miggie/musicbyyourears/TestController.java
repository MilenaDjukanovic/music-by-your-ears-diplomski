package com.miggie.musicbyyourears;

import com.miggie.musicbyyourears.repo.IconRepository;
import com.miggie.musicbyyourears.repo.SoundRepository;
import com.miggie.musicbyyourears.repo.UserRepository;
import com.miggie.musicbyyourears.repo.entity.*;
import com.miggie.musicbyyourears.requests.CreatePlaylistRequest;
import com.miggie.musicbyyourears.requests.CreateReviewRequest;
import com.miggie.musicbyyourears.requests.CreateSoundRequest;
import com.miggie.musicbyyourears.service.PlaylistService;
import com.miggie.musicbyyourears.service.ReviewService;
import com.miggie.musicbyyourears.service.mappers.IconCreateMapper;
import com.miggie.musicbyyourears.service.mappers.IconViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    private final ReviewService reviewService;

    @Autowired
    private final IconViewMapper iconViewMapper;
    private final IconCreateMapper iconCreateMapper;
    @Autowired
    private final UserRepository userRepository;

    private final SoundRepository soundRepository;

    private final PlaylistService playlistService;
    @GetMapping()
    public String test() {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setPassword("111");
//        userEntity.setAbout("juu");
//        userEntity.setUsername("miggie");
//        userEntity.setImage("this".getBytes());
//        userEntity.setFirstName("milena");
//        userEntity.setLastName("djuka");
//        this.userRepository.save(userEntity);
//        IconsEntity iconsEntity = new IconsEntity();
//        iconsEntity.setImage("dsa".getBytes());
//        iconsEntity.setImageFile("jdsjdja");
//        iconsEntity.setName("prva");
//        this.iconRepository.save(iconsEntity);
//
//        CreatePlaylistRequest createPlaylistRequest = new CreatePlaylistRequest();
//        createPlaylistRequest.setArtist("neko novi");
//        createPlaylistRequest.setAudio("juu".getBytes(StandardCharsets.UTF_8));
//        createPlaylistRequest.setCoverFile("jdka");
//        createPlaylistRequest.setCoverImage("fsf".getBytes(StandardCharsets.UTF_8));
//        Set<IconsDto> icons = new HashSet<>();
//        IconsEntity iconsDto = this.iconRepository.findById(1L).orElse(null);
//        icons.add(this.iconViewMapper.toDto(iconsDto));
//        createPlaylistRequest.setIcons(icons);
//        createPlaylistRequest.setUserId(1L);
//        this.playlistService.create(createPlaylistRequest);
//        CreateSoundRequest createSoundRequest = new CreateSoundRequest();
//        createSoundRequest.setAudio("jda".getBytes(StandardCharsets.UTF_8));
//        IconsEntity iconsDto = this.iconRepository.findById(1L).orElse(null);
//        createSoundRequest.setIconRequest(iconCreateMapper.toDto(iconsDto));
//        createSoundRequest.setName("kodoa");
//        createSoundRequest.setSoundPublic(false);
//        createSoundRequest.setUserId(1L);
//        SoundEntity soundEntity = new SoundEntity();
//        soundEntity.setAudio("jda".getBytes(StandardCharsets.UTF_8));
//        soundEntity.setSoundPublic(true);
//        soundEntity.setName("dd");
//        soundEntity.setIcon(iconsDto);
//        UserEntity userEntity = this.userRepository.findById(1L).orElse(null);
//        soundEntity.setUser(userEntity);
//        this.soundRepository.save(soundEntity);

        CreateReviewRequest createReviewRequest = new CreateReviewRequest();
        createReviewRequest.setComment("djhs");
        createReviewRequest.setPlaylistId(1L);
        createReviewRequest.setUserId(1L);
        ReviewsDto reviewsDto = this.reviewService.create(createReviewRequest);

        return "222";
    }
}
