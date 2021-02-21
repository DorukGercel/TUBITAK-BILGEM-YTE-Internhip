package yte.intern.spring.project.use_cases.common.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import yte.intern.spring.project.use_cases.common.entity.BaseUser;
import yte.intern.spring.project.use_cases.common.entity.NormalUser;

public interface BaseUserRepository extends JpaRepository<BaseUser, Long>{

	boolean existsByUsername(String username);

	Optional<BaseUser> findByUsername(String username);


	Optional<NormalUser> findByUserNo(String userNo);

	Optional<NormalUser> findByTcKimlikNo(String tcKimlikNo);

	@Transactional
	void deleteByUsername(String username);
}