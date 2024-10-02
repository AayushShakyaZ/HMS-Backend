package com.backend.emsbackend.controller;

import com.backend.emsbackend.model.dto.AuthResponseDto;
import com.backend.emsbackend.model.dto.LoginUserDto;
import com.backend.emsbackend.model.dto.RegisterUserDto;
import com.backend.emsbackend.model.entity.Role;
import com.backend.emsbackend.model.entity.UserEntity;
import com.backend.emsbackend.repository.RoleRepo;
import com.backend.emsbackend.repository.UserEntityRepo;
import com.backend.emsbackend.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserEntityRepo userEntityRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDto registerUserDto){
        if (userEntityRepo.existsByUsername(registerUserDto.getUsername())){
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        Role roles = roleRepo.findByName("USER").get();
        user.setRoles(Collections.singleton(roles));

        userEntityRepo.save(user);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginUserDto loginUserDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);

    }
}
