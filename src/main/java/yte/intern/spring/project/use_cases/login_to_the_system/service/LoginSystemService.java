package yte.intern.spring.project.use_cases.login_to_the_system.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.common.repository.BaseUserRepository;
import yte.intern.spring.project.use_cases.common.security.utility.JwtUtil;
import yte.intern.spring.project.use_cases.login_to_the_system.dto.*;

@Service
@RequiredArgsConstructor
public class LoginSystemService {
    
    @Value(value = "${security.secretKey}")
	private String secretKey;

	private final DaoAuthenticationProvider authenticationProvider;
	private final BaseUserRepository baseUserRepository;

	public LoginSystemResponse authenticate(final LoginSystemRequest loginRequest) {

		Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		try {
			Authentication user = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
			String token = JwtUtil.generateToken(user, secretKey, 15);

			String tcKimlikNo = baseUserRepository.findByUsername(loginRequest.getUsername()).get().getTcKimlikNo();

			if(loginRequest.getUsername().equals("CorpUser")){
				return new LoginSystemResponse("SUCCESS", "CorpUser",tcKimlikNo,token);
			}
			else{
				return new LoginSystemResponse("SUCCESS", "NormalUser",tcKimlikNo,token);
			}

			
		} catch (AuthenticationException e) {
			return new LoginSystemResponse("FAIL", null,null ,null);
		}
    }    
}