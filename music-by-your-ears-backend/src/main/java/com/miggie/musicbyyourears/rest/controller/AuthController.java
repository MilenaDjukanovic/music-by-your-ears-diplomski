package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.UserDto;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.requests.AuthUserRequest;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreateUserRequest;
import com.miggie.musicbyyourears.requests.UpdateUserRequest;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
     * @return DTO of created user
     */
    @PostMapping("update/user")
    public UserDto update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return this.userService.updateUser(updateUserRequest);
    }

    @PostMapping("update/user/image")
    public UserDto updateImage( @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        CreateIconRequest createIconRequest = new CreateIconRequest();
            createIconRequest.setName(imageFile.getOriginalFilename());
            createIconRequest.setImageFile(imageFile.getContentType());
            createIconRequest.setImage(imageFile.getBytes());
            //TODO
            createIconRequest.setExtension(".jpg");
            return this.userService.updateUserImage(createIconRequest);
    }

    @GetMapping("/user")
    public UserDto getLoggedInUser(){
        return this.userService.getLoggedInUser();
    }
}
