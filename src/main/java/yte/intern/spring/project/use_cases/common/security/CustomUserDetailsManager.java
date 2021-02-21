package yte.intern.spring.project.use_cases.common.security;

import yte.intern.spring.project.use_cases.common.entity.BaseUser;
import yte.intern.spring.project.use_cases.common.entity.CorpUser;
import yte.intern.spring.project.use_cases.common.entity.NormalUser;
import yte.intern.spring.project.use_cases.common.repository.BaseUserRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsManager implements UserDetailsManager {

    private final BaseUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(final UserDetails userDetail) {
        NormalUser user = (NormalUser) userDetail;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void createCorpUser(final UserDetails userDetail) {
        CorpUser corpUser = (CorpUser) userDetail;
        corpUser.setPassword(passwordEncoder.encode(corpUser.getPassword()));
        userRepository.save(corpUser);
    }

    @Override
    public void updateUser(final UserDetails user) {
        NormalUser oldUser = (NormalUser) loadUserByUsername(user.getUsername());
        NormalUser newUser = (NormalUser) user;
        newUser.setId(oldUser.getId());
        userRepository.save(newUser);
    }

    @Override
    public void deleteUser(final String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        BaseUser user = (NormalUser) loadUserByUsername(username);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Wrong old password is given!");
        }
    }

    @Override
    public boolean userExists(final String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}
}