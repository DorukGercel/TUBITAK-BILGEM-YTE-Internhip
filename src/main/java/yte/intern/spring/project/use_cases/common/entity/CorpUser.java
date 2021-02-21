package yte.intern.spring.project.use_cases.common.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CORP")
public class CorpUser extends BaseUser {
    
    @Column(name = "CORP_NO", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String corpNo;
}