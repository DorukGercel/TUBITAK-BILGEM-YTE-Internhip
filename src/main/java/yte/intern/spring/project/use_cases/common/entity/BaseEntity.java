package yte.intern.spring.project.use_cases.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION")
	private Long version;

	@Column(name = "CREATION_DATE")
	@CreatedDate
	private LocalDateTime creationDate;

	@Column(name = "LAST_MODIFIED_DATE")
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
}