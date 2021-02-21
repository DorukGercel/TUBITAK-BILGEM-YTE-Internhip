package yte.intern.spring.project.use_cases.register_to_the_system.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.common.entity.Authority;
import yte.intern.spring.project.use_cases.common.entity.NormalUser;
import yte.intern.spring.project.use_cases.common.security.CustomUserDetailsManager;
import yte.intern.spring.project.use_cases.common.security.utility.JwtUtil;
import yte.intern.spring.project.use_cases.register_to_the_system.dto.RegisterSystemRequest;
import yte.intern.spring.project.use_cases.register_to_the_system.dto.RegisterSystemResponse;
import yte.intern.spring.project.use_cases.register_to_the_system.mapper.RegisterSystemMapper;

@Service
@RequiredArgsConstructor
public class RegisterSystemService {
    
    @Value(value = "${security.secretKey}")
	private String secretKey;

    private final CustomUserDetailsManager customUserDetailsManager;
    private final DaoAuthenticationProvider authenticationProvider;
    private final RegisterSystemMapper registerMapper;

	public RegisterSystemResponse registerAndAuthenticate(RegisterSystemRequest normalUserRequest) {

        NormalUser normalUser = registerMapper.mapToEntity(normalUserRequest);

        //Set authortiy to normal user and save
        Authority normalUserAuthority = new Authority("NORMAL_USER");
        normalUser.setAuthorities(Set.of(normalUserAuthority));
        customUserDetailsManager.createUser(normalUser);

        //Check for authentication to give token
		Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(normalUserRequest.getUsername(), normalUserRequest.getPassword());
        System.out.println(usernamePasswordAuthenticationToken);
		try {   
            Authentication user = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
            String token = JwtUtil.generateToken(user, secretKey, 15);
			return new RegisterSystemResponse("SUCCESS" , "NormalUser",normalUser.getTcKimlikNo(),token);
		} catch (AuthenticationException e) {
            return new RegisterSystemResponse("FAIL" , null, null, null);
		}
	}
}