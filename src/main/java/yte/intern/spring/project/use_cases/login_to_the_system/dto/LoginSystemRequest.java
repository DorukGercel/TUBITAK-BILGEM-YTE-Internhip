package yte.intern.spring.project.use_cases.login_to_the_system.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginSystemRequest {
    
    @NotEmpty
	private final String username;
	@NotEmpty
    private final String password;
    
}