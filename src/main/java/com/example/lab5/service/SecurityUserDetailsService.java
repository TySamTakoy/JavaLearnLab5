package com.example.lab5.service;

import com.example.lab5.persistence.users.SecurityUserDetails;
import com.example.lab5.persistence.users.UserEntity;
import com.example.lab5.persistence.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByFullName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SecurityUserDetails(user);
    }
}
