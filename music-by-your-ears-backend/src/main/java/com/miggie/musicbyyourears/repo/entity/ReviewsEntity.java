package com.miggie.musicbyyourears.repo.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "reviews")
public class ReviewsEntity {

    /** Review ID **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Playlist that was reviewed **/
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "playlist_id", nullable = false)
    private PlaylistEntity playlist;


    /** User which made the commment **/
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    /** Comment **/
    @Column(nullable = false)
    @Getter @Setter
    private String comment;
}
