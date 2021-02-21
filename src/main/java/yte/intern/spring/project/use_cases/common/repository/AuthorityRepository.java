package yte.intern.spring.project.use_cases.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yte.intern.spring.project.use_cases.common.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}