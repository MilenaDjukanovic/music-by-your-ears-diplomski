package com.miggie.musicbyyourears.repo.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "icons")
@Entity
public class IconsEntity {

    /** Icon ID **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Icon name **/
    @Column(nullable = false)
    @Getter @Setter
    private String name;

    /** Icon extension **/
    @Column(nullable = false)
    @Getter @Setter
    private String extension;

    /** Icon image **/
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] image;

    /** Icon image file **/
    @Column(name = "image_file", nullable = false)
    @Getter @Setter
    private String imageFile;
}
