package com.backend.emsbackend.security;

import com.backend.emsbackend.model.entity.Role;
import com.backend.emsbackend.model.entity.UserEntity;
import com.backend.emsbackend.repository.UserEntityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserEntityRepo userEntityRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userEntityRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );
        return new User(
                user.getUsername(),
                user.getPassword(),
                mapRoles(user.getRoles())
        );
    }

    private Collection<GrantedAuthority> mapRoles(Set<Role> roles) {

        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());


    }
}


