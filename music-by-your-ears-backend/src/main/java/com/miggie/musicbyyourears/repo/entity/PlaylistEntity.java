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

    /** Playlist cover **/
    @Lob
    @Column(name = "cover_image", nullable = false, columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] coverImage;

    /** Playlist cover file **/
    @Column(name = "cover_file", nullable = false)
    @Getter @Setter
    private String coverFile;

    /** Playlist audio **/
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] audio;

    /** Icons of used sounds **/
    @Column()
    @ManyToMany(fetch = FetchType.EAGER)
    @Getter @Setter
    private Set<IconsEntity> icons;

    /** Added by user **/
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private UserEntity user;
}
