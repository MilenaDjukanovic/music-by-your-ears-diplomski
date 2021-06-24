package com.miggie.musicbyyourears.repo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    /** User ID **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Username of the user **/
    @Column(nullable = false)
    @Setter
    private String username;

    /** User password **/
    @Column(nullable = false)
    @Getter @Setter
    private String password;

    /** First Name **/
    @Column(name = "first_name", nullable = false)
    @Getter @Setter
    private String firstName;

    /** Last Name **/
    @Column(name = "last_name", nullable = false)
    @Getter @Setter
    private String lastName;

    /** User image **/
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] image;

    /** Description **/
    @Column()
    @Getter @Setter
    private String about;

    /** Flag if the user is enabled **/
    @Setter
    private boolean enabled = true;

    /**
     * Getter for user authorities
     * @return User authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    /**
     * Getter for username
     * @return username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Checks if the user account is not expired
     * @return true if not expired, otherwise false
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    /**
     * Checks if the user account is non locked
     * @return true if account is non locked, otherwise false
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    /**
     * Checks if credentials are expired
     * @return true if the credentials are non expired, otherwise false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    /**
     * Checks if the user account is enabled
     * @return true if the account is enabled, otherwise false
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
