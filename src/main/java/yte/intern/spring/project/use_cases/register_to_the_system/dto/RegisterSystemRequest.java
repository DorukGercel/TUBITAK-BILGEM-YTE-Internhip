package yte.intern.spring.project.use_cases.register_to_the_system.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.register_to_the_system.validators.TcKimlikNo;

/*DTO For Register System*/
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterSystemRequest {
    
    @Size(max = 255, message = "Username can't be longer than 255 characters!")
    private String username;

    @Size(max = 255, message = "Password can't be longer than 255 characters!")
    private String password;

    @Size(max = 255, message = "Name can't be longer than 255 characters!")
    private String name;

    @Size(max = 255, message = "Surname can't be longer than 255 characters!")
    private String surname;

    @Email(message = "Email not valid!")
    private String email;

    @TcKimlikNo
    private String tcKimlikNo;
}