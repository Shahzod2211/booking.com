package com.example.bookingplatform.user;

import com.example.bookingplatform.user.dto.UserSignUpDTO;
import com.example.bookingplatform.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Transactional
    public void create(UserSignUpDTO userSignUpDTO) {
        User user = new User(null, userSignUpDTO.getUsername(), userSignUpDTO.getPhoneNumber(), userSignUpDTO.getPassword());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));
    }
}
