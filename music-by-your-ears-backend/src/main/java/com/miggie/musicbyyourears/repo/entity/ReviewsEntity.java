package com.miggie.musicbyyourears.repo.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlaylistEntity playlist;


    /** User which made the comment **/
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    /** Comment **/
    @Column(nullable = false)
    @Getter @Setter
    private String comment;
}
