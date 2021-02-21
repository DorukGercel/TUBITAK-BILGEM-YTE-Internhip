package yte.intern.spring.project.use_cases.register_to_the_system.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterSystemResponse {
    
    private final String code;
    private final String role;
    private final String tcKimlikNo;
    private final String token;

}