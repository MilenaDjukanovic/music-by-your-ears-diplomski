package com.miggie.musicbyyourears.repo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "playlists")
public class PlaylistEntity {

    /** Playlist ID **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Artist **/
    @Column(nullable = false)
    @Getter @Setter
    private String artist;

    /** Name to show **/
    @Column(name = "name_to_show", nullable = false)
    @Getter @Setter
    private String nameToShow;

    /** Name **/
    @Column(nullable = false)
    @Getter @Setter
    private String name;

    /** Playlist cover **/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "icon_id", nullable = false)
    @Getter @Setter
    private IconsEntity coverImage;

    /** Playlist audio **/
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] audio;

    /** Playlist cover file **/
    @Column(name = "audio_file", nullable = false)
    @Getter @Setter
    private String audioFile;

    /** Added by user **/
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private UserEntity user;
}
