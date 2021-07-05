package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.UserDto;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.requests.AuthUserRequest;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreateUserRequest;
import com.miggie.musicbyyourears.security.JwtTokenUtil;
import com.miggie.musicbyyourears.service.AuthorizationService;
import com.miggie.musicbyyourears.service.UserService;
import com.miggie.musicbyyourears.service.mappers.UserViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController @RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    /** Authentication manager */
    private final AuthenticationManager authenticationManager;
    /** JWT Token Util */
    private final JwtTokenUtil jwtTokenUtil;
    /** User View Mapper */
    private final UserViewMapper userViewMapper;
    /** User Service */
    private final UserService userService;
    /** Authorization Service **/
    private final AuthorizationService authorizationService;

    /**
     * Handles the user login request
     * @param authUserRequest user login details
     *
     * @return User if login is successful, otherwise returns Unauthorized status
     */
    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid AuthUserRequest authUserRequest){
        try{
            Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(), authUserRequest.getPassword()));

            UserEntity user = (UserEntity) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, this.jwtTokenUtil.generateAccessToken(user))
                    .body(this.userViewMapper.toDto(user));
        }catch(BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("register/user")
    public UserDto register(@RequestBody @Valid CreateUserRequest createUserRequest)  {
        return this.userService.createUser(createUserRequest);
    }

    /**
     * Creates user
     * @param imageFile user profile image
     * @param firstName user first name
     * @param lastName user last name
     * @param username username
     * @param password password
     * @param rePassword rePassword
     * @param about about
     * @return DTO of created user
     * @throws IOException
     */
    @PostMapping("update/user")
    public UserDto register(@RequestParam("imageFile")MultipartFile imageFile,
                            @RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("rePassword") String rePassword,
                            @RequestParam("about") String about) throws IOException {
        CreateIconRequest createIconRequest = new CreateIconRequest();
        createIconRequest.setName(imageFile.getOriginalFilename());
        createIconRequest.setImageFile(imageFile.getContentType());
        createIconRequest.setImage(imageFile.getBytes());
        //TODO
        createIconRequest.setExtension(".jpg");

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName(firstName);
        createUserRequest.setLastName(lastName);
        createUserRequest.setPassword(password);
        createUserRequest.setRePassword(rePassword);
        createUserRequest.setUsername(username);
        createUserRequest.setAbout(about);
        return this.userService.updateUser(createUserRequest, createIconRequest);
    }
//
//    @GetMapping("/{userId}")
//    public UserDto getUserById(@PathVariable Long userId) throws AuthenticationException {
//        if(authorizationService.getAuthenticatedUser().getId().equals(userId)) {
//            return this.userService.findById(userId);
//        }
//        throw new AuthenticationException("Don't have access to this account");
//    }

}
