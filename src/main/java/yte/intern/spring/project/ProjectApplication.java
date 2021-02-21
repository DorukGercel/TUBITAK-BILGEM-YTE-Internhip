package yte.intern.spring.project;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.common.entity.Authority;
import yte.intern.spring.project.use_cases.common.entity.CorpUser;
import yte.intern.spring.project.use_cases.common.security.CustomUserDetailsManager;
import yte.intern.spring.project.use_cases.set_authorities.CreateAuthorities;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectApplication {

    private final CustomUserDetailsManager customUserDetailsManager;
	private final CreateAuthorities createAuthorities;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

	}

	@PostConstruct
	public void initSecurityAuthorities() {
		createAuthorities.createAuthorities();
	}

	@PostConstruct
	public void createCorpUsers(){

		//Corp Users Info
		CorpUser corpUser = new CorpUser();
		corpUser.setUsername("CorpUser");
		corpUser.setPassword("CorpUser");
		corpUser.setName("CorpUser");
		corpUser.setSurname("CorpUser");
		corpUser.setEmail("corpUser@gmail.com");
		corpUser.setTcKimlikNo("49264172870");

		//Set authortiy to corp user and save
        Authority corpUserAuthority = new Authority("CORP_USER");
        corpUser.setAuthorities(Set.of(corpUserAuthority));
        customUserDetailsManager.createCorpUser(corpUser);
	}
}
