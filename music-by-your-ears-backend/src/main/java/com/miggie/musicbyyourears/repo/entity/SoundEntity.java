package com.miggie.musicbyyourears.repo.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sounds")
public class SoundEntity {

    /** Sound ID **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Sound name **/
    @Column(nullable = false)
    @Getter @Setter
    private String name;

    /** Audio **/
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] audio;

    /** Icon for the sound **/
    @ManyToOne
    @JoinColumn(name = "icon_id", nullable = false)
    @Getter @Setter
    private IconsEntity icon;

    /** Is the sound public **/
    @Column(name = "public")
    @Getter @Setter
    private boolean soundPublic = false;

    /** Created by user **/
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private UserEntity user;
}
