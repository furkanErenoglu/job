package com.jobportal.job.service.impl;

import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.model.User;
import com.jobportal.job.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    private final MainLogger LOGGER = new MainLogger(UserCredentialsServiceImpl.class);
    public UserCredentialsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    private User findUserByUsername(String username){
        return userRepository.
    }
}
