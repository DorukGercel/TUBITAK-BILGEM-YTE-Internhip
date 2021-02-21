package yte.intern.spring.project.use_cases.set_authorities;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.common.entity.Authority;
import yte.intern.spring.project.use_cases.common.repository.AuthorityRepository;

@Component
@RequiredArgsConstructor
public class CreateAuthorities {
    
    private final AuthorityRepository authorityRepository;

    @Transactional
    public void createAuthorities(){

        authorityRepository.saveAll(Set.of(new Authority("NORMAL_USER"), new Authority("CORP_USER"), new Authority("ADMIN_USER")));

    }
}