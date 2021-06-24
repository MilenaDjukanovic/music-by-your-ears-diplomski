package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.ReviewsDto;
import com.miggie.musicbyyourears.requests.CreateReviewRequest;
import com.miggie.musicbyyourears.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("api/review")
@AllArgsConstructor
public class ReviewsController {

    /** Review Service **/
    private final ReviewService reviewService;

    @GetMapping("/{playlistId}")
    private Page<ReviewsDto> findReviewForPlaylist(@PathVariable Long playlistId) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);

        return this.reviewService.findByPlaylist(playlistId, pageable);
    }

    @PostMapping()
    private ReviewsDto create(@RequestBody CreateReviewRequest createReviewRequest) {
        return this.reviewService.create(createReviewRequest);
    }
}
