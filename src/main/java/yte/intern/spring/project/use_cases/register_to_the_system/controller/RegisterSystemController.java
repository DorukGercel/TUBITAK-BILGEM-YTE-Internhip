package yte.intern.spring.project.use_cases.register_to_the_system.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.register_to_the_system.dto.RegisterSystemRequest;
import yte.intern.spring.project.use_cases.register_to_the_system.dto.RegisterSystemResponse;
import yte.intern.spring.project.use_cases.register_to_the_system.service.RegisterSystemService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value =  "/registerSystem", method = RequestMethod.POST)
public class RegisterSystemController {
    
    private final RegisterSystemService registerService;

    @PostMapping
    public RegisterSystemResponse register(@Valid @RequestBody final RegisterSystemRequest registerRequest) {
		return registerService.registerAndAuthenticate(registerRequest);
	}
}