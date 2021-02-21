package yte.intern.spring.project.use_cases.login_to_the_system.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.login_to_the_system.dto.LoginSystemRequest;
import yte.intern.spring.project.use_cases.login_to_the_system.dto.LoginSystemResponse;
import yte.intern.spring.project.use_cases.login_to_the_system.service.LoginSystemService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/loginSystem", method = RequestMethod.POST)
public class LoginSystemController {
    
    private final LoginSystemService loginSystemService;

    @PostMapping
	public LoginSystemResponse login(@Valid @RequestBody final LoginSystemRequest loginSystemRequest) {
		return loginSystemService.authenticate(loginSystemRequest);
	}
}