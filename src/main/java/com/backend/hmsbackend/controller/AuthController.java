package com.backend.hmsbackend.controller;

import com.backend.hmsbackend.model.dto.AuthResponseDto;
import com.backend.hmsbackend.model.dto.LoginUserDto;
import com.backend.hmsbackend.model.dto.RegisterUserDto;
import com.backend.hmsbackend.model.entity.Role;
import com.backend.hmsbackend.model.entity.UserEntity;
import com.backend.hmsbackend.repository.RoleRepo;
import com.backend.hmsbackend.repository.UserEntityRepo;
import com.backend.hmsbackend.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String hello(){
        return "Hello user logged in!";
    }

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
