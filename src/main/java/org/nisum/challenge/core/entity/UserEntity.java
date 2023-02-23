package org.nisum.challenge.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.nisum.challenge.controller.dto.PhoneDto;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
@Entity
@Where(clause = "active=true")
@NoArgsConstructor
public class UserEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

}