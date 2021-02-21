package yte.intern.spring.project.use_cases.login_to_the_system.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginSystemResponse {

    private final String code;
    private final String role;
    private final String tcKimlikNo;
    private final String token;
    
}