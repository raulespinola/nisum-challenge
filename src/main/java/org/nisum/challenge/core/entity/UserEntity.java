package org.nisum.challenge.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

}