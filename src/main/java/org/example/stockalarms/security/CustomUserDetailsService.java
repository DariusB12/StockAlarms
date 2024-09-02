package org.example.stockalarms.security;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.model.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username(email) not found"));
    }
}
